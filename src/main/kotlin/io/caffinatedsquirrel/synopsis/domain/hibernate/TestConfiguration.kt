package io.caffinatedsquirrel.synopsis.domain.hibernate

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "testconfiguration")
class TestConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long = -1

    @OneToMany(mappedBy = "configuration")
    @JsonIgnore
    var testruns: Set<TestRun> = setOf()

    @Column(name = "primary_platform", nullable = false, length = 50)
    lateinit var primaryPlatform: String

    @Column(name = "primary_platform_version", nullable = false, length = 50)
    lateinit var primaryPlatformVersion: String

    @Column(name = "secondary_platform", nullable = true, length = 50)
    var secondaryPlatorm: String? = null

    @Column(name = "secondary_platform_version", nullable = true, length = 50)
    var secondaryPlatformVersion: String? = null

    constructor()

    constructor(primaryPlatform: String, primaryPlatformVersion: String) {
        this.primaryPlatform = primaryPlatform
        this.primaryPlatformVersion = primaryPlatformVersion
    }
}