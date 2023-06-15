package com.leanpay.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class InstallmentPlanResponse {
  private BigDecimal monthlyPayment;
  private BigDecimal totalInterestPaid;

  public BigDecimal getMonthlyPayment() {
    return monthlyPayment;
  }

  public void setMonthlyPayment(BigDecimal monthlyPayment) {
    this.monthlyPayment = monthlyPayment.setScale(2, RoundingMode.HALF_UP);
  }

  public BigDecimal getTotalInterestPaid() {
    return totalInterestPaid;
  }

  public void setTotalInterestPaid(BigDecimal totalInterestPaid) {
    this.totalInterestPaid = totalInterestPaid.setScale(2, RoundingMode.HALF_UP);
  }
}
