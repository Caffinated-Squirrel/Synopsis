package io.caffinatedsquirrel.synopsis.controllers

import io.caffinatedsquirrel.synopsis.commands.*
import io.caffinatedsquirrel.synopsis.domain.hibernate.Project
import io.caffinatedsquirrel.synopsis.domain.hibernate.Test
import io.caffinatedsquirrel.synopsis.services.ProjectRepository
import io.caffinatedsquirrel.synopsis.services.TestRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.reactivex.Observable

@Controller("/api/v1")
class ApiController(private val projectRepository: ProjectRepository,
                    private val testRepository: TestRepository) : ApiOperations {

    override fun postProject(@Body createProjectCommand: CreateProjectCommand): Observable<HttpResponse<*>> {
        return Observable.fromCallable {
            val createdProject = projectRepository.save(Project(createProjectCommand.name))
            HttpResponse.created(createdProject) as HttpResponse<*>
        }
    }

    override fun getProject(projectId: Long): Observable<HttpResponse<*>> {
        return Observable.fromCallable {
            val project = projectRepository.findById(projectId)
            HttpResponse.ok(project) as HttpResponse<*>
        }
    }

    override fun getProjectWhere(@QueryValue projectName: String?): Observable<HttpResponse<*>> {
        val query = GetProjectQuery(projectName = projectName)
        return if (query.isValid()) {
            Observable.fromCallable {
                val project = projectRepository.findByGetQuery(query)
                HttpResponse.ok(project) as HttpResponse<*>
            }
        } else {
            Observable.just(HttpResponse.badRequest(mapOf("error" to "Invalid query!")) as HttpResponse<*>)
        }
    }

    override fun postTest(@PathVariable projectId: Long, @Body createTestCommand: CreateTestCommand): Observable<HttpResponse<*>> {
        val test = Test(
                title = createTestCommand.title,
                description = createTestCommand.description,
                latestVersion = createTestCommand.latestVersion)

        return Observable.fromCallable {
            val optionalProject = projectRepository.findById(projectId)

            if (optionalProject.isPresent) {
                test.project = optionalProject.get()
                val createdTest = testRepository.save(test)
                HttpResponse.created(createdTest) as HttpResponse<*>
            } else {
                HttpResponse.badRequest(mapOf("error" to "No project found with id $projectId"))
            }
        }
    }

    override fun getTest(testId: Long): Observable<HttpResponse<*>> {
        return Observable.fromCallable {
            val test = testRepository.findById(testId)
            if (test.isPresent) {
                HttpResponse.ok(test) as HttpResponse<*>
            } else {
                HttpResponse.notFound(mapOf("error" to "Test with id $testId not found")) as HttpResponse<*>
            }
        }
    }

    override fun getTestWhere(@QueryValue projectId: Long?): Observable<HttpResponse<*>> {
        val query = GetTestQuery(projectId = projectId)
        return if (query.isValid()) {
            Observable.fromCallable {
                HttpResponse.ok(testRepository.findByProjectId(query.projectId!!)) as HttpResponse<*>
            }
        } else {
            Observable.just(HttpResponse.badRequest(mapOf("error" to "Invalid query! Use a valid test id or project id.")) as HttpResponse<*>)
        }
    }

    override fun postTestSuite(projectId: Long, createTestSuiteCommand: CreateTestSuiteCommand): Observable<HttpResponse<*>> {
//        val testSuite = TestSuiteEntity(
//                projectId = "",
//                title = createTestSuiteCommand.title,
//                description = createTestSuiteCommand.description,
//                tests = createTestSuiteCommand.tests.map { ObjectId(it) })
//        return Observable.fromCallable {
//            testSuiteDataService.writeTestSuite(testSuite)
//            HttpResponse.created(testSuite) as HttpResponse<*>
//        }
        return Observable.just(HttpResponse.created(mapOf("dummy" to "response (TODO)")))
    }

    override fun getTestSuite(testSuiteId: Long): Observable<HttpResponse<*>> {
//        return Observable.fromCallable {
//            val testSuite = testSuiteDataService.getTestSuiteById("")
//            if (testSuite == null) {
//                HttpResponse.notFound(mapOf("error" to "Test suite with id $testSuiteId not found")) as HttpResponse<*>
//            } else {
//                HttpResponse.ok(testSuite) as HttpResponse<*>
//            }
//        }
        return Observable.just(HttpResponse.created(mapOf("dummy" to "response (TODO)")))
    }

    override fun postTestRun(@PathVariable testId: Long,
                             @Body createTestRunCommand: CreateTestRunCommand): HttpResponse<*> {
        return HttpResponse.created(mapOf("response" to "STUB!"))
    }
}