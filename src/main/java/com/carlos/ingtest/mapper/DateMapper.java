package com.carlos.ingtest.mapper;

import org.mapstruct.Mapper;

import java.security.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Mapper(componentModel = "spring")
abstract class DateMapper {
    OffsetDateTime map(Date date) {
        return date.toInstant().atOffset(ZoneOffset.UTC);
    }
}
