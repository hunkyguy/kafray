import org.hank.kafray.NestMessage
import spray.json._
import org.hank.kafray.NestMsg._

import scala.util.parsing.json.JSON

/**
  * Created by ihank on 3/28/16.
  */
object ParseMessageTest{
    def main(args: Array[String]) {
        val goodStr = """{
                        |    "body": {
                        |        "action": [
                        |            {
                        |                "id": "x623",
                        |                "network": "WIFI",
                        |                "timestamp": "1449468637716"
                        |            }
                        |        ],
                        |        "deviceId": "hank_880ce084",
                        |        "platform": "python"
                        |    },
                        |    "head": {
                        |        "source": "1",
                        |        "version": "1.0"
                        |    }
                        |}""".stripMargin
        val mp = JSON.parseFull(goodStr)
        println(mp)
        goodStr.parseJson.convertTo[NestMessage]
        val badStr = // missing action.id
            """{
              |    "body": {
              |        "action": [
              |            {
              |                "network": "WIFI",
              |                "timestamp": "1449468637716"
              |            }
              |        ],
              |        "deviceId": "hank_880ce084",
              |        "platform": "python"
              |    },
              |    "head": {
              |        "source": "1",
              |        "version": "1.0"
              |    }
              |}""".stripMargin
        badStr.parseJson.convertTo[NestMessage]
    }
}
