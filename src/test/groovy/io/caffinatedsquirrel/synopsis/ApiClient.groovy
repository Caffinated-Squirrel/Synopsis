package io.caffinatedsquirrel.synopsis

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestRunCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestSuiteCommand
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

import javax.annotation.Nullable
import javax.validation.constraints.NotNull

/**
 * Declarative api client
 */
@Client("/api/v1")
interface ApiClient {

    @Post("/project")
    HttpResponse postProject(@NotNull @Body CreateProjectCommand createProjectCommand)

    @Get("/project/{projectId}")
    HttpResponse getProject(@PathVariable String projectId)

    @Post("/project/{projectId}/test")
    HttpResponse postTest(@PathVariable String projectId, @NotNull @Body CreateTestCommand createTestCommand)

    @Get("/test/{testId}")
    HttpResponse getTest(String testId)

    @Get("/test{?testId,projectId}")
    HttpResponse getTestWhere(@QueryValue @Nullable String testId, @QueryValue @Nullable String projectId)

    @Post("/project/{projectId}/testsuite")
    HttpResponse postTestSuite(@PathVariable String projectId, @Body CreateTestSuiteCommand createTestSuiteCommand)

    @Get("/testsuite/{testsuiteId}")
    HttpResponse getTestSuite(@PathVariable String testsuiteId)

    @Post("/test/{testId}/testrun")
    HttpResponse postTestRun(@PathVariable String testId, @PathVariable String scenarioId,
                             @NotNull @Body CreateTestRunCommand createTestRunCommand)
}
