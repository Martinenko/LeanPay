package com.leanpay.mapper;

import com.leanpay.dto.InstallmentPlanResponse;
import com.leanpay.model.InstallmentPlan;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InstallmentPlanMapperTest {
  private InstallmentPlanMapper installmentPlanMapper = Mappers.getMapper(InstallmentPlanMapper.class);

  @Test
  public void installmentPlanToInstallmentPlanResponse() {
    InstallmentPlan installmentPlan = new InstallmentPlan();
    installmentPlan.setMonthlyPayment(BigDecimal.valueOf(100.123456));
    installmentPlan.setTotalInterestPaid(BigDecimal.valueOf(5.678));

    InstallmentPlanResponse installmentPlanResponse = installmentPlanMapper.toResponse(installmentPlan);
    assertNotNull(installmentPlanResponse);
    assertEquals(installmentPlanResponse.getMonthlyPayment(), BigDecimal.valueOf(100.12));
    assertEquals(installmentPlanResponse.getTotalInterestPaid(), BigDecimal.valueOf(5.68));
  }
}
