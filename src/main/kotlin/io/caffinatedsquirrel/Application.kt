package io.caffinatedsquirrel

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("io.caffinatedsquirrel")
                .mainClass(Application.javaClass)
                .start()
    }
}