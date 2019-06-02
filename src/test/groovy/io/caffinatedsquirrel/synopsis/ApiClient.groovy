package io.caffinatedsquirrel.synopsis

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestRunCommand
import io.caffinatedsquirrel.synopsis.controllers.ApiOperations
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

import javax.validation.constraints.NotNull

@Client("/api/v1")
interface ApiClient extends ApiOperations {

    @Override
    HttpResponse postProject(@NotNull @Body CreateProjectCommand createProjectCommand)

    @Override
    HttpResponse postTest(@PathVariable int projectId, @NotNull @Body CreateTestCommand createTestCommand)

    @Override
    HttpResponse postTestRun(@PathVariable int testId, @NotNull @Body CreateTestRunCommand createTestRunCommand)
}
