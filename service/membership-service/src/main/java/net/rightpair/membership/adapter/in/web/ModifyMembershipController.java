package net.rightpair.membership.adapter.in.web;

import lombok.RequiredArgsConstructor;
import net.rightpair.membership.application.port.in.command.ModifyMembershipCommand;
import net.rightpair.membership.adapter.in.web.endpoint.ModifyMembershipEndPoint;
import net.rightpair.membership.application.port.in.usecase.ModifyMembershipUseCase;
import net.rightpair.common.annotation.WebAdapter;
import net.rightpair.membership.domain.Membership;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("/membership")
@RequiredArgsConstructor
public class ModifyMembershipController implements ModifyMembershipEndPoint {

    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @Override
    @PostMapping("/modify/{membershipId}")
    public ResponseEntity<Membership> modifyMembership(
            @PathVariable String membershipId,
            @RequestBody ModifyMembershipRequest request
    ) {
        ModifyMembershipCommand command = ModifyMembershipCommand.builder()
                .membershipId(membershipId)
                .name(request.name())
                .email(request.email())
                .address(request.address())
                .isValid(request.isValid())
                .isCorp(request.isCorp())
                .build();
        return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership(command));
    }
}
