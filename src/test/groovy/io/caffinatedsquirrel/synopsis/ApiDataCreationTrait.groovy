package io.caffinatedsquirrel.synopsis

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.micronaut.http.HttpResponse

trait ApiDataCreationTrait {

    HttpResponse<Object> createProject(CreateProjectCommand createProjectCommand =
                                       new CreateProjectCommand("My test project")) {
        ApiClient apiClient = this.apiClient
        apiClient.postProject(createProjectCommand)
    }
}
