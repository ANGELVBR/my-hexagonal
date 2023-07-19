package com.hexagonal.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.hexagonal.template")
@EntityScan("com.hexagonal.template.infrastructure.entity")
public class LauncherApplication {
  public static void main(String[] args) {
    SpringApplication.run(LauncherApplication.class, args);
  }
  
}
