package com.carlos.ingtest.service;


import com.carlos.ingtest.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.openapitools.model.MortgageCheckDto;
import org.openapitools.model.MortgageCheckResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MortgageCheckIT {

    @Autowired
    MortgageCheckServiceImpl mortgageCheckService;


    @Test
    public void testCheckRulesPassIsFeasible() {

        MortgageCheckDto mortgageCheckDto = new MortgageCheckDto().income(new BigDecimal("50000"))
                .maturityPeriod(30)
                .loanValue(new BigDecimal("150000"))
                .homeValue(new BigDecimal("180000"));

        MortgageCheckResponseDto mortgageCheckResponseDto = mortgageCheckService.checkMortgage(mortgageCheckDto);

        assertTrue(mortgageCheckResponseDto.getFeasibility());
    }

    @Test
    public void testMaturityPeriod0OrLessReturnsBusinessError() {

        MortgageCheckDto mortgageCheckDto = new MortgageCheckDto().income(new BigDecimal("50000"))
                .maturityPeriod(0)
                .loanValue(new BigDecimal("150000"))
                .homeValue(new BigDecimal("180000"));

        BusinessException businessException = assertThrows(BusinessException.class,  () -> mortgageCheckService.checkMortgage(mortgageCheckDto));
        assertEquals("Maturity date can not be 0 or lower", businessException.getMessage());
    }


    @Test
    public void testMortgageShouldNotExceed4TimesTheIncomeIsNotFeasible() {

        MortgageCheckDto mortgageCheckDto = new MortgageCheckDto().income(new BigDecimal("50000"))
                .maturityPeriod(30)
                .loanValue(new BigDecimal("210000"))
                .homeValue(new BigDecimal("250000"));

        MortgageCheckResponseDto mortgageCheckResponseDto = mortgageCheckService.checkMortgage(mortgageCheckDto);

        assertFalse(mortgageCheckResponseDto.getFeasibility());
    }

    @Test
    public void testMortgageShouldNotExceedHomeValueIsNotFeasible() {

        MortgageCheckDto mortgageCheckDto = new MortgageCheckDto()
                .income(new BigDecimal("50000"))
                .maturityPeriod(30)
                .loanValue(new BigDecimal("100000"))
                .homeValue(new BigDecimal("60000"));

        MortgageCheckResponseDto mortgageCheckResponseDto = mortgageCheckService.checkMortgage(mortgageCheckDto);

        assertFalse(mortgageCheckResponseDto.getFeasibility());
    }

    @Test
    public void testMonthlyCostShouldBeTheMaturityPeriodTimesSmallerThanTheLoanValue() {

        MortgageCheckDto mortgageCheckDto = new MortgageCheckDto()
                .income(new BigDecimal("50000"))
                .maturityPeriod(30)
                .loanValue(new BigDecimal("150000"))
                .homeValue(new BigDecimal("180000"));

        MortgageCheckResponseDto mortgageCheckResponseDto = mortgageCheckService.checkMortgage(mortgageCheckDto);

        assertTrue(new BigDecimal("5000").compareTo(mortgageCheckResponseDto.getMonthlyCost()) == 0);
    }


}
