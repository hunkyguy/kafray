package org.hank.kafray

import java.util.Properties

import kafka.producer.{KeyedMessage, Producer, ProducerConfig}

/**
  * Created by ihank on 16/3/25.
  */
case class MsgProducer(topic: String){
    val props = new Properties()
    props.put("metadata.broker.list", ParseConfig.BROKERS)
    props.put("serializer.class", "kafka.serializer.StringEncoder")
    props.put("producer.type", "sync")
    //props.put("message.send.max.retries","1")
    props.put("acks", "0")

    val config = new ProducerConfig(props)
    lazy val producer = new Producer[String, String](config)

    private def keyedMessage(topic: String, message: String): KeyedMessage[String, String] =
        new KeyedMessage[String, String](topic, message)

    private def sendMessage(producer: Producer[String, String], message: KeyedMessage[String, String]) =
        producer.send(message)


    def send(message: String) = sendMessage(producer, keyedMessage(topic, message))

}

