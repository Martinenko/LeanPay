package com.leanpay.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoanCalculationControllerTest {

  private static final String URI = "/loan-calculation/installment-plan";
  private static final double EXPECTED_MONTHLY_PAYMENT_AMOUNT = 102.31;
  private static final double EXPECTED_TOTAL_INTEREST_PAID_AMOUNT = 23.06;

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @BeforeAll
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  /**
   * TODO: set this to be ran in self-contained manner
   *
   * @throws Exception
   */
  @Test
  public void calculateInstallmentPlan() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(URI)
                                          .param("loanAmount", "1000")
                                          .param("annualInterestPercentage", "5")
                                          .param("numberOfMonths", "10")
                                          .accept(MediaType.APPLICATION_JSON))
                                          .andDo(print())
                                          .andExpect(status().isOk())
                                          .andExpect(MockMvcResultMatchers.jsonPath("$.monthlyPayment").exists())
                                          .andExpect(MockMvcResultMatchers.jsonPath("$.monthlyPayment").value(EXPECTED_MONTHLY_PAYMENT_AMOUNT))
                                          .andExpect(MockMvcResultMatchers.jsonPath("$.totalInterestPaid").exists())
                                          .andExpect(MockMvcResultMatchers.jsonPath("$.totalInterestPaid").value(EXPECTED_TOTAL_INTEREST_PAID_AMOUNT));
  }
}
