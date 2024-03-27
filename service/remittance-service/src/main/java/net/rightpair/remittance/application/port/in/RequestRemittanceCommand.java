package net.rightpair.remittance.application.port.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import net.rightpair.common.SelfValidating;
import net.rightpair.remittance.domain.RemittanceRequest;

@Getter
public class RequestRemittanceCommand extends SelfValidating<RequestRemittanceCommand> {

    @NotNull
    private final String fromMembershipId; // from membership

    private final String toMembershipId; // to membership

    private final String toBankName;

    private final String toBankAccountNumber;

    private final RemittanceRequest.RemittanceType remittanceType; // 0: membership(내부 고객), 1: bank (외부 은행 계좌)

    // 송금요청 금액
    @NotNull
    private final Integer amount;

    @Builder
    public RequestRemittanceCommand(String fromMembershipId, String toMembershipId, String toBankName, String toBankAccountNumber, RemittanceRequest.RemittanceType remittanceType, Integer amount) {
        this.fromMembershipId = fromMembershipId;
        this.toMembershipId = toMembershipId;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.remittanceType = remittanceType;
        this.amount = amount;
    }
}
