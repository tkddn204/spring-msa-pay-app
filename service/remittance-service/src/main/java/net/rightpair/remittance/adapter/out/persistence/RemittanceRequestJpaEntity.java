package net.rightpair.remittance.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.rightpair.remittance.domain.RemittanceRequest;

@Getter
@Entity
@Table(name = "remittance_request")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemittanceRequestJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long remittanceRequestId;
    private String fromMembershipId; // from membership
    private String toMembershipId; // to membership
    private String toBankName;
    private String toBankAccountNumber;
    private RemittanceRequest.RemittanceType remittanceType; // 0: membership(내부 고객), 1: bank (외부 은행 계좌)
    // 송금요청 금액
    private int amount;
    private String remittanceStatus;

    @Builder
    public RemittanceRequestJpaEntity(Long remittanceRequestId, String fromMembershipId, String toMembershipId, String toBankName, String toBankAccountNumber, RemittanceRequest.RemittanceType remittanceType, int amount, String remittanceStatus) {
        this.remittanceRequestId = remittanceRequestId;
        this.fromMembershipId = fromMembershipId;
        this.toMembershipId = toMembershipId;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.remittanceType = remittanceType;
        this.amount = amount;
        this.remittanceStatus = remittanceStatus;
    }

    public void updateRemittanceStatus(String remittanceStatus) {
        this.remittanceStatus = remittanceStatus;
    }
}
