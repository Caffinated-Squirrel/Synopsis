package io.caffinatedsquirrel.synopsis

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class ApiControllerSpec extends Specification {

    @Inject
    EmbeddedServer embeddedServer

    @Inject
    ApiClient apiClient


    void "Get index"() {
        given:
        HttpStatus status = apiClient.index()

        expect:
        status == HttpStatus.OK
    }

    def 'Create a project'() {
        given: 'A user creates a project'
        CreateProjectCommand projectCommand = new CreateProjectCommand("My Test Project")

        when: 'The project is posted'
        HttpResponse response = apiClient.postProject(projectCommand)

        then: 'The project shall be created'
        // TODO: Get request
        response.status == HttpStatus.CREATED
    }
}
