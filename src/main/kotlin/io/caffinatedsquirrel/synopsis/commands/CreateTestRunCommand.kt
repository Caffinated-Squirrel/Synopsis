package io.caffinatedsquirrel.synopsis.commands

import io.caffinatedsquirrel.synopsis.model.ResultType
import io.caffinatedsquirrel.synopsis.model.FailureInfo
import io.caffinatedsquirrel.synopsis.model.TestConfig
import io.caffinatedsquirrel.synopsis.model.TestStepResult

class CreateTestRunCommand(val testVersion: Int?,
                           val executedBy: String,
                           val result: ResultType,
                           val startedAt: String,
                           val executionTime: Long,
                           val runConfig: TestConfig,
                           val stepResults: List<TestStepResult>,
                           val build: String,
                           val failureInfo: FailureInfo?)