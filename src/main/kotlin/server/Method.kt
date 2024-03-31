package server

enum class Method {
    GET,
    POST,
    PUT,
    PATCH,
    DELETE
}

fun mapMethod(m: Method): org.http4k.core.Method {
    return when (m) {
        Method.GET -> org.http4k.core.Method.GET
        Method.POST -> org.http4k.core.Method.POST
        Method.PUT -> org.http4k.core.Method.PUT
        Method.PATCH -> org.http4k.core.Method.PATCH
        Method.DELETE -> org.http4k.core.Method.DELETE
    }
}

