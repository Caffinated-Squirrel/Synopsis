package io.caffinatedsquirrel.synopsis.commands

class CreateTestCommand(val title: String,
                        val description: String,
                        val steps: List<String>,
                        val validConfigs: List<Any>)