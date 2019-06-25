package io.caffinatedsquirrel.synopsis.commands

class CreateTestSuiteCommand(val title: String,
                             val description: String,
                             val tests: List<String>)