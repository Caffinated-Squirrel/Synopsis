package io.caffinatedsquirrel.synopsis.domain.hibernate

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "test_step")
class TestStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long = -1

    @ManyToOne
    @JsonIgnore
    var scenario: Scenario? = null

    @Column(name = "action", nullable = false)
    var action: String = ""

    @Column(name = "expected", nullable = false)
    var expected: String = ""

    @Column(name = "actual", nullable = false)
    var actual: String = ""
}