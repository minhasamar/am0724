package com.example.toolrental.controllers;

import com.example.toolrental.entities.Tool;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class TestToolController {

    @Autowired
    private WebTestClient client;

    @Autowired
    private JdbcClient jdbcClient;

    private List<String> getCodes(){
        return jdbcClient.sql("select tool_code from tool")
                .query(String.class)
                .list();
    }

    @Test
    void getAllTools() throws Exception {
        client.get().uri("/api/v1/tools")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Tool.class).hasSize(4)
                .consumeWith(System.out::println);
    }

    @ParameterizedTest(name = "Tool Code: {0}")
    @MethodSource("getCodes")
    void getToolByCode(String toolCode) {
        client.get().uri("/api/v1/tools/{toolCode}", toolCode)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.toolCode").isEqualTo(toolCode);
    }
}
