package me.ak;

import me.ak.hbase.HBaseObjectManager;

import org.apache.hadoop.conf.Configuration;

//just for testing
public class App {

	public static void main(String[] args) {
		HBaseObjectManager.initialize(new Configuration());
		
	}
}
