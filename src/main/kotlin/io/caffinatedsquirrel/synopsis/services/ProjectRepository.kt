package io.caffinatedsquirrel.synopsis.services

import io.caffinatedsquirrel.synopsis.commands.GetProjectQuery
import io.caffinatedsquirrel.synopsis.domain.hibernate.Project
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import javax.persistence.EntityManager

@Repository
abstract class ProjectRepository(private val entityManager: EntityManager) : CrudRepository<Project, Long> {

    fun findByGetQuery(getQuery: GetProjectQuery): Project? {
        return if (getQuery.isValid()) {
            entityManager.createQuery("SELECT * FROM project WHERE name = :pname", Project::class.java)
                    .setParameter("pname", getQuery.projectName)
                    .singleResult
        } else {
            null
        }
    }
}