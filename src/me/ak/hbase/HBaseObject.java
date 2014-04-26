package me.ak.hbase;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import me.ak.utils.StringUtil;
import me.ak.utils.TableName;
import me.annotations.Pure;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;

public abstract class HBaseObject {
	private String id;
	private HTable table;
	private Map<String, Map<String, String>> contentWithFamilies;
	
	@Pure
	protected String tableName() {
		return new TableName(this.getClass().getSimpleName())
		.hungarize()
		.pluralize().toString();
	}
	
	protected HBaseObject() throws IOException {
		this.table = new HTable(new Configuration(), this.tableName());
	}
	
	protected HBaseObject(Configuration conf) throws IOException {
		this.table = new HTable(conf, this.tableName());
	}

	protected HBaseObject(String tableName) throws IOException {
		this.table = new HTable(new Configuration(), tableName);
	}

	protected HBaseObject(Configuration conf, String tableName) {
		try {
			this.table = new HTable(conf, tableName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HBaseObject initialize(String id) {
		Get get = new Get(id.getBytes());
		Optional<Result> result = null;
		try {
			result = Optional.of(this.table.get(get));
		} catch (IOException e) {
			e.printStackTrace();
		}
		result.ifPresent(this::initializeContent);
		return this;
	}
	
	private void initializeContent(Result res) {
		this.contentWithFamilies = res.list().stream().collect(
				Collectors.groupingBy(k -> StringUtil.byteToString(k.getFamily()),
						Collectors.toMap(item -> StringUtil.byteToString(((KeyValue) item).getKey()), 
										 item -> StringUtil.byteToString(((KeyValue) item).getValue())))	
			);
	}
	
	public Optional<String> get(String family, String qualifier) {
		Optional<Map<String, String>> familyKeyValues = Optional.ofNullable(this.contentWithFamilies.get(family));
		return familyKeyValues.map(m -> m.get(qualifier));
	}
	
	public Optional<Map<String, String>> get(String family) {
		return Optional.ofNullable(this.contentWithFamilies.get(family));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HTable getTable() {
		return table;
	}

	public void setTable(HTable table) {
		this.table = table;
	}
}
