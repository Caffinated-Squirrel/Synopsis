package io.caffinatedsquirrel.synopsis.model

class Scenario(val steps: List<TestStep>,
               val validConfigs: List<TestConfig>,
               val parameterList: TestParameterList?)