package net.rightpair.membership.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 오염이 되어서는 안되는 고객 정보(핵심 도메인)
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {
    private final String membershipId;
    private final String name;
    private final String email;
    private final String address;
    @JsonProperty("valid")
    private final Boolean isValid;
    @JsonProperty("corp")
    private final Boolean isCorp;

    public static Membership generate(
            MembershipId membershipId,
            MembershipName membershipName,
            MembershipEmail membershipEmail,
            MembershipAddress membershipAddress,
            MembershipIsValid membershipIsValid,
            MembershipIsCorp membershipIsCorp

    ) {
        return new Membership(
                membershipId.membershipId,
                membershipName.membershipName,
                membershipEmail.membershipEmail,
                membershipAddress.membershipAddress,
                membershipIsValid.membershipIsValid,
                membershipIsCorp.membershipIsCorp
        );
    }

    public record MembershipId(String membershipId) { }
    public record MembershipName(String membershipName) { }
    public record MembershipEmail(String membershipEmail) { }
    public record MembershipAddress(String membershipAddress) { }
    public record MembershipIsValid(Boolean membershipIsValid) { }
    public record MembershipIsCorp(Boolean membershipIsCorp) { }
}
