package io.caffinatedsquirrel.synopsis

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.commands.TestStep
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

    def 'Create a project'() {
        given: 'A user creates a project'
        CreateProjectCommand projectCommand = new CreateProjectCommand("My Test Project")

        when: 'The project is posted'
        HttpResponse response = apiClient.postProject(projectCommand)

        then: 'The project shall be created'
        // TODO: Get request
        response.status == HttpStatus.CREATED
    }

    def 'Create a test'() {
        given: 'A project has been created'
        CreateProjectCommand projectCommand = new CreateProjectCommand("My Test Project")
        def project = apiClient.postProject(projectCommand).body()

        when: 'A test is posted'
        CreateTestCommand createTest = new CreateTestCommand(
                "Create test using the api sample test",
                "Given a project has been created\nWhen a test is posted\nThen the test shall be created",
                [new TestStep("Create a project using the api", "The project is created"),
                 new TestStep("Create a test for the project", "The test is created")],
                [null])
        HttpResponse response = apiClient.postTest(1, createTest)

        then: 'The test shall be created'
        // TODO: Get request
        response.status == HttpStatus.CREATED
    }
}
