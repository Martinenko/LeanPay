package com.leanpay.repository;

import com.leanpay.model.LoanCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface LoanCalculationRepo extends JpaRepository<LoanCalculation, Long> {

  @Query(value =  "SELECT la FROM LoanCalculation la" +
                  " WHERE la.loanAmount = :loanAmount" +
                  " AND la.annualInterestPercentage = :annualInterestRate" +
                  " AND la.numberOfMonths = :numberOfMonths")
  Optional<LoanCalculation> findByIndex(BigDecimal loanAmount, BigDecimal annualInterestRate, int numberOfMonths);
}
