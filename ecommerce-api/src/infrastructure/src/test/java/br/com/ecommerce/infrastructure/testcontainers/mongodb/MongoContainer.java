package br.com.ecommerce.infrastructure.testcontainers.mongodb;

import br.com.ecommerce.infrastructure.testcontainers.ContainerTest;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ContextConfiguration(initializers = MongoContainer.Initializer.class)
public class MongoContainer implements ContainerTest {

  private final MongoTemplate mongoTemplate;

  @Autowired
  public MongoContainer(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @BeforeEach
  public void beforeEach() {

  }

  @AfterEach
  public void afterEach() {
    mongoTemplate.getCollectionNames().forEach(name -> {
      MongoCollection<Document> collection = mongoTemplate.getCollection(name);
      Bson filterAll = new Document();
      collection.deleteMany(filterAll);
    });
  }

  public MongoTemplate getMongoTemplate() {
    return mongoTemplate;
  }

  public static class Initializer implements
      ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static MongoDBContainer mongoDBContainer;

    public Initializer() {
      if (mongoDBContainer == null) {
        mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));
        mongoDBContainer.start();
      }
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues values = TestPropertyValues.of(
          "spring.data.mongodb.host=" + mongoDBContainer.getContainerIpAddress(),
          "spring.data.mongodb.port=" + mongoDBContainer.getFirstMappedPort()
      );
      values.applyTo(configurableApplicationContext);
    }
  }

}