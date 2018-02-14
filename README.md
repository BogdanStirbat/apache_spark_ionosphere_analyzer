A project for running the logistic regression algorithm in [Apache Spark](https://spark.apache.org/) .

The algorithm is run on radar data, collected to detect free electrons in the ionosphere; the data set, and more information about the data itself,
can be found [here](http://archive.ics.uci.edu/ml/datasets/Ionosphere) .

To compile the project, run:
```shell
  sbt package
```

To run the project:
```shell
  /path/to/apache_spark/spark-2.2.1-bin-hadoop2.7/bin/spark-submit --class "IonosphereAnalyzerApp" --master local[4] target/scala-2.11/ionosphere-analizer-project_2.11-1.0.jar
```


sbt package

/home/bogdan/Applications/apache_spark/spark-2.2.1-bin-hadoop2.7/bin/spark-submit --class "IonosphereAnalyzerApp" --master local[4] target/scala-2.11/ionosphere-analizer-project_2.11-1.0.jar


