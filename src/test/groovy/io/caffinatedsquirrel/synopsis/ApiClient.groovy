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
    HttpResponse getProject(Long projectId)

    @Get("/project{?projectName}")
    HttpResponse getProjectWhere(@QueryValue @Nullable Long projectId, @QueryValue @Nullable String projectName)

    @Post("/project/{projectId}/test")
    HttpResponse postTest(@PathVariable Long projectId, @NotNull @Body CreateTestCommand createTestCommand)

    @Get("/test/{testId}")
    HttpResponse getTest(Long testId)

    @Get("/test{?projectId}")
    HttpResponse getTestWhere(@QueryValue @Nullable Long projectId)

    @Post("/project/{projectId}/testsuite")
    HttpResponse postTestSuite(@PathVariable Long projectId, @Body CreateTestSuiteCommand createTestSuiteCommand)

    @Get("/testsuite/{testsuiteId}")
    HttpResponse getTestSuite(@PathVariable Long testsuiteId)

    @Post("/test/{testId}/testrun")
    HttpResponse postTestRun(@PathVariable String testId,
                             @NotNull @Body CreateTestRunCommand createTestRunCommand)
}
