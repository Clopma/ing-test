package com.carlos.ingtest.service;


import com.carlos.ingtest.mapper.InterestRateMapper;
import com.carlos.ingtest.repositories.InterestRateRepository;
import org.openapitools.model.InterestRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InterestRatesServiceImpl implements InterestRatesService {

    @Autowired
    InterestRateRepository interestRateRepository;

    @Autowired
    InterestRateMapper interestRateMapper;

    @Override
    public List<InterestRateDto> getInterestRates() {
        return interestRateMapper.interestRateEntityListToDtoList(interestRateRepository.findAll());
    }
}
