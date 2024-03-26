package net.rightpair.remittance.adapter.out.service.membership;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
}