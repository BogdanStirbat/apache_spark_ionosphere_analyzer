import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
import org.apache.spark.ml.feature.{StringIndexer, VectorAssembler}

object IonosphereAnalyzerApp {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Ionosphere Analyzer Application").getOrCreate()

    val dataFile = "/path/to/data/ionosphere.data"
    val data = spark.read.format("csv").option("inferSchema", "true").load(dataFile)

    val featureCols = Array("_c0", "_c1", "_c2", "_c3", "_c4", "_c5", "_c6", "_c7", "_c8", "_c9",
                            "_c10", "_c11", "_c12", "_c13", "_c14", "_c15", "_c16", "_c17", "_c18", "_c19",
                            "_c20", "_c21", "_c22", "_c23", "_c24", "_c25", "_c26", "_c27", "_c28", "_c29",
                            "_c30", "_c31", "_c32", "_c33")

    val assembler = new VectorAssembler().setInputCols(featureCols).setOutputCol("features")
    val labelIndexer = new StringIndexer().setInputCol("_c34").setOutputCol("label")

    val df2 = assembler.transform(data)
    val df3 = labelIndexer.fit(df2).transform(df2)

    val Array(trainData, testData) = df3.randomSplit(Array(0.6, 0.4))
    val lr = new LogisticRegression().setMaxIter(100).setRegParam(0.1).setElasticNetParam(0.1)
    val model = lr.fit(trainData)
    val predictions = model.transform(testData)

    val evaluator = new BinaryClassificationEvaluator().setLabelCol("label")
    val accuracy = evaluator.evaluate(predictions)
    println(s"accuracy: $accuracy")

    spark.stop()
  }
}
