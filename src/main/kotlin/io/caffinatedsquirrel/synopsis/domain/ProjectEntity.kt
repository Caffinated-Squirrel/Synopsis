package io.caffinatedsquirrel.synopsis.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

class ProjectEntity (
        @BsonId @JsonSerialize(using = ObjectIdSerializer::class)
        var id: ObjectId? = ObjectId(),
        var name: String) {

    constructor(): this(null, "")
}