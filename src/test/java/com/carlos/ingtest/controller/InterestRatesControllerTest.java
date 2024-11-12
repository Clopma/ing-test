package com.carlos.ingtest.controller;

import org.junit.jupiter.api.Test;
import org.openapitools.model.InterestRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InterestRatesControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void InterestRateShouldReturnListOfMortgages() {
        //Then
        List<InterestRateDto> response = restTemplate.exchange("http://localhost:" + port + "/api/interest-rates", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<InterestRateDto>>() {}).getBody();

        //Validate
        assertEquals(3, response.size());
        //Random check on the test data (no ID's on DTO)
        assertEquals(30, response.stream().filter(ir -> ir.getInterestRate().equals(new BigDecimal("0.15"))).findAny().get().getMaturityPeriod());

    }


}
