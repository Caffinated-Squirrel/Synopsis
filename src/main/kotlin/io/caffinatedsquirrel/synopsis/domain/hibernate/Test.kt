package io.caffinatedsquirrel.synopsis.domain.hibernate

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "test")
class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long = -1

    @ManyToOne
    @JsonIgnore
    var project: Project? = null

    @OneToMany(mappedBy = "test")
    @JsonIgnore
    var testruns: Set<TestRun> = setOf()

    @Column(name = "title", nullable = false, length = 50)
    lateinit var title: String

    @Column(name = "description", nullable = false)
    @Type(type = "text")
    lateinit var description: String

    @Column(name = "latest_version", nullable = true)
    var latestVersion: Int? = null

    constructor()

    constructor(title: String, description: String, latestVersion: Int) {
        this.title = title
        this.description = description
        this.latestVersion = latestVersion
    }
}