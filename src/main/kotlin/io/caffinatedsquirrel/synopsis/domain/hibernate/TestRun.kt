package io.caffinatedsquirrel.synopsis.domain.hibernate

import com.fasterxml.jackson.annotation.JsonIgnore
import io.caffinatedsquirrel.synopsis.model.ExecutionMode
import io.caffinatedsquirrel.synopsis.model.ResultType
import javax.persistence.*

@Entity
@Table(name = "testrun")
class TestRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long = -1

    @ManyToOne
    @JsonIgnore
    var test: Test? = null

    @ManyToOne
    @JsonIgnore
    var configuration: TestConfiguration? = null

    @OneToOne(optional = true)
    @JsonIgnore
    lateinit var failureInfo: FailureInfo

    @Column(name = "result", nullable = false)
    lateinit var result: ResultType

    @Column(name = "execution_mode", nullable = false)
    lateinit var executionMode: ExecutionMode

    @Column(name = "executed_at", nullable = false)
    var executedAt: Long = -1

    @Column(name = "duration", nullable = true)
    var duration: Long? = null

    constructor()

    constructor(result: ResultType, executedAt: Long, duration: Long?) {
        this.result = result
        this.executedAt = executedAt
        this.duration = duration
    }
}