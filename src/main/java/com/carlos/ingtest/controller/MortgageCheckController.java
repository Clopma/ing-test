package com.carlos.ingtest.controller;

import com.carlos.ingtest.service.MortgageCheckService;
import org.openapitools.api.MortgageCheckApi;
import org.openapitools.model.InterestRateDto;
import org.openapitools.model.MortgageCheckDto;
import org.openapitools.model.MortgageCheckResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MortgageCheckController implements MortgageCheckApi {

    @Autowired
    MortgageCheckService mortgageCheckService;

    @Override
    public ResponseEntity<MortgageCheckResponseDto> postMortgageCheck(MortgageCheckDto mortgageCheckDto) {
        return new ResponseEntity<>(mortgageCheckService.checkMortgage(mortgageCheckDto), HttpStatus.OK);
    }


}
