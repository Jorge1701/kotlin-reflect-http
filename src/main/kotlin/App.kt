import example.CounterController
import example.ErrorController
import example.HelloWorldController
import example.ObjectsController
import server.startServer

fun main() {
    startServer(
        HelloWorldController::class,
        CounterController::class,
        ObjectsController::class,
        ErrorController::class,
        port = 8080
    )
}
