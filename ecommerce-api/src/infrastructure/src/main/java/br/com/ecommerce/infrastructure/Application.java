package br.com.ecommerce.infrastructure;

import br.com.ecommerce.adapters.mapper.product.CreateProductInputData;
import br.com.ecommerce.core.exception.NotFoundException;
import br.com.ecommerce.core.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoCollection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.ResourceUtils;

@SpringBootApplication
public class Application {

  private final ProductService service;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Autowired
  Application(ProductService service) {
    this.service = service;
  }

  @PostConstruct
  public void initData() throws IOException {
    try {
      service.findProductByCode(3096034909L);
    } catch (NotFoundException e) {
      String content = loadDefaultJsonDataFile();
      convertToListOfProducts(content)
          .stream()
          .map(CreateProductInputData::toEntity)
          .forEach(service::save);
    }
  }

  private String loadDefaultJsonDataFile() throws IOException {
    File file = ResourceUtils.getFile("classpath:defaultData.json");
    return new String(Files.readAllBytes(file.toPath()));
  }

  private List<CreateProductInputData> convertToListOfProducts(String content) {
    Gson gson = new Gson();
    Type productListType = new TypeToken<ArrayList<CreateProductInputData>>(){}.getType();
    return gson.fromJson(content, productListType);
  }
}