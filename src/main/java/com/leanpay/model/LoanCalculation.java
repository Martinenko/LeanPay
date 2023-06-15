package com.leanpay.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "loan_calculation")
public class LoanCalculation extends BaseEntity {

  @Column(name = "loan_amount")
  private BigDecimal loanAmount;

  @Column(name = "annual_interest_percentage")
  private BigDecimal annualInterestPercentage;

  @Column(name = "number_of_months")
  private int numberOfMonths;

  @Embedded
  private InstallmentPlan installmentPlan;

  public LoanCalculation(BigDecimal loanAmount, BigDecimal annualInterestPercentage, int numberOfMonths, InstallmentPlan installmentPlan) {
    this.loanAmount = loanAmount;
    this.annualInterestPercentage = annualInterestPercentage;
    this.numberOfMonths = numberOfMonths;
    this.installmentPlan = installmentPlan;
  }

  public InstallmentPlan getInstallmentPlan() {
    return installmentPlan;
  }

  public void setInstallmentPlan(InstallmentPlan installmentPlan) {
    this.installmentPlan = installmentPlan;
  }
}
