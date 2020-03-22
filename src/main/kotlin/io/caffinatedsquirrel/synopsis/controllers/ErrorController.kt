package io.caffinatedsquirrel.synopsis.controllers

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import org.slf4j.LoggerFactory

@Controller
class ErrorController {

    private val LOG = LoggerFactory.getLogger(ErrorController::class.java)

    @Error(global = true, exception = Exception::class)
    fun onError(request: HttpRequest<Any>, ex: Exception): HttpResponse<Any> {
        if (LOG.isErrorEnabled) {
            LOG.error("Request to ${request.uri} failed! Body: ${request.body} ${request.headers.map { "${it.key}:${it.value}" }}")
            ex.printStackTrace()
        }

        return HttpResponse.serverError(mapOf("error" to "Error occurred processing the request. Please try again later."))
    }
}