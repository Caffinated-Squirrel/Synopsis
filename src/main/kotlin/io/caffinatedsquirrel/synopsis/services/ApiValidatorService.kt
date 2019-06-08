package io.caffinatedsquirrel.synopsis.services

import io.caffinatedsquirrel.synopsis.commands.CreateProjectCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestCommand
import io.caffinatedsquirrel.synopsis.commands.CreateTestRunCommand
import javax.inject.Singleton

@Singleton
class ApiValidatorService {

    fun validateProject(projectCommand: CreateProjectCommand) : ValidatorResult {
        val projectNameLengthLimit = 50
        val projectNameLengthMinimum = 3

        return when {
            projectCommand.name.length > projectNameLengthLimit -> {
                ValidatorResult(true,
                        "Project name ${projectCommand.name} is too long. Must be $projectNameLengthLimit characters or less.")
            }
            projectCommand.name.length < projectNameLengthMinimum -> {
                ValidatorResult(true,
                        "Project name ${projectCommand.name} is too short. Must be $projectNameLengthMinimum characters or longer.")
            }
            else -> {
                ValidatorResult(false, "")
            }
        }
    }

    fun validateTest(projectId: String, testCommand: CreateTestCommand) : ValidatorResult {
        if (testCommand.version < 0) {
            return ValidatorResult(true, "Test version must be greater than or equal to 0.")
        }

        return ValidatorResult(false, "")
    }

    fun validateTestRun(testId: Int, testRunCommand: CreateTestRunCommand) : ValidatorResult {
        return ValidatorResult(false, "")
    }
}