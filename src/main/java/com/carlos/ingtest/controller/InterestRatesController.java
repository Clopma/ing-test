package com.carlos.ingtest.controller;

import com.carlos.ingtest.service.InterestRatesService;
import org.openapitools.api.InterestRatesApi;
import org.openapitools.model.InterestRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class InterestRatesController implements InterestRatesApi {

    @Autowired
    InterestRatesService interestRatesService;

    @Override
    public ResponseEntity<List<InterestRateDto>> getInterestRates() {
        return new ResponseEntity<>(interestRatesService.getInterestRates(), HttpStatus.OK);
    }


}
