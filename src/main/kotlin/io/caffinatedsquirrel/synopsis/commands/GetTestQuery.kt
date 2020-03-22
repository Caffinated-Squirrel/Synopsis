package io.caffinatedsquirrel.synopsis.commands

class GetTestQuery(val projectId: Long?) {

    fun isValid(): Boolean {
        return projectId != null && projectId > 0
    }
}