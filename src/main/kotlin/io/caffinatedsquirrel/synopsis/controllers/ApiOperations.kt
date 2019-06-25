package io.caffinatedsquirrel.synopsis.controllers

import io.caffinatedsquirrel.synopsis.commands.*
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.reactivex.Observable

interface ApiOperations {

    @Post("/project")
    fun postProject(@Body createProjectCommand: CreateProjectCommand): Observable<HttpResponse<Any>>

    @Get("/project/{projectId}")
    fun getProject(@PathVariable projectId: String): Observable<HttpResponse<Any>>

    @Post("/project/{projectId}/test")
    fun postTest(@PathVariable projectId: String, @Body createTestCommand: CreateTestCommand): Observable<HttpResponse<Any>>

    @Get("/test/{testId}")
    fun getTest(testId: String): Observable<HttpResponse<Any>>

    @Get("/test{?testId,projectId}")
    fun getTestWhere(@QueryValue testId: String?, @QueryValue projectId: String?): Observable<HttpResponse<Any>>

    @Post("/project/{projectId}/testsuite")
    fun postTestSuite(@PathVariable projectId: String, @Body createTestSuiteCommand: CreateTestSuiteCommand): Observable<HttpResponse<Any>>

    @Get("/testsuite/{testsuiteId}")
    fun getTestSuite(@PathVariable testsuiteId: String): Observable<HttpResponse<Any>>

    @Post("/test/{testId}/testrun")
    fun postTestRun(@PathVariable testId: String, @PathVariable scenarioId: String,
                    @Body createTestRunCommand: CreateTestRunCommand): HttpResponse<Any>
}