HBOM
====

HBase Object Model. Something that'll help you handle HTable records from your Java (or JRuby) app.

Disclaimer
----
I started it out to play around with new concepts of Java 8. It might turn out into something usefull though. This is still work in progress. 


How to use it
----
This is an object model for HBase. Core of it is HBaseObject class. 

Just inherit it with your own class named after your HTable. For instance, if your hbase table name is my_hbase_records, your class name should be MyHbaseRecord (yes, table name is plural, class name is singular). You should get something resembling an ORM for non-relational database.

<code>
public class MyHbaseRecord extends HBaseObject {
}
</code>

You should also implement all the constructors in your subclass (eventhough you'll probably just call super()) as they all throw IOException.
