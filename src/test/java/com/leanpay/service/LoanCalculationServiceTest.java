package com.leanpay.service;

import com.leanpay.model.InstallmentPlan;
import com.leanpay.model.LoanCalculation;
import com.leanpay.repository.LoanCalculationRepo;
import com.leanpay.service.impl.LoanCalculationServiceImpl;
import com.leanpay.utils.CalculationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LoanCalculationServiceTest {

  @Mock
  private LoanCalculationRepo loanCalculationRepo;

  @InjectMocks
  private LoanCalculationServiceImpl loanCalculationService;

  private LoanCalculation loanCalculation;
  MockedStatic<CalculationUtils> calculationUtilsMockedStatic = Mockito.mockStatic(CalculationUtils.class);

  @BeforeEach
  public void setup() {
    InstallmentPlan installmentPlan = new InstallmentPlan(BigDecimal.valueOf(102.31), BigDecimal.valueOf(23.06));
    loanCalculation = new LoanCalculation(BigDecimal.valueOf(10000), BigDecimal.valueOf(5),
                                          10, installmentPlan);
  }

  @Test
  @DisplayName("JUnit test for getInstallmentPlan method")
  public void getInstallmentPlan() {
    loanCalculationService.getInstallmentPlan(BigDecimal.valueOf(10000), BigDecimal.valueOf(5),
                                              10);
    verify(loanCalculationRepo, times(1)).save(any(LoanCalculation.class));
    calculationUtilsMockedStatic.verify(() -> CalculationUtils.calculateMonthlyPayment(any(BigDecimal.class),
                                                                                       any(BigDecimal.class),
                                                                                       anyInt()), times(1));

    calculationUtilsMockedStatic.verify(() -> CalculationUtils.calculateMonthlyPayment(any(BigDecimal.class),
                                                                                       any(BigDecimal.class),
                                                                                       anyInt()), times(1));
  }



}
