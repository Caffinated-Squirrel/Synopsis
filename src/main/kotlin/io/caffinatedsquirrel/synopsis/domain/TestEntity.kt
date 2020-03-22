//package io.caffinatedsquirrel.synopsis.domain
//
//import com.fasterxml.jackson.databind.annotation.JsonSerialize
//import org.bson.codecs.pojo.annotations.BsonId
//import org.bson.types.ObjectId
//
//class TestEntity (
//        @BsonId @JsonSerialize(using = ObjectIdSerializer::class)
//        var id: ObjectId? = ObjectId(),
//        var projectId: ObjectId,
//        var title: String,
//        var description: String,
//        var latestVersion: Int) {
//
//    constructor(): this(null, ObjectId(), "", "", 0)
//}