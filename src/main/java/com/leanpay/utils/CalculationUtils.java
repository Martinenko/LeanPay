package com.leanpay.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationUtils {

  /**
   * Calculates monthly payment. Formula used for calculation is
   * payment = loanAmount * 𝑖(1+𝑖)^𝑛)/(1+𝑖)𝑛−1
   * i - annual interest rate
   * n = number of months
   *
   * @param loanAmount - amount that we want to loan
   * @param annualInterestRate - interest rate that we will need to pay for a loan
   * @param numberOfMonths - number of months to give money back
   * @return monthly payment amount
   */
  public static BigDecimal calculateMonthlyPayment(BigDecimal loanAmount, BigDecimal annualInterestRate, int numberOfMonths) {
    BigDecimal annualInterestRateDecimal = annualInterestRate.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                                                             .divide(BigDecimal.valueOf(12), 6, RoundingMode.HALF_UP);
    BigDecimal powMultiplicand = BigDecimal.ONE.add(annualInterestRateDecimal)
                                               .pow(numberOfMonths)
                                               .setScale(6, RoundingMode.HALF_UP);
    BigDecimal dividend = loanAmount.multiply(annualInterestRateDecimal)
                                    .multiply(powMultiplicand);
    BigDecimal divisor = powMultiplicand.subtract(BigDecimal.ONE);
    return dividend.divide(divisor, 5, RoundingMode.HALF_UP);
  }

  /**
   * Calculate total interest amount that will be paid. Formula is
   * totalInterestPaid = monthlyAmount * numberOfMonths - loanAmount
   *
   * @param monthlyPayment - amount of money to be paid monthly for installment
   * @param loanAmount - amount of money that we want to loan
   * @param numberOfMonths - number of months to return the loan
   * @return total interest amount
   */
  public static BigDecimal calculateTotalInterestPaid(BigDecimal monthlyPayment, BigDecimal loanAmount, int numberOfMonths) {
    return monthlyPayment.multiply(BigDecimal.valueOf(numberOfMonths)).subtract(loanAmount);
  }
}
