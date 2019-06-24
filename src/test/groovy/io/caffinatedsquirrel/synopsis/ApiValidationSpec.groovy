package io.caffinatedsquirrel.synopsis

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class ApiValidationSpec extends Specification implements ApiDataCreationTrait {

    @Inject
    EmbeddedServer embeddedServer

    @Inject
    ApiClient apiClient

//    def 'Create project - validation failures'() {
//        given: 'An invalid project'
//        // Handled by "where"
//
//        when: 'The project is posted'
//        apiClient.postProject(project)
//
//        then: 'A bad request (400) response shall be rendered'
//        HttpClientResponseException responseException = thrown HttpClientResponseException
//        responseException.status == HttpStatus.BAD_REQUEST
//
//        where:
//        validationError  | project
//        "Name too long"  | new CreateProjectCommand("a" * 51)
//        "Name too short" | new CreateProjectCommand("a")
//    }

    def 'Create project - validation boundaries'() {
        given: 'A valid project'

        when: 'The project is posted'
        HttpResponse response = apiClient.postProject(project)

        then: 'The project shall be created'
        response.status == HttpStatus.CREATED
        createProject()

        where:
        boundary          | project
        "Max eventType length" | new CreateProjectCommand("a" * 50)
        "Min eventType length" | new CreateProjectCommand("a" * 3)
    }
}
