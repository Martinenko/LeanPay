package com.leanpay.mapper;

import com.leanpay.dto.InstallmentPlanResponse;
import com.leanpay.model.InstallmentPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InstallmentPlanMapper {

  // For the sake of visibility, and maintenance sometimes
  // I prefer having fields explicitly added even though they don't have to be here
  @Mapping(target = "monthlyPayment", source =  "monthlyPayment")
  @Mapping(target = "totalInterestPaid", source = "totalInterestPaid")
  InstallmentPlanResponse toResponse(InstallmentPlan installmentPlan);
}
