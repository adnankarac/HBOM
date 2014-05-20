package me.ak.hbase.persistence;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;

public class Persistence {
	private String tableName;
	private Configuration configuration;
	
	public Persistence(Configuration configuration, String tableName) {
		this.configuration = configuration;
		this.tableName = tableName;
	}
	
	public void get() {}
	
	public void put(String id, Map<String, Map<String, byte[]>> contentWithFamilies) {
		Put put = new Put(id.getBytes());
		contentWithFamilies.forEach((family, qualValue) -> 
										qualValue.forEach((qualifier, value) -> 
											put.add(family.getBytes(), qualifier.getBytes(), value)));
		try {
			try(HTable table = new HTable(configuration, tableName)) {
				table.put(put);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Optional<Result> get(String id) {
		try {
			try(HTable table = new HTable(configuration, tableName)) {
				return Optional.of(table.get(new Get(id.getBytes())));
			}
		} catch (IOException e) {
			return Optional.empty();
		}
	}
}
