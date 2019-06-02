package io.caffinatedsquirrel.synopsis.commands

import io.caffinatedsquirrel.synopsis.enums.TestResultType

class CreateTestRunCommand(val testVersion: Int,
                           val executedBy: String,
                           val result: TestResultType,
                           val startedAt: String,
                           val executionTime: Long,
                           val runConfig: String,
                           val stepResults: List<TestStepResult>,
                           val build: String)