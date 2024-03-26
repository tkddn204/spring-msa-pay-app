package net.rightpair.remittance.domain;

public record RemittanceRequest (
        String remittanceRequestId,
        String remittanceFromMembershipId,
        String toBankName,
        String toBankAccountNumber,
        RemittanceType remittanceType,
        int amount,
        String remittanceStatus
) {

    public enum RemittanceType {
        MEMBERSHIP, BANK
    }

    public static RemittanceRequest generate(
            RemittanceRequest.RemittanceRequestId remittanceRequestId,
            RemittanceRequest.RemittanceFromMembershipId remittanceFromMembershipId,
            RemittanceRequest.ToBankName toBankName,
            RemittanceRequest.ToBankAccountNumber toBankAccountNumber,
            RemittanceRequest.RemittanceTypeValue remittanceType,
            RemittanceRequest.Amount amount,
            RemittanceRequest.RemittanceStatus remittanceRequestStatus
    ){
        return new RemittanceRequest(
                remittanceRequestId.remittanceRequestId,
                remittanceFromMembershipId.remittanceFromMembershipId,
                toBankName.toBankName,
                toBankAccountNumber.toBankAccountNumber,
                remittanceType.remittanceType,
                amount.amount,
                remittanceRequestStatus.remittanceStatus
        );
    }

    public record RemittanceRequestId (String remittanceRequestId) { }
    public record RemittanceFromMembershipId (String remittanceFromMembershipId) { }
    public record ToBankName (String toBankName) { }
    public record ToBankAccountNumber (String toBankAccountNumber) { }
    public record RemittanceTypeValue (RemittanceType remittanceType) { }
    public record Amount(int amount) { }
    public record RemittanceStatus (String remittanceStatus) { }
}
