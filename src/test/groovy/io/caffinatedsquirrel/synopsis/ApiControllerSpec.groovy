package io.caffinatedsquirrel.synopsis

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.model.Scenario
import io.caffinatedsquirrel.synopsis.model.TestConfig
import io.caffinatedsquirrel.synopsis.model.TestParameterList
import io.caffinatedsquirrel.synopsis.model.TestStep
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class ApiControllerSpec extends Specification implements ApiDataCreationTrait {

    @Inject
    EmbeddedServer embeddedServer

    @Inject
    ApiClient apiClient

    def 'Create a project'() {
        given: 'A user creates a project'
        CreateProjectCommand createProjectCommand = new CreateProjectCommand("My Test Project")

        when: 'The project is posted'
        HttpResponse response = createProject(apiClient, createProjectCommand)

        then: 'The project shall be created'
        // TODO: Get request
        response.status == HttpStatus.CREATED
    }

    def 'Create a test'() {
        given: 'A project has been created'
        createProject()

        when: 'A test is posted'
        CreateTestCommand createTest = new CreateTestCommand(
                "Create test using the api sample test",
                "Given a project has been created\nWhen a test is posted\nThen the test shall be created",
                1,
                [new Scenario(
                        [new TestStep("Create a project using the api", "The project is created"),
                         new TestStep("Create a test for the project", "The test is created")],
                        [new TestConfig("Windows", "10", "Chrome", "55")],
                        null)]
        )
        // TODO: Create project should return the id, use that for this request. For now the project id isn't validated for this request.
        HttpResponse response = apiClient.postTest("1", createTest)

        then: 'The test shall be created'
        // TODO: Get request
        response.status == HttpStatus.CREATED
    }

    def 'Create a parameterized test'() {
        given: 'A project has been created'
        CreateProjectCommand projectCommand = new CreateProjectCommand("My Test Project")
        def project = apiClient.postProject(projectCommand).body()

        when: 'A test is posted'
        CreateTestCommand createTest = new CreateTestCommand(
                "Create test using the api sample test",
                "Given a project with name \$name\nWhen a project is created\nThen a project named \$name shall be created",
                1,
                [new Scenario(
                        [new TestStep("Create a project named \$name", "The project is created")],
                        [new TestConfig("Windows", "10", "Chrome", "55")],
                        new TestParameterList(["projectName": ["Hello", "World"]]))]
        )
        // TODO: Create project should return the id, use that for this request. For now the project id isn't validated for this request.
        HttpResponse response = apiClient.postTest("1", createTest)

        then: 'The test shall be created'
        // TODO: Get request
        response.status == HttpStatus.CREATED
    }
}
