package org.hank.kafray

import spray.json._

/**
  * Created by ihank on 16/3/25.
  */
case class NestMessage(head: MsgHead, body: MsgBody)

case class MsgHead(source: String, version: String)

case class MsgBody(
                      deviceId: String,
                      platform: String,
                      action: List[MsgBodyAction]
                  )

case class MsgBodyAction(
                            id: String,
                            timestamp: String,
                            network: Option[String]
                        )

case class MsgResponse (result: Int, reason: String)

object NestMsg extends DefaultJsonProtocol {
    implicit val msgBodyAction = jsonFormat(MsgBodyAction, "id", "timestamp", "network")

    implicit val msgBody = jsonFormat(MsgBody, "deviceId", "platform", "action")

    implicit val msgHead = jsonFormat(MsgHead, "source", "version")

    implicit val msg = jsonFormat(NestMessage, "head", "body")

    implicit val msgResponse = jsonFormat2(MsgResponse)
}