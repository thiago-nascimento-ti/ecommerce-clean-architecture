package br.com.ecommerce.infrastructure.api;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/v1/health-check")
public class HealthCheckResource {

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public void getStatus() {
  }

}