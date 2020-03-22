//package io.caffinatedsquirrel.synopsis.services
//
//import com.mongodb.MongoClient
//import com.mongodb.client.model.Filters
//import io.caffinatedsquirrel.synopsis.commands.GetTestQuery
//import io.caffinatedsquirrel.synopsis.domain.TestEntity
//import org.bson.codecs.configuration.CodecRegistries.fromProviders
//import org.bson.codecs.configuration.CodecRegistries.fromRegistries
//import org.bson.codecs.pojo.PojoCodecProvider
//import org.bson.conversions.Bson
//import org.bson.types.ObjectId
//import org.litote.kmongo.findOne
//import org.litote.kmongo.findOneById
//import org.litote.kmongo.withDocumentClass
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class TestDataService {
//
//    @Inject
//    private lateinit var mongoClient: MongoClient
//
//    @Inject
//    private lateinit var databaseConfig: DatabaseConfig
//
//    private val db by lazy {
//        val codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
//                fromProviders(PojoCodecProvider
//                        .builder()
//                        .register(TestEntity::class.java)
//                        .build()))
//        mongoClient.getDatabase(databaseConfig.getDatabaseName()).withCodecRegistry(codecRegistry)
//    }
//
//    private val testEntityJavaClass = TestEntity::class.java
//
//    private val testCollectionName = "test"
//
//    fun writeTest(testEntity: TestEntity) {
//        db.getCollection(testCollectionName, testEntityJavaClass)
//                .insertOne(testEntity)
//    }
//
//    fun getTestById(testId: String): TestEntity? {
//        return db.getCollection(testCollectionName).withDocumentClass<TestEntity>()
//                .findOneById(ObjectId(testId))
//    }
//
//    fun getTestByFilter(testQuery: GetTestQuery): TestEntity {
//        val filter: Bson = when {
//            testQuery.projectId != null -> { Filters.eq("projectId", ObjectId(testQuery.projectId.toString())) }
//            else -> { throw Exception("Query must have at least one non-null value!") }
//        }
//
//        return db.getCollection(testCollectionName).withDocumentClass<TestEntity>()
//                .findOne(filter)!!
//    }
//}