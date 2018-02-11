import org.apache.spark.sql.SparkSession

object IonosphereAnalyzerApp {
  def main(args: Array[String]) {
    val dataFile = "/home/bogdan/side_projects/apache_spark_ionosphere_analyzer/data/ionosphere.data"
    val spark = SparkSession.builder.appName("Ionosphere Analyzer Application").getOrCreate()
    val logData = spark.read.textFile(dataFile).cache()
    val goodExamples = logData.filter(line => line.contains("g")).count()
    val badExamples = logData.filter(line => line.contains("b")).count()
    println(s"Good examples: $goodExamples, bad examples: $badExamples")
    spark.stop()
  }
}
