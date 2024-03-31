package server

import com.google.gson.Gson
import org.http4k.core.*
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotations
import kotlin.reflect.full.functions

val JSON = Gson()

fun handleError(e: Exception): Response {
    val message = e.cause?.message

    return if (message != null) {
        Response(Status.INTERNAL_SERVER_ERROR).body(message)
    } else {
        Response(Status.INTERNAL_SERVER_ERROR)
    }
}

fun handleResult(result: Any?): Response {
    return when (result) {
        is Response -> result
        is Unit -> Response(Status.OK)
        is String -> Response(Status.OK).body(result)
        else -> {
            try {
                Response(Status.OK).body(JSON.toJson(result))
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }
}

fun createHandler(method: Method, route: String, controllerInstance: Any, function: KFunction<*>): RoutingHttpHandler {
    return route bind mapMethod(method) to {
        println("Handling $method to '$route'")

        val response = try {
            val result = function.call(controllerInstance)
            handleResult(result)
        } catch (e: Exception) {
            handleError(e)
        }

        response.header("Content-Type", "application/json")
    }
}

fun generateHandlers(vararg classes: KClass<*>): HttpHandler {
    return routes(classes.flatMap { controller ->
        val controllerAnnotation = controller.findAnnotations(Controller::class).firstOrNull()
            ?: throw Exception("Class $controller does not have @Controller annotation")

        println("Controller $controller")
        val controllerInstance = controller.createInstance()

        controller.functions.map { function ->
            val endpointAnnotation = function.findAnnotations(Endpoint::class).firstOrNull()

            if (endpointAnnotation == null) null
            else {
                val route = (controllerAnnotation.baseUrl + endpointAnnotation.route).ifBlank { "/" }

                println("Route ${endpointAnnotation.method} '$route'")

                createHandler(endpointAnnotation.method, route, controllerInstance, function)
            }
        }
    }.filterNotNull())
}

fun startServer(vararg classes: KClass<*>, port: Int) {
    println("Starting server on port $port ...")
    generateHandlers(*classes).asServer(SunHttp(port)).start()
}
