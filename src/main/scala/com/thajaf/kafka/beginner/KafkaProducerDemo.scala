package com.thajaf.kafka.beginner

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

/**
  * Created by afsalthaj on 14/05/2017.
  */
// Run a Landoop kafka docker container to have your kafka cluster up and running.
// Once that is set up, please run the below job to produce and send messages to specified topic (and partitions)
object KafkaProducerDemo {
  def main(args: Array[String]): Unit = {
    val properties = new Properties
    properties.setProperty("bootstrap.servers", "127.0.0.1:9092")
    properties.setProperty("key.serializer", classOf[StringSerializer].getName)
    properties.setProperty("value.serializer", classOf[StringSerializer].getName)
    properties.setProperty("acks", "1")
    properties.setProperty("retries", "3")
    properties.setProperty("linger.ms", "1")

    val producer = new KafkaProducer[String, String](properties)

    (1 to 10).map(t => {
      producer.send(new ProducerRecord[String, String]("second_topic", t.toString, s"the message with key---------------: $t" ))
    })

    producer.close()
  }
}
