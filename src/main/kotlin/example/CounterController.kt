package example

import server.annotations.Controller
import server.annotations.Endpoint

@Controller
class CounterController(var i: Int = 0) {

    @Endpoint(route = "/count")
    fun count(): String {
        return "Current: ${i++}"
    }
}
