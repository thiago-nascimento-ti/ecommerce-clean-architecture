package br.com.ecommerce.infrastructure.database.springdata;

import br.com.ecommerce.adapters.repository.mongo.model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMongoRepository extends MongoRepository<ProductModel, Long> {

}