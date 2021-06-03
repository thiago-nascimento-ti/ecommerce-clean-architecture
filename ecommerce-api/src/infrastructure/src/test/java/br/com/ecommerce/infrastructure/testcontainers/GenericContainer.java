package br.com.ecommerce.infrastructure.testcontainers;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GenericContainer {

  private final List<ContainerTest> containers;

  public GenericContainer(List<ContainerTest> containers) {
    this.containers = containers;
  }

  @BeforeEach
  public void beforeEach() {
    containers.stream().forEach(ContainerTest::beforeEach);
  }

  @AfterEach
  public void afterEach() {
    containers.stream().forEach(ContainerTest::afterEach);
  }

}