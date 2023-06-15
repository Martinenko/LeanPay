package com.leanpay.controller;

import com.leanpay.dto.InstallmentPlanResponse;
import com.leanpay.mapper.InstallmentPlanMapper;
import com.leanpay.model.InstallmentPlan;
import com.leanpay.service.LoanCalculationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@CrossOrigin
@RestController
@RequestMapping("loan-calculation")
@OpenAPIDefinition(info = @Info(title = "Installment Plan", version = "1.0",
                                description = "Calculates installment plans based on the amount, annual interest percentage," +
                                              " and number of months",
                                contact = @Contact(name = "Ivan Martinenko")))
public class LoanCalculationController {

  private final LoanCalculationService loanCalculationService;
  private final InstallmentPlanMapper installmentPlanMapper;

  @Autowired
  public LoanCalculationController(LoanCalculationService loanCalculationService, InstallmentPlanMapper installmentPlanMapper) {
    this.loanCalculationService = loanCalculationService;
    this.installmentPlanMapper = installmentPlanMapper;
  }

  @GetMapping("installment-plan")
  @Operation(summary = "Calculating installment plan")
  @ApiResponse(responseCode = "200", description = "Calculating installment plan",
               content = { @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = InstallmentPlanResponse.class)) })
  public ResponseEntity<InstallmentPlanResponse> calculateInstallmentPlan(@Parameter @RequestParam("loanAmount") BigDecimal loanAmount,
                                                                          @Parameter @RequestParam("annualInterestPercentage") BigDecimal annualInterestPercentage,
                                                                          @Parameter @RequestParam("numberOfMonths") int numberOfMonths) {
    InstallmentPlan installmentPlan = loanCalculationService.getInstallmentPlan(loanAmount, annualInterestPercentage, numberOfMonths);
    InstallmentPlanResponse response = installmentPlanMapper.toResponse(installmentPlan);
    return ResponseEntity.ok().body(response);
  }
}
