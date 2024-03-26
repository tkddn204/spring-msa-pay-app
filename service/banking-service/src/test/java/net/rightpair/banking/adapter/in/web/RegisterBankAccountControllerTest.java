package net.rightpair.banking.adapter.in.web;

import net.rightpair.banking.application.port.out.GetMembershipPort;
import net.rightpair.banking.common.SpringBootIntegrationTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class RegisterBankAccountControllerTest extends SpringBootIntegrationTest {

    @Mock
    private GetMembershipPort getMembershipPort;

    @Test
    public void registerMembership_success() throws Exception {
        // given
//        RegisterBankAccountRequest request = new RegisterBankAccountRequest(
//                "1", "bank", "123-456-789", true
//        );
//
//        RegisteredBankAccount expected = RegisteredBankAccount.generate(
//                new RegisteredBankAccount.MembershipId("1"),
//                new RegisteredBankAccount.BankName("bank"),
//                new RegisteredBankAccount.BankAccountNumber("123-456-789"),
//                new RegisteredBankAccount.BankAccountIsValid(true)
//        );
//
//        // when
//        ResultActions result = mockMvc.perform(post("/banking/register/account")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request))
//        );
//
//        // then
//        result
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.membershipId").value(expected.getMembershipId()))
//                .andExpect(jsonPath("$.bankName").value(expected.getBankName()))
//                .andExpect(jsonPath("$.bankAccountNumber").value(expected.getBankAccountNumber()))
//                .andExpect(jsonPath("$.valid").value(expected.getIsValid()));
    }
}