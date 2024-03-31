package server.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class Endpoint(val route: String = "")
