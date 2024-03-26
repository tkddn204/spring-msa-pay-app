package net.rightpair.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FirmBankingRequest {
    private final String firmBankingRequestId;

    private final String fromBankName;

    private final String fromBankAccountNumber;

    private final String toBankName;

    private final String toBankAccountNumber;

    private final Integer moneyAmount; // only won

    private final FirmBankingStatusType firmBankingStatus; // 0: 요청, 1: 완료, 2: 실패

    private final UUID uuid;

    public enum FirmBankingStatusType {
        REQUESTED, SUCCESS, FAILED
    }

    public static FirmBankingRequest generate(
            FirmBankingRequestId firmBankingRequestId,
            FromBankName fromBankName,
            FromBankAccountNumber fromBankAccountNumber,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            MoneyAmount moneyAmount,
            FirmBankingStatus firmBankingStatus,
            FirmBankingRequestUUID firmBankingRequestUUID
    ) {
        return new FirmBankingRequest(
                firmBankingRequestId.firmBankingRequestId,
                fromBankName.fromBankName,
                fromBankAccountNumber.fromBankAccountNumber,
                toBankName.toBankName,
                toBankAccountNumber.toBankAccountNumber,
                moneyAmount.moneyAmount,
                firmBankingStatus.firmBankingStatus,
                firmBankingRequestUUID.uuid
        );
    }

    public record FirmBankingRequestId(String firmBankingRequestId){ }
    public record FromBankName(String fromBankName){ }
    public record FromBankAccountNumber(String fromBankAccountNumber){ }
    public record ToBankName(String toBankName){ }
    public record ToBankAccountNumber(String toBankAccountNumber){ }
    public record MoneyAmount(Integer moneyAmount) { }
    public record FirmBankingStatus(FirmBankingStatusType firmBankingStatus) { }
    public record FirmBankingRequestUUID(UUID uuid) { }
}
