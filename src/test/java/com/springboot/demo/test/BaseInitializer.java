package com.springboot.demo.test;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers

public class BaseInitializer {
    @Container
    public static MySQLContainer container = new MySQLContainer("mysql:8.0")
            .withDatabaseName("test_container_db")
            .withPassword("priyanka")
            .withUsername("priyanka");

    static {
        container.start();
    }

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        Assert.assertTrue(container.isRunning());
    }
}
