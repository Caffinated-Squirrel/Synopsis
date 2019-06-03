package io.caffinatedsquirrel.synopsis.controllers

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestRunCommand
import io.caffinatedsquirrel.synopsis.services.ApiValidatorService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.security.annotation.Secured
import javax.inject.Inject

@Controller("/api/v1")
class ApiController : ApiOperations {

    @Inject
    lateinit var apiValidatorService: ApiValidatorService

    @Secured("isAuthenticated()")
    override fun postProject(@Body createProjectCommand: CreateProjectCommand): HttpResponse<Any> {
        val validation = apiValidatorService.validateProject(createProjectCommand)
        if (validation.hasError) {
            return HttpResponse.badRequest(mapOf("error" to validation.message))
        }

        return HttpResponse.created(mapOf("response" to "STUB!"))
    }

    @Secured("isAuthenticated()")
    override fun postTest(@PathVariable projectId: String, @Body createTestCommand: CreateTestCommand) : HttpResponse<Any> {
        return HttpResponse.created(mapOf("response" to "STUB!"))
    }

    @Secured("isAuthenticated()")
    override fun postTestRun(@PathVariable testId: String, @Body createTestRunCommand: CreateTestRunCommand): HttpResponse<Any> {
        return HttpResponse.created(mapOf("response" to "STUB!"))
    }
}