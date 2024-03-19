package net.rightpair.application.port.in.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.rightpair.domain.Membership;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "멤버쉽")
public interface FindMembershipEndPoint {
    @Operation(
            method = "GET",
            description = "멤버쉽을 조회하는 엔드포인트"
    )
    ResponseEntity<Membership> findMembershipByMemberId(@PathVariable String membershipId);
}
