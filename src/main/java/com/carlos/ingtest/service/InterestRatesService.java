package com.carlos.ingtest.service;


import org.openapitools.model.InterestRateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InterestRatesService {

    List<InterestRateDto> getInterestRates();

}
