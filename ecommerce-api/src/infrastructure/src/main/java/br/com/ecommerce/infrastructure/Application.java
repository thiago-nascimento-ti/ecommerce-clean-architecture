package br.com.ecommerce.infrastructure;

import br.com.ecommerce.adapters.mapper.product.CreateProductInputData;
import br.com.ecommerce.core.exception.NotFoundException;
import br.com.ecommerce.core.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

  private String loadDefaultJsonDataFile() {
    InputStream inputStream = Application.class.getResourceAsStream("/mongo/defaultData.json");
    String text = new BufferedReader(
        new InputStreamReader(inputStream, StandardCharsets.UTF_8))
        .lines()
        .collect(Collectors.joining("\n"));
    return text;
  }

  private List<CreateProductInputData> convertToListOfProducts(String content) {
    Gson gson = new Gson();
    Type productListType = new TypeToken<ArrayList<CreateProductInputData>>(){}.getType();
    return gson.fromJson(content, productListType);
  }
}