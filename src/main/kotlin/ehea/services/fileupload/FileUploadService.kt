package ehea.services.fileupload

import java.io.File
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.writeBytes

class FileUploadService: IFileUploadService {

    private val imageDirectory = "images"

    override fun uploadScreenshot(ip: String, base64: String) {
        val pictureBytes = getByteArrayByBase64(base64)
        createDirectoryIfNotExists(ip)

        val path = createPath(ip)
        path.writeBytes(pictureBytes)
    }

    private fun createPath(ip: String) = Path("images/$ip/${System.currentTimeMillis() / 1000}.png")
    private fun getByteArrayByBase64(base64: String) = Base64.getDecoder().decode(base64)

    private fun createDirectoryIfNotExists(ip: String) {
        val directoryName = "$imageDirectory/$ip"
        val directory = File(directoryName)

        if (!directory.exists()) directory.mkdir()
    }

    override fun uploadLog(log: String) {
        TODO("Not yet implemented")
    }

}