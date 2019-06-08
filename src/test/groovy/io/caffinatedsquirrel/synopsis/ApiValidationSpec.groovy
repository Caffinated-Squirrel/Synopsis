package io.caffinatedsquirrel.synopsis

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.model.Scenario
import io.caffinatedsquirrel.synopsis.model.TestConfig
import io.caffinatedsquirrel.synopsis.model.TestStep
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

    def 'Create project - validation failures'() {
        given: 'An invalid project'
        // Handled by "where"

        when: 'The project is posted'
        apiClient.postProject(project)

        then: 'A bad request (400) response shall be rendered'
        HttpClientResponseException responseException = thrown HttpClientResponseException
        responseException.status == HttpStatus.BAD_REQUEST

        where:
        validationError  | project
        "Name too long"  | new CreateProjectCommand("a" * 51)
        "Name too short" | new CreateProjectCommand("a")
    }

    def 'Create project - validation boundaries'() {
        given: 'A valid project'

        when: 'The project is posted'
        HttpResponse response = apiClient.postProject(project)

        then: 'The project shall be created'
        response.status == HttpStatus.CREATED
        !response.body.isEmpty()

        where:
        boundary | project
        "Max name length" | new CreateProjectCommand("a" * 50)
        "Min name length" | new CreateProjectCommand("a" * 3)
    }

    def 'Create test - version less than 0'() {
        given: 'A project has been created'
        createProject()

        when: 'A test with a version less than 0 is posted'
        // TODO: Create project should return the id, use that for this request. For now the project id isn't validated for this request.
        apiClient.postTest("1", new CreateTestCommand(
                "Create test using the api sample test",
                "Given a project has been created\nWhen a test with a version less than 0 is posted\nThen the test shall be rejected by the api",
                -1,
                [new Scenario(
                        [new TestStep("Create a project using the api", "The project is created"),
                         new TestStep("Post a test with a version less than 0", "The request is rejected with an error code")],
                        [new TestConfig("Windows", "10", "Chrome", "55")],
                        null)]
        ))

        then: 'The test shall be rejected by the api'
        HttpClientResponseException responseException = thrown HttpClientResponseException
        responseException.status == HttpStatus.BAD_REQUEST
    }
}
