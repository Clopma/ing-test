package com.carlos.ingtest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.openapitools.model.MortgageCheckResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MortgageCheckControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void InterestRateShouldReturnListOfMortgages() throws JsonProcessingException {

        JSONObject json = new JSONObject();
        json.put("income", "50000");
        json.put("maturityPeriod", "30");
        json.put("loanValue", "150000");
        json.put("homeValue", "180000");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String response = restTemplate.postForObject("http://localhost:" + port + "/api/mortgage-check",
                new HttpEntity<>(json.toString(), headers), String.class);

        MortgageCheckResponseDto mortgageCheckResponseDto = new ObjectMapper().readValue(response, MortgageCheckResponseDto.class);

        assertTrue(mortgageCheckResponseDto.getMonthlyCost().compareTo(new BigDecimal("5000")) == 0);
        assertTrue(mortgageCheckResponseDto.getFeasibility());

    }


}
