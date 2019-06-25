package io.caffinatedsquirrel.synopsis.commands

class GetTestQuery(val testId: String?,
                   val projectId: String?) {

    fun isValid(): Boolean {
        val testIdEmptyOrNull = testId?.isEmpty()
        val projectIdEmptyOrNull = projectId?.isEmpty()

        return testIdEmptyOrNull == false || projectIdEmptyOrNull == false
    }
}