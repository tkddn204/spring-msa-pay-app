package net.rightpair.money.adapter.axon.command;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import net.rightpair.common.SelfValidating;

@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class CreateMoneyCommand extends SelfValidating<CreateMoneyCommand> {
    @NotNull
    private String membershipId;

    @Builder
    public CreateMoneyCommand(String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}
