package me.ak.hbase.persistence;

import java.io.IOException;
import java.util.Optional;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;

public class Persistence {
	private String tableName;
	private Configuration configuration;
	
	public Persistence(Configuration configuration, String tableName) {
		this.configuration = configuration;
		this.tableName = tableName;
	}
	
	public void get() {}
	
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
