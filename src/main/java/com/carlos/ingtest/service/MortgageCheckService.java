package com.carlos.ingtest.service;


import org.openapitools.model.MortgageCheckDto;
import org.openapitools.model.MortgageCheckResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface MortgageCheckService {

    MortgageCheckResponseDto checkMortgage(MortgageCheckDto mortgageCheckDto);

}
