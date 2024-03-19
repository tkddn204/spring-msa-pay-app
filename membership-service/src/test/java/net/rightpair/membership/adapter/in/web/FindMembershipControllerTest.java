package net.rightpair.membership.adapter.in.web;

import net.rightpair.membership.adapter.out.persistence.MembershipJpaEntity;
import net.rightpair.membership.adapter.out.persistence.MembershipMapper;
import net.rightpair.membership.adapter.out.persistence.MembershipPersistenceAdapter;
import net.rightpair.membership.common.SpringBootIntegrationTest;
import net.rightpair.membership.domain.Membership;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FindMembershipControllerTest extends SpringBootIntegrationTest {

    @Autowired
    private MembershipPersistenceAdapter membershipPersistenceAdapter;

    @Autowired
    private MembershipMapper membershipMapper;

    @Test
    public void findMembership_success() throws Exception {
        // given
        MembershipJpaEntity entity = membershipPersistenceAdapter.createMembership(
                new Membership.MembershipName("name"),
                new Membership.MembershipEmail("email"),
                new Membership.MembershipAddress("address"),
                new Membership.MembershipIsValid(true),
                new Membership.MembershipIsCorp(false)
        );
        Membership savedMembership = membershipMapper.mapToDomainEntity(entity);

        // when
        ResultActions result = mockMvc.perform(get("/membership/{membershipId}",
                savedMembership.getMembershipId()));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(entity.getName()))
                .andExpect(jsonPath("$.email").value(entity.getEmail()))
                .andExpect(jsonPath("$.address").value(entity.getAddress()))
                .andExpect(jsonPath("$.valid").value(entity.isValid()))
                .andExpect(jsonPath("$.corp").value(entity.isCorp()));
    }

}