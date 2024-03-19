package net.rightpair.adapter.in.web;

import net.rightpair.common.SpringBootIntegrationTest;
import net.rightpair.domain.Membership;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegisterMembershipControllerTest extends SpringBootIntegrationTest {
    @Test
    public void registerMembership_success() throws Exception {
        // given
        RegisterMembershipRequest request = new RegisterMembershipRequest(
                "name", "address", "email", false);

        Membership expected = Membership.generate(
                new Membership.MembershipId("1"),
                new Membership.MembershipName("name"),
                new Membership.MembershipEmail("email"),
                new Membership.MembershipAddress("address"),
                new Membership.MembershipIsValid(true),
                new Membership.MembershipIsCorp(false)
        );

        // when
        ResultActions result = mockMvc.perform(post("/membership/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(expected.getName()))
                .andExpect(jsonPath("$.email").value(expected.getEmail()))
                .andExpect(jsonPath("$.address").value(expected.getAddress()))
                .andExpect(jsonPath("$.valid").value(expected.isValid()))
                .andExpect(jsonPath("$.corp").value(expected.isCorp()));
    }

}