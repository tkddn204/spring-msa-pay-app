package net.rightpair.banking.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;
import net.rightpair.banking.domain.type.FirmBankingStatusType;

import java.util.UUID;

@Getter
@Entity
@Table(name = "request_firm_banking")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class FirmBankingRequestJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestFirmBankingId;
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount;
    private FirmBankingStatusType firmBankingStatus;
    private String uuid;
    private String aggregateIdentifier;

    @Builder
    public FirmBankingRequestJpaEntity(Long requestFirmBankingId, String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int moneyAmount, FirmBankingStatusType firmBankingStatus, UUID uuid, String aggregateIdentifier) {
        this.requestFirmBankingId = requestFirmBankingId;
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
        this.firmBankingStatus = firmBankingStatus;
        this.uuid = uuid.toString();
        this.aggregateIdentifier = aggregateIdentifier;
    }

    public void updateUUID(String uuid) {
        this.uuid = uuid;
    }

    public void updateFirmBankingStatus(FirmBankingStatusType firmBankingStatus) {
        this.firmBankingStatus = firmBankingStatus;
    }
}
