package net.rightpair.banking.adapter.in.web;

public record RequestFirmBankingRequest(
        // a -> b 실물계좌로 요청을 하기 위한 Request
        String fromBankName,
        String fromBankAccountNumber,
        String toBankName,
        String toBankAccountNumber,
        Integer moneyAmount
) {
}
