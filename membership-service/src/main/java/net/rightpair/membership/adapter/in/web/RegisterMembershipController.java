package net.rightpair.membership.adapter.in.web;

import lombok.RequiredArgsConstructor;
import net.rightpair.membership.application.port.in.command.RegisterMembershipCommand;
import net.rightpair.membership.application.port.in.endpoint.RegisterMembershipEndPoint;
import net.rightpair.membership.application.port.in.usecase.RegisterMembershipUseCase;
import net.rightpair.common.WebAdapter;
import net.rightpair.membership.domain.Membership;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/membership")
@RequiredArgsConstructor
public class RegisterMembershipController implements RegisterMembershipEndPoint {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @Override
    @PostMapping("/register")
    public ResponseEntity<Membership> registerMembership(
            @RequestBody RegisterMembershipRequest request
    ) {
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.name())
                .address(request.address())
                .email(request.email())
                .isCorp(request.isCorp())
                .build();

        return ResponseEntity.ok(registerMembershipUseCase.registerMembership(command));
    }
}
