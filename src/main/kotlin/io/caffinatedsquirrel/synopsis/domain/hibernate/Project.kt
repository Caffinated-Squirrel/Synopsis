package io.caffinatedsquirrel.synopsis.domain.hibernate

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "project")
class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long = -1

    @Column(name = "name", nullable = false, unique = true, length = 50)
    var name: String? = null

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    var tests: Set<Test> = setOf()

    constructor()

    constructor(name: String) {
        this.name = name
    }
}