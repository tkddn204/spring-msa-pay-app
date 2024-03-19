package net.rightpair.application.port.in.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.rightpair.adapter.in.web.RegisterMembershipRequest;
import net.rightpair.domain.Membership;
import org.springframework.http.ResponseEntity;

@Tag(name = "멤버쉽")
public interface RegisterMembershipEndPoint {

    @Operation(
            method = "POST",
            description = "멤버쉽을 등록하는 엔드포인트"
    )
    ResponseEntity<Membership> registerMembership(RegisterMembershipRequest request);
}
