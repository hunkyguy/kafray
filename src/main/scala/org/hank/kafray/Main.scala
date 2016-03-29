package org.hank.kafray

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http

/**
  * Created by ihank on 16/3/25.
  */
object Main extends App{
    implicit lazy val system = ActorSystem("Main")
    implicit val ec = system.dispatcher

    val service = system.actorOf(Props[RoutesActor], "Service")

    /* bind to Akka's I/O interface */
    IO(Http) ! Http.Bind(service, "0.0.0.0", ParseConfig.PORT)

}
