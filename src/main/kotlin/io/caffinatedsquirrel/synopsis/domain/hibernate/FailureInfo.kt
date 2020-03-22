package io.caffinatedsquirrel.synopsis.domain.hibernate

import javax.persistence.*

@Entity
@Table(name = "failureinfo")
class FailureInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long = -1

    @OneToOne
    lateinit var testrun: TestRun

    @Column(name = "stacktrace", nullable = false)
    var stacktrace: String = ""

    constructor()

    constructor(stacktrace: String) {
        this.stacktrace = stacktrace
    }
}