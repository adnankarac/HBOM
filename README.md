HBOM
====

HBase Object Model

This is still work in progress.

Object model for HBase. Core of it is HBaseObject class. Just inherit it with your own class named after your HTable for instance, if your hbase table name is my_hbase_records, your class name should be MyHbaseRecord (yes, table name is plural, class name is singular).
