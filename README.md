# DQT - data quality tool

Is a library built on top of Apache Spark for defining "unit tests for data", which measure data quality in large datasets, inspired by Amazon Deequ

Allows you to calculate data quality metrics on your dataset, define and verify data quality constraints, and be informed about changes in the data distribution. Instead of implementing checks and verification algorithms on your own, you can focus on describing how your data should look. DQT is implemented on top of Apache Spark and is designed to scale with large datasets (think billions of rows) that typically live in a distributed filesystem or a data warehouse.

### Example:
```scala
val dqt: DQT[SimpleStruct] = new DQT(
dataset,
Seq[Constraint[SimpleStruct]](new NotNullConstraint(col("name")), new SizeConstraintcol("name"), 10, 1000)),
Seq[Callback[SimpleStruct]](logIt)
)
dqt.run()
```
is equivalent of

_"Amount of possible values of column name of dataset dqt must be between 10 and 1000 and must be not null. Otherwise, we should log these values."_