HBOM
====

HBase Object Model

I started it out to play around with new concepts of Java 8. It might turn out into something usefull. This is still work in progress. 

Object model for HBase. Core of it is HBaseObject class. Just inherit it with your own class named after your HTable. For instance, if your hbase table name is my_hbase_records, your class name should be MyHbaseRecord (yes, table name is plural, class name is singular). You should get something resembling an ORM for non-relational database.
