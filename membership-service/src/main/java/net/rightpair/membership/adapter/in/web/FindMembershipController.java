package net.rightpair.membership.adapter.in.web;

import lombok.RequiredArgsConstructor;
import net.rightpair.membership.application.port.in.command.FindMembershipCommand;
import net.rightpair.membership.application.port.in.endpoint.FindMembershipEndPoint;
import net.rightpair.membership.application.port.in.usecase.FindMembershipUseCase;
import net.rightpair.membership.domain.Membership;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membership")
@RequiredArgsConstructor
public class FindMembershipController implements FindMembershipEndPoint {
    private final FindMembershipUseCase findMembershipUsecase;
    @GetMapping("/{membershipId}")
    public ResponseEntity<Membership> findMembershipByMemberId(
            @PathVariable String membershipId
    ) {
        FindMembershipCommand command = FindMembershipCommand.builder()
                .membershipId(membershipId)
                .build();
        return ResponseEntity.ok(findMembershipUsecase.findMembership(command));
    }
}
