package io.caffinatedsquirrel.synopsis.commands

import io.caffinatedsquirrel.synopsis.enums.TestResultType

class TestStepResult(val actual: String,
                     val result: TestResultType,
                     val executionTime: Long)