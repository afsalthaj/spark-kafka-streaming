name := "spark-streaming"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-streaming_2.11" % "2.1.0",
  "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.1.0"
)