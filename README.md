# HBOM

HBase Object Model. Something that'll help you handle HTable records from your Java (or JRuby) app.

## Disclaimer

I started it out to play around with new concepts of Java 8. It might turn into something usefull though. This is still work in progress. 


## How to use it

This is an object model for HBase. Core of it is HBaseObject class. 

Just inherit it with your own class named after your HTable. For instance, if your hbase table name is my_hbase_records, your class name should be MyHbaseRecord (yes, table name is plural, class name is singular). You should get something resembling an ORM for non-relational database.

```java
public class MyHbaseRecord extends HBaseObject {
   public MyHbaseRecord(String id) {
      super(id);
   }
}
```

You will need to initialize HBaseObjectMManager with your org.apache.hadoop.conf.Configuration object:

For example, if you are working locally, just do: 
```java
HBaseObjectManager.initialize(new Configuration());
```
somewhere in your app initialization.

### Getting recodrs

Initialize new object and pass row id as String to your constructor:

```java
MyHbaseRecord rec = new MyHbaseRecord("1234");
```
