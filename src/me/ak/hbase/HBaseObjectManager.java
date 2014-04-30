package me.ak.hbase;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import me.ak.hbase.persistence.Persistence;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class HBaseObjectManager {
	private static HBaseObjectManager locinstance; 
	private Map<String, Persistence> persistances;
	
	public static HBaseObjectManager initialize(Configuration configuration) {
		if(locinstance == null) {
			locinstance = new HBaseObjectManager();
		
			HBaseAdmin admin;
			try {
				admin = new HBaseAdmin(configuration);
				List<String> tableNames = Arrays.asList(admin.getTableNames());
				locinstance.persistances = tableNames.stream()
						.collect(Collectors.toMap(x -> x, x -> new Persistence(configuration, x)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return locinstance;
	}
	
	public static HBaseObjectManager instance() {
		if(locinstance == null) {
			throw new RuntimeException("HBaseObjectManager not initialized!");
		} else {
			return locinstance;
		}
	}
	
	public Optional<Persistence> getPersistance(String tableName) {
		return Optional.ofNullable(this.persistances.get(tableName));
	}
	
	private HBaseObjectManager(){}
}
