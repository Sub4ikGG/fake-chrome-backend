package ehea

private const val ERROR_TAG = "[Error]"
private const val INFO_TAG = "[Info]"
private const val SECURITY_TAG = "[Security]"

fun printError(error: String) {
    println(
        "\n" +
                "\t\t$ERROR_TAG: $error" +
                "\n"
    )
}

fun printInfo(info: String) {
    println(
        "\n" +
                "\t\t$INFO_TAG: $info" +
                "\n"
    )
}

fun printSecurity(security: String) {
    println(
        "\n" +
                "\t\t$SECURITY_TAG: $security" +
                "\n"
    )
}