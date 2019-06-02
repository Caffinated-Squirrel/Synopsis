package io.caffinatedsquirrel.synopsis.controllers

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestRunCommand
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.HttpStatus

@Controller("/api/v1")
class ApiController : ApiOperations {

    override fun index(): HttpStatus {
        return HttpStatus.OK
    }

    override fun postProject(@Body createProjectCommand: CreateProjectCommand): HttpResponse<Any> {
        return HttpResponse.created(mapOf("response" to "STUB!"))
    }

    override fun postTest(@PathVariable projectId: Int, @Body createTestCommand: CreateTestCommand) : HttpResponse<Any> {
        return HttpResponse.created(mapOf("response" to "STUB!"))
    }

    override fun postTestRun(@PathVariable testId: Int, @Body createTestRunCommand: CreateTestRunCommand): HttpResponse<Any> {
        return HttpResponse.created(mapOf("response" to "STUB!"))
    }
}