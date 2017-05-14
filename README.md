# spark-kafka-streaming
The project (in WIP deal with Spark streaming and Kafka (with only Scala).

Before you start trying out the simple programs in this project, make sure your docker is running and you have the container running that
spins up the Kafka instance with definitive UI:

## Pre-requisite:
* Java (1.8 preferably)
* Start docker (refer installation guides in Google)
* Get the landoop docker image and run Kafka instance.

## Docker and Landoop for Linux/Mac users:
Once the above set-ups are completed, run the following command in terminal:

Docker for Mac >= 1.12, Docker for Windows 10 
```                      
docker run --rm -it \
           -p 2181:2181 -p 3030:3030 -p 8081:8081 \
           -p 8082:8082 -p 8083:8083 -p 9092:9092 \
           -e ADV_HOST=127.0.0.1 \
           landoop/fast-data-dev
```
Start the container and verify if you can see the UI in 
http://127.0.0.1:3030/


## For Beginners:
As soon as you get a quick idea of how things work using the sample codes in this project, start reading the following documentation in order: </br>
https://spark.apache.org/docs/2.1.0/streaming-programming-guide.html  </br>
https://spark.apache.org/docs/2.1.0/streaming-kafka-0-10-integration.html

## A few doubts:
It says in spark streaming API, that only one StreamingContext can be active in a JVM at the same time.
This can be different from the usual consumer implementations of Kafka. While we can consider spark streaming as an implementation similar to any other [consumer](src/main/scala/com/thajaf/kafka/beginner/KafkaConsumerDemo.scala), the limitation of only one context in a JVM makes it a little harder for parallel consumption of kafka message queues? As we learn, we might get an idea on how this can be handled `easily` in Spark Streaming.
