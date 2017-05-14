package com.thajaf.kafka.beginner

import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

/**
  * Created by afsalthaj on 14/05/2017.
  */
// Run a Landoop kafka docker container to have your kafka cluster up and running.
// and then run the below consumer job
// You could try running multiple instances of consumer and see how the parallel processing works for
// a consumer group
object KafkaConsumerDemo {

  def main(args: Array[String]): Unit = {
    val properties = new Properties
    properties.setProperty("bootstrap.servers", "127.0.0.1:9092")
    properties.setProperty("key.deserializer", classOf[StringDeserializer].getName)
    properties.setProperty("value.deserializer", classOf[StringDeserializer].getName)
    properties.setProperty("group.id", "mygroupid")
    properties.setProperty("auto.offset.reset", "earliest")
    properties.setProperty("enable.auto.commit", "false")

    // If the autocommit is set to true, you can define an interval too (make sure your system is idempotent)
    // Also, if auto commit is true, there is no need of separate `kafkaConsumer.commitAsync`
    // properties.setProperty("enable.auto.commit", "true")
    // properties.setProperty("auto.commit.interval.ms", "1000")

    val kafkaConsumer = new KafkaConsumer[String, String](properties)

    kafkaConsumer.subscribe(Collections.singleton("second_topic"))

    while(true) {
      println(s"===============Waiting for the next read================")
      val consumerRecords: ConsumerRecords[String, String] = kafkaConsumer.poll(100)
      val recordIterator = consumerRecords.records("second_topic").iterator()
      while(recordIterator.hasNext){
        val record = recordIterator.next
        println(s"The key of the iterator is ${record.key}")
        println(s"""The value of the iterator is "${record.value}" """)
        println(s"The partition of the iterator is ${record.partition}")
        println(s"The topic of the iterator is ${record.topic}")
        println("\n==================================================")
      }

      kafkaConsumer.commitAsync
    }
  }
}