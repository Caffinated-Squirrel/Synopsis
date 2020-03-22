package io.caffinatedsquirrel.synopsis.services

import io.caffinatedsquirrel.synopsis.commands.GetTestQuery
import io.caffinatedsquirrel.synopsis.domain.hibernate.Test
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import javax.persistence.EntityManager

@Repository
abstract class TestRepository(private val entityManager: EntityManager): CrudRepository<Test, Long> {

    @Query("FROM Test t WHERE t.project.id = :projectId")
    abstract fun findByProjectId(projectId: Long): List<Test>
}