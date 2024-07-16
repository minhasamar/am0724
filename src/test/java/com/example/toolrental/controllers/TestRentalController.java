package com.example.toolrental.controllers;


import com.example.toolrental.entities.RentalRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class TestRentalController {

    @Autowired
    private WebTestClient webTestClient;

    private List<RentalRequest> getRequests() throws ParseException{
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        return List.of(
                //new RentalRequest("JAKR", df.parse("9/3/15"), 5, Double.valueOf(101)),
                new RentalRequest("LADW", df.parse("7/2/20"), 3, Double.valueOf(10)),
                new RentalRequest("CHNS", df.parse("7/2/15"), 5, Double.valueOf(25)),
                new RentalRequest("JAKD", df.parse("9/3/15"), 6, Double.valueOf(0)),
                new RentalRequest("JAKR", df.parse("7/2/15"), 9, Double.valueOf(0)),
                new RentalRequest("JAKR", df.parse("7/2/20"), 4, Double.valueOf(50))
        );
    }

    @ParameterizedTest(name = "Request rental: {0}")
    @MethodSource("getRequests")
    void TestCheckout(RentalRequest request) {
        webTestClient.post().uri("/api/v1/rentals/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), RentalRequest.class)
                .exchange();

    }

    void TestCheckoutValidation() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        RentalRequest request = new RentalRequest("JAKR", df.parse("9/3/15"), 5, Double.valueOf(101));
        webTestClient.post().uri("/api/v1/rentals/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), RentalRequest.class)
                .exchange()
                .expectStatus()
                .isBadRequest();

    }
}
