package example

import server.Method.DELETE
import server.annotations.Controller
import server.annotations.Endpoint

@Controller(baseUrl = "/count")
class CounterController(private var i: Int = 0) {

    @Endpoint
    fun count(): String {
        return "Current: ${i++}"
    }

    @Endpoint(method = DELETE)
    fun deleteCount() {
        i = 0
    }
}
