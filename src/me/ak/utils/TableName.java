package me.ak.utils;

import java.util.Arrays;

import me.annotations.Pure;

public class TableName {
	private final String tableName;
	
	@Pure
	public TableName hungarize() {
		return new TableName(
				Arrays.asList(tableName.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"))
				.stream()
				.reduce("", (acc, y) -> acc + "_" + y)
				.toLowerCase()
				.substring(1)
			);
	}
	
	@Pure
	public TableName pluralize() {
		return new TableName(tableName + "s");
	}
	
	public TableName(String s) {
		this.tableName = s;
	}
	
	@Override
	public String toString() {
		return this.tableName;
	}
}
