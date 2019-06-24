package io.caffinatedsquirrel.synopsis

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestRunCommand
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

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

    @Post("/test/{testId}/testrun")
    HttpResponse postTestRun(@PathVariable String testId, @PathVariable String scenarioId,
                             @NotNull @Body CreateTestRunCommand createTestRunCommand)
}
