package io.caffinatedsquirrel.synopsis.controllers

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestRunCommand
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.reactivex.Observable

interface ApiOperations {

    @Post("/project")
    fun postProject(@Body createProjectCommand: CreateProjectCommand): Observable<HttpResponse<Any>>

    @Get("/project/{projectId}")
    fun getProject(@PathVariable projectId: String): Observable<HttpResponse<Any>>

    @Post("/project/{projectId}/test")
    fun postTest(@PathVariable projectId: String, @Body createTestCommand: CreateTestCommand) : Observable<HttpResponse<Any>>

    @Get("/test/{testId}")
    fun getTest(testId: String): Observable<HttpResponse<Any>>

    @Post("/test/{testId}/testrun")
    fun postTestRun(@PathVariable testId: String, @PathVariable scenarioId: String,
                    @Body createTestRunCommand: CreateTestRunCommand): HttpResponse<Any>
}