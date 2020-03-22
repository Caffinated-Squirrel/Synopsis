package io.caffinatedsquirrel.synopsis.controllers

import io.caffinatedsquirrel.synopsis.commands.*
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.reactivex.Observable

interface ApiOperations {

    @Post("/project")
    @Secured("isAuthenticated()")
    fun postProject(@Body createProjectCommand: CreateProjectCommand): Observable<HttpResponse<*>>

    @Get("/project/{projectId}")
    @Secured("isAuthenticated()")
    fun getProject(projectId: Long): Observable<HttpResponse<*>>

    @Get("/project{?projectName}")
    @Secured("isAuthenticated()")
    fun getProjectWhere(@QueryValue projectName: String?): Observable<HttpResponse<*>>

    @Post("/project/{projectId}/test")
    @Secured("isAuthenticated()")
    fun postTest(@PathVariable projectId: Long, @Body createTestCommand: CreateTestCommand): Observable<HttpResponse<*>>

    @Get("/test/{testId}")
    @Secured("isAuthenticated()")
    fun getTest(testId: Long): Observable<HttpResponse<*>>

    @Get("/test{?projectId}")
    @Secured("isAuthenticated()")
    fun getTestWhere(@QueryValue projectId: Long?): Observable<HttpResponse<*>>

    @Post("/project/{projectId}/testsuite")
    @Secured("isAuthenticated()")
    fun postTestSuite(@PathVariable projectId: Long, @Body createTestSuiteCommand: CreateTestSuiteCommand): Observable<HttpResponse<*>>

    @Get("/testsuite/{testSuiteId}")
    @Secured("isAuthenticated()")
    fun getTestSuite(@PathVariable testSuiteId: Long): Observable<HttpResponse<*>>

    @Post("/test/{testId}/testrun")
    @Secured("isAuthenticated()")
    fun postTestRun(@PathVariable testId: Long,
                    @Body createTestRunCommand: CreateTestRunCommand): HttpResponse<*>
}