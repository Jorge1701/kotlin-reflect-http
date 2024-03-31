package server

@Target(AnnotationTarget.CLASS)
annotation class Controller(val baseUrl: String = "")
