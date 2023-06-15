package com.leanpay.service;

import com.leanpay.model.InstallmentPlan;

import java.math.BigDecimal;

public interface LoanCalculationService {

  /**
   * Creates Installment Plan object, saves it along with parameters gotten in request and returns it
   *
   * @param loanAmount - amount that we want to loan
   * @param annualInterestRate - interest rate that we will need to pay for a loan
   * @param numberOfMonths - number of months to give money back
   * @return - returns Installment plan for passed parameters
   */
  InstallmentPlan getInstallmentPlan(BigDecimal loanAmount, BigDecimal annualInterestRate, int numberOfMonths);

}
