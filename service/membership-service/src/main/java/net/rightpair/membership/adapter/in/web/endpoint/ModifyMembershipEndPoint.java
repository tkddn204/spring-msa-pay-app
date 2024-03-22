package net.rightpair.membership.adapter.in.web.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.rightpair.membership.adapter.in.web.ModifyMembershipRequest;
import net.rightpair.membership.domain.Membership;
import org.springframework.http.ResponseEntity;

@Tag(name = "멤버쉽")
public interface ModifyMembershipEndPoint {

    @Operation(
            method = "POST",
            description = "멤버쉽을 수정하는 엔드포인트"
    )
    ResponseEntity<Membership> modifyMembership(String membershipId, ModifyMembershipRequest request);
}
