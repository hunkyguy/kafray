package org.hank.kafray

import akka.actor.ActorSystem
import kafka.common.FailedToSendMessageException
import spray.routing.{Route, HttpService}
import spray.json._

import NestMsg._

/**
  * Created by ihank on 16/3/25.
  */
trait MsgService extends HttpService{
    implicit val ec = ActorSystem().dispatcher

    val rawContent = extract(_.request.entity.asString)
    val producer = new MsgProducer(ParseConfig.TOPICS)

    def validateAndSend(message: String): Route = {
        try {
            message.parseJson.convertTo[NestMessage]
            producer.send(message)
            complete(MsgResponse(0, "").toJson.toString())
        }catch {
            case e: FailedToSendMessageException =>
                val response = MsgResponse(1, "Fail to Send Message.").toJson.toString()
                complete(response)
            case e: DeserializationException =>
                val response = MsgResponse(2, "Message format incorrect.").toJson.toString()
                complete(response)
            case e: Throwable =>
                val response = MsgResponse(9, "Internal error.").toJson.toString()
                complete(response)
        }
    }

    val msgRoute =
        path("kafka") {
            post {
                rawContent {
                    json => validateAndSend(json)
                }
            } ~
                get{
                    parameterMap {
                        params =>
                            // only get the first key, the whole raw json str follow behind the '?' char
                            val data = params.keys.head
                            validateAndSend(data)
                    }
                }
        }
}
