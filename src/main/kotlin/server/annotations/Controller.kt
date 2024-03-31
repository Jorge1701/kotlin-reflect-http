package server.annotations

@Target(AnnotationTarget.CLASS)
annotation class Controller(val baseUrl: String = "")
