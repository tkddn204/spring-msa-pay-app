package net.rightpair.adapter.in.web;

import net.rightpair.adapter.out.persistence.MembershipJpaEntity;
import net.rightpair.adapter.out.persistence.MembershipMapper;
import net.rightpair.adapter.out.persistence.MembershipPersistenceAdapter;
import net.rightpair.common.SpringBootIntegrationTest;
import net.rightpair.domain.Membership;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ModifyMembershipControllerTest extends SpringBootIntegrationTest {

    @Autowired
    private MembershipPersistenceAdapter membershipPersistenceAdapter;

    @Autowired
    private MembershipMapper membershipMapper;

    @Test
    public void modifyMembership_success() throws Exception {
        // given
        MembershipJpaEntity entity = membershipPersistenceAdapter.createMembership(
                new Membership.MembershipName("name"),
                new Membership.MembershipEmail("email"),
                new Membership.MembershipAddress("address"),
                new Membership.MembershipIsValid(true),
                new Membership.MembershipIsCorp(false)
        );
        Membership savedMembership = membershipMapper.mapToDomainEntity(entity);

        Membership expected = Membership.generate(
                new Membership.MembershipId("1"),
                new Membership.MembershipName("modified_name"),
                new Membership.MembershipEmail("modified_email"),
                new Membership.MembershipAddress("modified_address"),
                new Membership.MembershipIsValid(false),
                new Membership.MembershipIsCorp(false)
        );
        ModifyMembershipRequest request = new ModifyMembershipRequest(
                expected.getName(),
                expected.getAddress(),
                expected.getEmail(),
                expected.getIsValid(),
                null
        );

        // when
        ResultActions result = mockMvc.perform(post("/membership/modify/{membershipId}",
                savedMembership.getMembershipId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(expected.getName()))
                .andExpect(jsonPath("$.email").value(expected.getEmail()))
                .andExpect(jsonPath("$.address").value(expected.getAddress()))
                .andExpect(jsonPath("$.valid").value(expected.getIsValid()))
                .andExpect(jsonPath("$.corp").value(expected.getIsCorp()));
    }

}