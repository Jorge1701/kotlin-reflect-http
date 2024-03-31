package example

import server.Endpoint
import server.Controller

@Controller
class HelloWorldController {

    @Endpoint
    fun helloWorld(): String {
        return "Hello world!"
    }
}
