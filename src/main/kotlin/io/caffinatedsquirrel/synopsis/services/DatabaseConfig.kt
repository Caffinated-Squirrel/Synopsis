package io.caffinatedsquirrel.synopsis.services

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("synopsis")
class DatabaseConfig {

    private var databaseName = "synopsis"

    fun getDatabaseName(): String {
        return databaseName
    }

    fun setDatabaseName(databaseName: String) {
        this.databaseName = databaseName
    }
}