package io.caffinatedsquirrel.synopsis.controllers

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestRunCommand
import io.caffinatedsquirrel.synopsis.domain.ProjectEntity
import io.caffinatedsquirrel.synopsis.domain.TestEntity
import io.caffinatedsquirrel.synopsis.services.ProjectDataService
import io.caffinatedsquirrel.synopsis.services.TestDataService
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.PathVariable
import io.micronaut.security.annotation.Secured
import io.reactivex.Observable
import org.slf4j.LoggerFactory
import javax.inject.Inject

@Controller("/api/v1")
class ApiController : ApiOperations {

    @Inject
    lateinit var projectDataService: ProjectDataService

    @Inject
    lateinit var testDataService: TestDataService

    private val LOG = LoggerFactory.getLogger(ApiController::class.java)

    @Secured("isAuthenticated()")
    override fun postProject(@Body createProjectCommand: CreateProjectCommand): Observable<HttpResponse<Any>> {
        val project = ProjectEntity(name = createProjectCommand.name)
        return Observable.fromCallable {
            projectDataService.writeProject(project)
            HttpResponse.created(project) as HttpResponse<Any>
        }
    }

    @Secured("isAuthenticated()")
    override fun getProject(projectId: String): Observable<HttpResponse<Any>> {
        return Observable.fromCallable {
            val project = projectDataService.getProject(projectId)
            HttpResponse.ok(project) as HttpResponse<Any>
        }
    }

    @Secured("isAuthenticated()")
    override fun postTest(@PathVariable projectId: String, @Body createTestCommand: CreateTestCommand): Observable<HttpResponse<Any>> {
        val test = TestEntity(
                title = createTestCommand.title,
                description = createTestCommand.description,
                latestVersion = createTestCommand.latestVersion)
        return Observable.fromCallable {
            testDataService.writeTest(test)
            HttpResponse.created(test) as HttpResponse<Any>
        }
    }

    @Secured("isAuthenticated()")
    override fun getTest(testId: String): Observable<HttpResponse<Any>> {
        return Observable.fromCallable {
            val project = testDataService.getTest(testId)
            HttpResponse.ok(project) as HttpResponse<Any>
        }
    }

    @Secured("isAuthenticated()")
    override fun postTestRun(@PathVariable testId: String, @PathVariable scenarioId: String,
                             @Body createTestRunCommand: CreateTestRunCommand): HttpResponse<Any> {
        return HttpResponse.created(mapOf("response" to "STUB!"))
    }

    @Error
    fun onError(request: HttpRequest<Any>): HttpResponse<Any> {
        if (LOG.isErrorEnabled) {
            LOG.error("Request to ${request.uri} failed! Body: ${request.body} ${request.headers.map {"${it.key}:${it.value}"}}")
        }

        return HttpResponse.serverError(mapOf("error" to "Error occurred processing the request. Please try again later."))
    }
}