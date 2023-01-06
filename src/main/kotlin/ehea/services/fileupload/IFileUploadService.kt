package ehea.services.fileupload

interface IFileUploadService {

    fun uploadScreenshot(ip: String, base64: String)
    fun uploadLog(log: String)

}