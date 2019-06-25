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

    def 'Get test with search criteria'() {
        given: 'A project with tests has been created'
        Project project = createProject().body()
        CreateTestCommand createTest1 = new CreateTestCommand("Get test with search criteria",
                "Verify the api returns the correct test when a filter is specified", 1)
        CreateTestCommand createTest2 = new CreateTestCommand("Ducks quack",
                "Verify the duck quacks", 1)
        Test test1 = apiClient.postTest(project.id, createTest1).body()
        Test test2 = apiClient.postTest(project.id, createTest2).body()

        when: 'The test is searched for using #searchType'
        Test testFromFilter
        switch(searchType) {
            case 'projectId':
                testFromFilter = apiClient.getTestWhere(null, project.id).body()
                break
            case 'testId':
                testFromFilter = apiClient.getTestWhere(test1.id, null).body()
                break
            default:
                throw new Exception("$searchType is not defined!")
        }

        then: 'The test shall be returned'
        testFromFilter.id == test1.id
        testFromFilter.title == test1.title
        testFromFilter.description == test1.description
        testFromFilter.latestVersion == test1.latestVersion

        where:
        searchType << ["projectId", "testId"]
    }
}
