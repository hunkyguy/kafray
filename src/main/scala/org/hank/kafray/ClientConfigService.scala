package org.hank.kafray

import com.typesafe.config.ConfigRenderOptions
import spray.routing.HttpService

import ParseConfig.CLIENT_CONFIG

/**
  * Created by ihank on 16/3/25.
  */
trait ClientConfigService extends HttpService{
    val clientConfigRoute = {
        (path("config") & get) {
            parameters('version.as[String] ? "default") {
                version: String => {
                    val ver = version.replace('.', '_')
                    try {
                        val config = CLIENT_CONFIG.getValue(ver).render(ConfigRenderOptions.concise())
                        complete(config)
                    } catch {
                        case _: Throwable => {
                            val config = CLIENT_CONFIG.getValue("default").render(ConfigRenderOptions.concise())
                            complete(config)
                        }
                    }
                }
            }
        }
    }

}
