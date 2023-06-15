package com.leanpay.service.impl;

import com.leanpay.model.InstallmentPlan;
import com.leanpay.model.LoanCalculation;
import com.leanpay.repository.LoanCalculationRepo;
import com.leanpay.service.LoanCalculationService;
import com.leanpay.utils.CalculationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LoanCalculationServiceImpl implements LoanCalculationService {

  private final LoanCalculationRepo loanCalculationRepo;

  @Autowired
  public LoanCalculationServiceImpl(LoanCalculationRepo loanCalculationRepo) {
    this.loanCalculationRepo = loanCalculationRepo;
  }

  // this can maybe go into a facade because we have 2 different domains, LoanCalculation and
  // InstallmentPlan that is embedded into it
  @Override
  public InstallmentPlan getInstallmentPlan(BigDecimal loanAmount, BigDecimal annualInterestRate, int numberOfMonths) {
    // this can be as a small improvement in communication with product owner/stakeholder
    /* Optional<LoanCalculation> loanCalculationOptional = loanCalculationRepo.findByIndex(loanAmount,
                                                                                          annualInterestPercentage,
                                                                                          numberOfMonths);

    if (loanCalculationOptional.isPresent()) {
      return loanCalculationOptional.get().getInstallmentPlan();
    } */

    BigDecimal monthlyPayment = CalculationUtils.calculateMonthlyPayment(loanAmount, annualInterestRate, numberOfMonths);
    BigDecimal totalInterestPaid = CalculationUtils.calculateTotalInterestPaid(monthlyPayment, loanAmount, numberOfMonths);
    InstallmentPlan installmentPlan = new InstallmentPlan(monthlyPayment, totalInterestPaid);
    LoanCalculation loanCalculation = new LoanCalculation(loanAmount, annualInterestRate,
                                                          numberOfMonths, installmentPlan);
    loanCalculationRepo.save(loanCalculation);
    return installmentPlan;
  }
}
