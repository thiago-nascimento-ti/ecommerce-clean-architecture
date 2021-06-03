package br.com.ecommerce.infrastructure.api;

import java.net.URI;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class RestResource {

  protected URI getLocation(UUID id) {
    return getLocation(id.toString());
  }

  protected URI getLocation(long id) {
    return getLocation(String.valueOf(id));
  }

  protected URI getLocation(String id) {
    return ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(id)
        .toUri();
  }

  protected ResponseEntity<Void> created(URI uri) {
    return ResponseEntity.created(uri).build();
  }

}