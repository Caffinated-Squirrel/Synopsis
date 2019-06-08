package io.caffinatedsquirrel.synopsis.commands

import io.caffinatedsquirrel.synopsis.model.Scenario

class CreateTestCommand(val title: String,
                        val description: String,
                        val version: Int,
                        val scenarios: List<Scenario>)