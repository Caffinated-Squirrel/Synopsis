package io.caffinatedsquirrel.synopsis.commands

class GetProjectQuery(val projectName: String?) {

    fun isValid(): Boolean {
        return projectName != null && projectName.isNotEmpty()
    }
}