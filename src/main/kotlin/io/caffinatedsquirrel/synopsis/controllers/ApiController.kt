package io.caffinatedsquirrel.synopsis.controllers

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestRunCommand
import io.caffinatedsquirrel.synopsis.services.ApiValidatorService
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import javax.inject.Inject

@Controller("/api/v1")
class ApiController : ApiOperations {

    @Inject
    lateinit var apiValidatorService: ApiValidatorService

    override fun index(): HttpStatus {
        return HttpStatus.OK
    }

    override fun postProject(@Body createProjectCommand: CreateProjectCommand): HttpResponse<Any> {
        val validation = apiValidatorService.validateProject(createProjectCommand)
        if (validation.hasError) {
            return HttpResponse.badRequest(mapOf("hasError" to validation.message))
        }

        return HttpResponse.created(mapOf("response" to "STUB!"))
    }

    override fun postTest(@PathVariable projectId: Int, @Body createTestCommand: CreateTestCommand) : HttpResponse<Any> {
        return HttpResponse.created(mapOf("response" to "STUB!"))
    }

    override fun postTestRun(@PathVariable testId: Int, @Body createTestRunCommand: CreateTestRunCommand): HttpResponse<Any> {
        return HttpResponse.created(mapOf("response" to "STUB!"))
    }
}