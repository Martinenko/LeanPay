package com.leanpay.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
public class InstallmentPlan {

  @Column(name = "monthly_payment")
  private BigDecimal monthlyPayment;

  @Column(name = "total_interest_paid")
  private BigDecimal totalInterestPaid;

  public InstallmentPlan() {
  }

  public InstallmentPlan(BigDecimal monthlyPayment, BigDecimal totalInterestPaid) {
    this.monthlyPayment = monthlyPayment;
    this.totalInterestPaid = totalInterestPaid;
  }

  public BigDecimal getMonthlyPayment() {
    return monthlyPayment;
  }

  public void setMonthlyPayment(BigDecimal monthlyPayment) {
    this.monthlyPayment = monthlyPayment;
  }

  public BigDecimal getTotalInterestPaid() {
    return totalInterestPaid;
  }

  public void setTotalInterestPaid(BigDecimal totalInterestPaid) {
    this.totalInterestPaid = totalInterestPaid;
  }
}
