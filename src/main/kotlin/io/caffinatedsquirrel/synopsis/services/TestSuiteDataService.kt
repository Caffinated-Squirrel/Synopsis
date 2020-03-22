//package io.caffinatedsquirrel.synopsis.services
//
//import com.mongodb.MongoClient
//import io.caffinatedsquirrel.synopsis.domain.TestEntity
//import io.caffinatedsquirrel.synopsis.domain.TestSuiteEntity
//import org.bson.codecs.configuration.CodecRegistries.fromProviders
//import org.bson.codecs.configuration.CodecRegistries.fromRegistries
//import org.bson.codecs.pojo.PojoCodecProvider
//import org.bson.types.ObjectId
//import org.litote.kmongo.findOneById
//import org.litote.kmongo.withDocumentClass
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class TestSuiteDataService {
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
//                        .register(TestSuiteEntity::class.java)
//                        .build()))
//        mongoClient.getDatabase(databaseConfig.getDatabaseName()).withCodecRegistry(codecRegistry)
//    }
//
//    private val testSuiteEntityJavaClass = TestSuiteEntity::class.java
//
//    private val testSuiteCollectionName = "testsuite"
//
//    fun writeTestSuite(testSuite: TestSuiteEntity) {
//        db.getCollection(testSuiteCollectionName, testSuiteEntityJavaClass)
//                .insertOne(testSuite)
//    }
//
//    fun getTestSuiteById(testId: String): TestEntity? {
//        return db.getCollection(testSuiteCollectionName).withDocumentClass<TestEntity>()
//                .findOneById(ObjectId(testId))
//    }
//}