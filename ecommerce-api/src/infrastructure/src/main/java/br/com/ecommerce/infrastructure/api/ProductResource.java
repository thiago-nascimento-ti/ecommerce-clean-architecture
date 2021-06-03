package br.com.ecommerce.infrastructure.api;

import br.com.ecommerce.adapters.mapper.product.CreateProductInputData;
import br.com.ecommerce.adapters.mapper.product.ProductOutputData;
import br.com.ecommerce.core.entity.Paged;
import br.com.ecommerce.core.entity.Product;
import br.com.ecommerce.core.service.ProductService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v1/products")
public class ProductResource {

  private final ProductService service;

  @Autowired
  public ProductResource(ProductService service) {
    this.service = service;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> create(@RequestBody CreateProductInputData createUserInputData) {
    Product product = createUserInputData.toEntity();
    service.save(product);
    URI location = getLocation(product.getCode());
    return ResponseEntity.created(location).build();
  }

  @GetMapping("{code}")
  @ResponseStatus(HttpStatus.OK)
  public ProductOutputData findById(@PathVariable("code") Long code) {
    Product product = service.findProductByCode(code);
    return new ProductOutputData().fromEntity(product);
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<ProductOutputData>> findAllPaged(
      @RequestParam(value = "_page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "_limit", required = false, defaultValue = "0") int itemsPerPage) {
    Paged<Product> paged = service.findAllProductPaged(page, itemsPerPage);

    List<ProductOutputData> outputDataList = paged
        .getItems()
        .stream()
        .map(this::toOutputData)
        .collect(Collectors.toList());

    HttpHeaders headers = new HttpHeaders();
    headers.add("x-total-count", String.valueOf(paged.getTotalItems()));

    return new ResponseEntity<>(outputDataList, headers, HttpStatus.OK);
  }

  private URI getLocation(long id) {
    return ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(id)
        .toUri();
  }

  private ProductOutputData toOutputData(Product product) {
    return new ProductOutputData().fromEntity(product);
  }

}