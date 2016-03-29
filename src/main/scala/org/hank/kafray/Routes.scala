package org.hank.kafray

/**
  * Created by ihank on 16/3/25.
  */
trait Routes extends MsgService with EchoService with ClientConfigService{
    val routes =
        echoRoute ~
            clientConfigRoute ~
            msgRoute

}
