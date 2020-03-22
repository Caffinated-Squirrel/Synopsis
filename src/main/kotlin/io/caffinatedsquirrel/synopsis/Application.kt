package io.caffinatedsquirrel.synopsis

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("io.caffinatedsquirrel.synopsis")
                .mainClass(Application.javaClass)
                .start()
    }
}