package io.caffinatedsquirrel.synopsis

import io.caffinatedsquirrel.synopsis.domain.hibernate.Project
import io.caffinatedsquirrel.synopsis.domain.hibernate.Test
import io.caffinatedsquirrel.synopsis.services.ProjectRepository
import io.caffinatedsquirrel.synopsis.services.TestRepository
import io.micronaut.context.annotation.Requires
import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.scheduling.annotation.Async
import org.slf4j.LoggerFactory
import javax.inject.Singleton


@Singleton
@Requires(env = ["demo"])
open class DemoDataLoader(private val projectRepository: ProjectRepository,
                          private val testRepository: TestRepository) {

    private val LOG = LoggerFactory.getLogger(DemoDataLoader::class.java)

    @EventListener
    @Async
    open fun onStartup(event: StartupEvent) {
        LOG.info("Creating demo data")
        val demoProject = Project(name = "Demo Project")
        val persistedDemoProject = projectRepository.save(demoProject)

        val demoTest = Test(
                title = "Demo Test",
                description ="Given the Synopsis server is running in demo mode\n" +
                        "When the demo project is selected\n" +
                        "Then this project shall be displayed",
                latestVersion = 1)
        // Use persistedDemoProject instead of demoProject, because it has been assigned an id
        demoTest.project = persistedDemoProject
        testRepository.save(demoTest)
    }
}