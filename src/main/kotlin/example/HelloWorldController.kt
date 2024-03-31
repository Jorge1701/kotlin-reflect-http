package example

import server.annotations.Endpoint
import server.annotations.Controller

@Controller
class HelloWorldController {

    @Endpoint
    fun helloWorld(): String {
        return "Hello world!"
    }
}
