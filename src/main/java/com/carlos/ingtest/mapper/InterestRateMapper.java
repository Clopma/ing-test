package com.carlos.ingtest.mapper;

import com.carlos.ingtest.entities.InterestRate;
import org.mapstruct.Mapper;
import org.openapitools.model.InterestRateDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface InterestRateMapper {

    InterestRateDto interestRateEntityToDto(InterestRate interestRateEntity);

    List<InterestRateDto> interestRateEntityListToDtoList(List<InterestRate> interestRateEntity);
}
