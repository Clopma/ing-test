package com.carlos.ingtest.service;


import com.carlos.ingtest.entities.InterestRate;
import com.carlos.ingtest.repositories.InterestRateRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class InterestRatesIT {

    @Autowired
    InterestRatesServiceImpl interestRatesService;

    @Mock
    InterestRateRepository interestRateRepository;


    @Test
    public void testGetInterestRatesMockedData() {

        //When
        when(interestRateRepository.findAll()).thenReturn(List.of(
                InterestRate.builder().id(1).interestRate(new BigDecimal("0.1")).maturityPeriod(10).build(),
                InterestRate.builder().id(2).interestRate(new BigDecimal("0.2")).maturityPeriod(20).build())
        );

        assertEquals(3, interestRatesService.getInterestRates().size());
    }


}
