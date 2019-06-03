package io.caffinatedsquirrel.synopsis.controllers

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestRunCommand
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

interface ApiOperations {

    @Post("/project")
    fun postProject(@Body createProjectCommand: CreateProjectCommand): HttpResponse<Any>

    @Post("/project/{projectId}/test")
    fun postTest(@PathVariable projectId: String, @Body createTestCommand: CreateTestCommand) : HttpResponse<Any>

    @Post("/test/{testId}/testrun")
    fun postTestRun(@PathVariable testId: String, @Body createTestRunCommand: CreateTestRunCommand): HttpResponse<Any>
}