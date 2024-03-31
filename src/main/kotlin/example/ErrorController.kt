package example

import org.http4k.core.Response
import org.http4k.core.Status
import server.Endpoint
import server.Controller

@Controller
class ErrorController {

    @Endpoint(route = "/error")
    fun error() {
        throw Exception("Error")
    }

    @Endpoint(route = "/error-response")
    fun errorResponse(): Response {
        return Response(Status.INTERNAL_SERVER_ERROR).body("Error")
    }
}
