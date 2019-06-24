package io.caffinatedsquirrel.synopsis.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.caffinatedsquirrel.synopsis.model.Scenario
import io.caffinatedsquirrel.synopsis.services.ValidationResult
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

class TestEntity (
        @BsonId @JsonSerialize(using = ObjectIdSerializer::class)
        var id: ObjectId? = ObjectId(),
        var title: String,
        var description: String,
        var latestVersion: Int) {

    constructor(): this(null, "", "", 0)
}