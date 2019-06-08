package io.caffinatedsquirrel.synopsis

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.micronaut.http.HttpResponse

trait ApiDataCreationTrait {

    HttpResponse createProject(ApiClient apiClient = this.apiClient, CreateProjectCommand createProjectCommand =
                                       new CreateProjectCommand("My Test Project")) {
        apiClient.postProject(createProjectCommand)
    }
}
