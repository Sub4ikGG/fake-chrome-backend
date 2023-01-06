package ehea

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import ehea.plugins.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import org.slf4j.event.Level

fun main() {
    embeddedServer(CIO, port = 8080) {
        install(CallLogging) {
            level = Level.INFO

            format { call ->
                val status = call.response.status()
                val httpMethod = call.request.httpMethod.value
                val userAgent = call.request.headers["User-Agent"]
                val ipAddress = call.request.headers["ip-address"]

                "\n--------------------------------\n" +
                        "Status: $status\nHTTP method: $httpMethod\nUser agent: $userAgent\nIP-Address: $ipAddress\n" +
                        "--------------------------------"
            }
        }
        module()
    }.start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}