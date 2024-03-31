package server

import server.Method

@Target(AnnotationTarget.FUNCTION)
annotation class Endpoint(val route: String = "", val method: Method = Method.GET)
