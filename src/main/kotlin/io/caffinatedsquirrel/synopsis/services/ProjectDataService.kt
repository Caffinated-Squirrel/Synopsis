//package io.caffinatedsquirrel.synopsis.services
//
//import com.mongodb.MongoClient
//import com.mongodb.client.model.CreateCollectionOptions
//import com.mongodb.client.model.Filters
//import com.mongodb.client.model.ValidationOptions
//import io.caffinatedsquirrel.synopsis.domain.ProjectEntity
//import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
//import org.bson.BsonArray
//import org.bson.BsonDocument
//import org.bson.BsonInt32
//import org.bson.BsonString
//import org.bson.codecs.configuration.CodecRegistries.fromProviders
//import org.bson.codecs.configuration.CodecRegistries.fromRegistries
//import org.bson.codecs.pojo.PojoCodecProvider
//import org.bson.types.ObjectId
//import org.litote.kmongo.findOneById
//import org.litote.kmongo.withDocumentClass
//import javax.inject.Inject
//import javax.inject.Singleton
//import javax.persistence.EntityManager
//import javax.persistence.PersistenceContext
//
//@Singleton
//open class ProjectDataService {
//
//    @Inject
//    private lateinit var mongoClient: MongoClient
//
//    @Inject
//    private lateinit var databaseConfig: DatabaseConfig
//
//    @Inject
//    @PersistenceContext
//    @CurrentSession
//    private lateinit var entityManager: EntityManager
//
//    private val db by lazy {
//        val codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
//                fromProviders(PojoCodecProvider
//                        .builder()
//                        .register(ProjectEntity::class.java)
//                        .build()))
//        mongoClient.getDatabase(databaseConfig.getDatabaseName()).withCodecRegistry(codecRegistry)
//    }
//
//    private val projectEntityJavaClass = ProjectEntity::class.java
//
//    private val projectCollectionName = "project"
//
//    fun createCollectionWithValidator() {
//        val bsonType = BsonString("object")
//        val requiredList = BsonArray(listOf(BsonString("name")))
//        val properties = BsonDocument().append("name", BsonDocument()
//                .append("bsonType", BsonString("string"))
//                .append("minLength", BsonInt32(3))
//                .append("maxLength", BsonInt32(50))
//                .append("description", BsonString("Name is required and must be 3 to 50 characters long.")))
//        val validatorDocument = BsonDocument()
//                .append("bsonType", bsonType)
//                .append("required", requiredList)
//                .append("properties", properties)
//
//        val validator = ValidationOptions().validator(Filters.jsonSchema(validatorDocument))
//        val collOptions = ValidationOptions().validator(
//                Filters.jsonSchema(BsonDocument.parse("""{
//            bsonType: "object",
//            required: ["name"],
//            properties: {
//                name: {
//                    bsonType: "string",
//                    minLength: 3,
//                    maxLength: 50,
//                    description: "Name is required and must be 3 to 50 characters long."
//                }
//            }
//            }""".trimMargin())))
//
//        db.createCollection("contacts",
//                CreateCollectionOptions().validationOptions(validator))
//    }
//
//    fun writeProject(projectEntity: ProjectEntity) {
//        db.getCollection(projectCollectionName, projectEntityJavaClass)
//                .insertOne(projectEntity)
//    }
//
//    fun getProject(projectId: String): ProjectEntity {
//        return db.getCollection(projectCollectionName).withDocumentClass<ProjectEntity>()
//                .findOneById(ObjectId(projectId))!!
//    }
//}