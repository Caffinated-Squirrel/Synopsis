package io.caffinatedsquirrel.synopsis.domain.hibernate

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "scenario")
class Scenario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long = -1

    @OneToMany(mappedBy = "scenario")
    @JsonIgnore
    var testSteps: Set<TestStep> = setOf()

    @Column(name = "name", nullable = false)
    var name: String = ""

    @Column(name = "is_parameterized", nullable = false)
    var isParameterized: Boolean = false
}