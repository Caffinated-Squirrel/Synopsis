package io.caffinatedsquirrel.synopsis.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

class TestSuiteEntity (
        @BsonId @JsonSerialize(using = ObjectIdSerializer::class)
        var id: ObjectId? = ObjectId(),
        var projectId: String,
        var title: String,
        var description: String,
        var tests: List<ObjectId>) {

    constructor(): this(null, "", "", "", listOf())
}