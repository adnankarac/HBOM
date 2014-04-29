package me.ak.hbase;

import java.io.IOException;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;

public class HBaseScan {
	private final Configuration configuration;
	private final ResultScanner resultScanner;
	public HBaseScan(Configuration conf) {
		this.configuration = conf;
		this.resultScanner = null;
	}
	
	public HBaseScan(Configuration conf, ResultScanner resultScanner) {
		this.configuration = conf;
		this.resultScanner = resultScanner;
	}
	
	private HBaseScan scan(String tableName, Filter filters) throws IOException {
		Scan scan = new Scan().setFilter(filters);
		try(HTable table = new HTable(configuration, tableName)) {
			return new HBaseScan(configuration, table.getScanner(scan));
		}
	}
	
	private Stream<Result> test() {
		Spliterator<Result> spliterator = this.resultScanner.spliterator();
		return StreamSupport.stream(spliterator, true);
	}

}
