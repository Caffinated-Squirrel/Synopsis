package io.caffinatedsquirrel.synopsis.model

import io.caffinatedsquirrel.synopsis.enums.TestResultType

class TestStepResult(val actual: String,
                     val result: TestResultType,
                     val executionTime: Long)