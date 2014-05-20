package me.ak.hbase;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import me.ak.annotations.Pure;
import me.ak.utils.StringUtil;
import me.ak.utils.TableName;

import org.apache.commons.lang.NotImplementedException;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;

public abstract class HBaseObject {
	private String id;
	private Map<String, Map<String, byte[]>> contentWithFamilies;
	
	@Pure
	protected String tableName() {
		return new TableName(this.getClass().getSimpleName())
		.toUndersoceNotation()
		.pluralize().toString();
	}
	
	protected HBaseObject(String id) {
		this.id = id;
		this.initialize(id);
	}

	private HBaseObject initialize(String id) {
		Optional<Result> result = null;
		result = HBaseObjectManager.instance()
				.getPersistance(this.tableName())
				.flatMap(x -> x.get(id));
		
		if(!result.map(Result::isEmpty).orElse(false)) { 
			this.initializeContent(result.get());
		} 
		return this;
	}
	
	private void initializeContent(Result res) {
		this.contentWithFamilies = res.list().stream().collect(
				Collectors.groupingBy(k -> StringUtil.byteToString(k.getFamily()),
						Collectors.toMap(item -> StringUtil.byteToString(((KeyValue) item).getQualifier()), 
										 item -> ((KeyValue) item).getValue()))	
			);
	}
	
	public Optional<byte[]> get(String family, String qualifier) {
		Optional<Map<String, byte[]>> familyKeyValues = Optional.ofNullable(this.contentWithFamilies.get(family));
		return familyKeyValues.map(m -> m.get(qualifier));
	}
	
	public Optional<Map<String, byte[]>> get(String family) {
		return Optional.ofNullable(this.contentWithFamilies.get(family));
	}
	
	public void save() {
		HBaseObjectManager.instance()
			.getPersistance(this.tableName())
			.ifPresent(x -> x.put(this.id, this.contentWithFamilies));
	}
	
	
	//factory methods
	public static HBaseObject create(String id) {
		throw new NotImplementedException();
	}
	
	public static HBaseObject find(String id) {
		throw new NotImplementedException();
	}
	
	//getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
