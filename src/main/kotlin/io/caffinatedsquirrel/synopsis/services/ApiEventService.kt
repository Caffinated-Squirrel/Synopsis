package io.caffinatedsquirrel.synopsis.services

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestRunCommand
import javax.inject.Singleton

@Singleton
class ApiEventService {

    fun createProject(projectCommand: CreateProjectCommand) {

    }

    fun createTest(projectId: Int, testCommand: CreateTestCommand) {

    }

    fun createTestRun(testId: Int, testRunCommand: CreateTestRunCommand) {

    }
}