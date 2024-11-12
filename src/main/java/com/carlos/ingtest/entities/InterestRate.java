package com.carlos.ingtest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "interest_rate")
public class InterestRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "maturity_period")
    Integer maturityPeriod;

    @Column(name = "interest_rate")
    BigDecimal interestRate;

    @Column(name = "last_update")
    Date lastUpdate;

}
