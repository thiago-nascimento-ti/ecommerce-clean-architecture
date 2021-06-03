package br.com.ecommerce.infrastructure.database.springdata;

import br.com.ecommerce.adapters.repository.mongo.model.OrderModel;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMongoRepository extends MongoRepository<OrderModel, UUID> {

}