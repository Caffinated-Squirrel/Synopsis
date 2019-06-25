package io.caffinatedsquirrel.synopsis.controllers

import io.caffinatedsquirrel.synopsis.commands.*
import io.caffinatedsquirrel.synopsis.domain.ProjectEntity
import io.caffinatedsquirrel.synopsis.domain.TestEntity
import io.caffinatedsquirrel.synopsis.services.ProjectDataService
import io.caffinatedsquirrel.synopsis.services.TestDataService
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.reactivex.Observable
import org.bson.types.ObjectId
import org.litote.kmongo.project
import org.slf4j.LoggerFactory
import javax.inject.Inject

@Controller("/api/v1")
@Suppress("UNCHECKED_CAST") // Suppress these warnings, if the cast fails then returning an error 500 is the correct response
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
                projectId = ObjectId(projectId),
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
            val project = testDataService.getTestById(testId)
            if (project != null) {
                HttpResponse.ok(project) as HttpResponse<Any>
            } else {
                HttpResponse.notFound(mapOf("error" to "Test with id $testId not found")) as HttpResponse<Any>
            }
        }
    }

    @Secured("isAuthenticated()")
    override fun getTestWhere(@QueryValue testId: String?, @QueryValue projectId: String?): Observable<HttpResponse<Any>> {
        val query = GetTestQuery(testId = testId, projectId = projectId)
        return if (query.isValid()) {
            Observable.fromCallable {
                HttpResponse.ok(testDataService.getTestByFilter(query)) as HttpResponse<Any>
            }
        } else {
            Observable.just(HttpResponse.badRequest(mapOf("error" to "Invalid filter criteria!")) as HttpResponse<Any>)
        }
    }

    @Secured("isAuthenticated()")
    override fun postTestSuite(projectId: String, createTestSuiteCommand: CreateTestSuiteCommand): Observable<HttpResponse<Any>> {
        return Observable.just(HttpResponse.created(mapOf("response" to "STUB!")) as HttpResponse<Any>)
    }

    @Secured("isAuthenticated()")
    override fun getTestSuite(testsuiteId: String): Observable<HttpResponse<Any>> {
        return Observable.just(HttpResponse.created(mapOf("response" to "STUB!")) as HttpResponse<Any>)
    }

    @Secured("isAuthenticated()")
    override fun postTestRun(@PathVariable testId: String, @PathVariable scenarioId: String,
                             @Body createTestRunCommand: CreateTestRunCommand): HttpResponse<Any> {
        return HttpResponse.created(mapOf("response" to "STUB!"))
    }

    @Error
    fun onError(request: HttpRequest<Any>): HttpResponse<Any> {
        if (LOG.isErrorEnabled) {
            LOG.error("Request to ${request.uri} failed! Body: ${request.body} ${request.headers.map { "${it.key}:${it.value}" }}")
        }

        return HttpResponse.serverError(mapOf("error" to "Error occurred processing the request. Please try again later."))
    }
}