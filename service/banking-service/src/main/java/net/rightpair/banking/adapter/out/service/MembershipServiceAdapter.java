package net.rightpair.banking.adapter.out.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.rightpair.banking.application.port.out.GetMembershipPort;
import net.rightpair.banking.application.port.out.MembershipStatus;
import net.rightpair.common.CommonHttpClient;
import net.rightpair.common.annotation.InternalServiceAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

@InternalServiceAdapter
public class MembershipServiceAdapter implements GetMembershipPort {

    private final CommonHttpClient commonHttpClient;
    private final ObjectMapper objectMapper;

    private final String membershipServiceUrl;

    public MembershipServiceAdapter(CommonHttpClient commonHttpClient,
                                    ObjectMapper objectMapper,
                                    @Value("${service.membership.url}") String membershipServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.objectMapper = objectMapper;
        this.membershipServiceUrl = membershipServiceUrl;
    }

    @Override
    public MembershipStatus getMembershipStatus(String membershipId) {
        String url = UriComponentsBuilder.newInstance()
                .host(membershipServiceUrl)
                .pathSegment("membership", membershipId)
                .toUriString();
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            Membership membership = objectMapper.readValue(jsonResponse, Membership.class);
            return new MembershipStatus(membership.getMembershipId(), membership.getIsValid());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
