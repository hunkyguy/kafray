package org.hank.kafray

import com.typesafe.config.ConfigFactory

/**
  * Created by ihank on 16/3/25.
  */
object ParseConfig {
    val config = ConfigFactory.load()

    final val PORT = config.getInt("server.port")

    final val BROKERS = config.getString("producer.brokers")

    final val TOPICS = config.getString("producer.topics")

    final val CLIENT_CONFIG = config.getConfig("client")

}
