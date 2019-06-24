package io.caffinatedsquirrel.synopsis

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
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
        CreateProjectCommand createProjectCommand = new CreateProjectCommand("My test project")

        when: 'The project is posted'
        HttpResponse<Project> postResponse = createProject(createProjectCommand)

        then: 'The project shall be created'
        postResponse.status == HttpStatus.CREATED
        HttpResponse<Project> getResponse = apiClient.getProject(postResponse.body().id)
        getResponse.body() == postResponse.body()
    }

    def 'Create a test'() {
        given: 'A project has been created'
        Project project = createProject().body()

        when: 'A test is posted'
        CreateTestCommand createTest = new CreateTestCommand(
                "Create test using the api sample test",
                "Given a project has been created\nWhen a test is posted\nThen the test shall be created",
                1
        )

        HttpResponse postResponse = apiClient.postTest(project.id, createTest)
        Test postTest = postResponse.body()

        then: 'The test shall be created'
        HttpResponse getResponse = apiClient.getTest(postTest.id)
        Test getTest = getResponse.body()
        postResponse.status == HttpStatus.CREATED
        getTest.title == createTest.title
        getTest.description == createTest.description
        getTest.latestVersion == createTest.latestVersion
    }
}
