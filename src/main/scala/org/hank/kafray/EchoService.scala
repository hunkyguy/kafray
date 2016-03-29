package org.hank.kafray

import java.net.InetAddress
import java.util.Date

import spray.routing.HttpService

/**
  * Created by ihank on 16/3/25.
  */
trait EchoService extends HttpService {
    val echoRoute = path(""){
        complete("Host is:" + InetAddress.getLocalHost.getHostName + ", Date: " + new Date())
    }
}
