package io.caffinatedsquirrel.synopsis.controllers

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestRunCommand
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*

interface ApiOperations {

    @Get("/")
    fun index(): HttpStatus

    @Post("/project")
    fun postProject(@Body createProjectCommand: CreateProjectCommand): HttpResponse<Any>

    @Post("/project/{projectId}/test")
    fun postTest(@PathVariable projectId: Int, @Body createTestCommand: CreateTestCommand) : HttpResponse<Any>

    @Post("/test/{testId}/testrun")
    fun postTestRun(@PathVariable testId: Int, @Body createTestRunCommand: CreateTestRunCommand): HttpResponse<Any>
}