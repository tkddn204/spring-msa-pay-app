package net.rightpair.banking.adapter.in.web;

import net.rightpair.banking.common.SpringBootIntegrationTest;
import net.rightpair.banking.domain.RegisteredBankAccount;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegisterBankAccountControllerTest extends SpringBootIntegrationTest {

    @Test
    public void registerMembership_success() throws Exception {
        // given
        RegisterBankAccountRequest request = new RegisterBankAccountRequest(
                "1", "bank", "1234-5678-9", true
        );

        RegisteredBankAccount expected = RegisteredBankAccount.generate(
                new RegisteredBankAccount.MembershipId("1"),
                new RegisteredBankAccount.BankName("bank"),
                new RegisteredBankAccount.BankAccountNumber("1234-5678-9"),
                new RegisteredBankAccount.BankAccountIsValid(true)
        );

        // when
        ResultActions result = mockMvc.perform(post("/banking/register/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.membershipId").value(expected.getMembershipId()))
                .andExpect(jsonPath("$.bankName").value(expected.getBankName()))
                .andExpect(jsonPath("$.bankAccountNumber").value(expected.getBankAccountNumber()))
                .andExpect(jsonPath("$.valid").value(expected.getIsValid()));
    }
}