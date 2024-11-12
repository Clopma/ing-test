package com.carlos.ingtest.service;


import com.carlos.ingtest.exception.BusinessException;
import org.openapitools.model.MortgageCheckDto;
import org.openapitools.model.MortgageCheckResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class MortgageCheckServiceImpl implements MortgageCheckService {


    public static final String MAX_TIMES_LOAN_VALUE = "4";

    @Override
    public MortgageCheckResponseDto checkMortgage(MortgageCheckDto mortgageCheckDto) {

        if (mortgageCheckDto.getMaturityPeriod() == null
                || mortgageCheckDto.getLoanValue() == null
                || mortgageCheckDto.getIncome() == null
                || mortgageCheckDto.getHomeValue() == null) {
            throw new BusinessException("None of the values can be null", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        boolean isFeasible = mortgageCheckDto.getIncome().multiply(new BigDecimal(MAX_TIMES_LOAN_VALUE)).compareTo(mortgageCheckDto.getLoanValue()) > 0
                && mortgageCheckDto.getLoanValue().compareTo(mortgageCheckDto.getHomeValue()) <= 0;

        if (mortgageCheckDto.getMaturityPeriod().compareTo(0) <= 0){
            throw new BusinessException("Maturity date can not be 0 or lower", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new MortgageCheckResponseDto()
                .feasibility(isFeasible)
                .monthlyCost(mortgageCheckDto.getLoanValue().divide(new BigDecimal(mortgageCheckDto.getMaturityPeriod()), 2, RoundingMode.HALF_DOWN));
    }
}
