package org.hank.kafray

import akka.actor.{ActorRefFactory, Actor}

/**
  * Created by ihank on 16/3/25.
  */
class RoutesActor extends Actor with Routes {
    val actorRefFactory: ActorRefFactory = context

    override def receive = runRoute(routes)
}
