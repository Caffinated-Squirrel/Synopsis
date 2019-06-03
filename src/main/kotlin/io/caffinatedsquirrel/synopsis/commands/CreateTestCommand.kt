package io.caffinatedsquirrel.synopsis.commands

import io.caffinatedsquirrel.synopsis.model.TestConfig
import io.caffinatedsquirrel.synopsis.model.TestParameterList
import io.caffinatedsquirrel.synopsis.model.TestStep

class CreateTestCommand(val title: String,
                        val description: String,
                        val version: Int,
                        val steps: List<TestStep>,
                        val validConfigs: List<TestConfig>,
                        val parameterList: TestParameterList?)