package net.rightpair.banking.application.service;

import lombok.RequiredArgsConstructor;
import net.rightpair.banking.adapter.out.external.bank.GetBankAccountRequest;
import net.rightpair.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import net.rightpair.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import net.rightpair.banking.application.exception.RegisteredBankAccountNotFoundException;
import net.rightpair.banking.application.port.in.command.RegisterBankAccountCommand;
import net.rightpair.banking.application.port.in.usecase.RegisterBankAccountUseCase;
import net.rightpair.banking.application.port.out.GetMembershipPort;
import net.rightpair.banking.application.port.out.MembershipStatus;
import net.rightpair.banking.application.port.out.RegisteredBankAccountPort;
import net.rightpair.banking.application.port.out.RequestExternalBankAccountInfoPort;
import net.rightpair.banking.domain.ExternalBankAccount;
import net.rightpair.banking.domain.RegisteredBankAccount;
import net.rightpair.common.annotation.UseCase;
import net.rightpair.exception.MembershipInvalidException;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final GetMembershipPort getMembershipPort;
    private final RegisteredBankAccountPort registeredBankAccountPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;

    private final RequestExternalBankAccountInfoPort requestExternalBankAccountInfoPort;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {

        // 0. 멤버쉽 서비스를 호출하여 멤버쉽이 유효한지 확인
        MembershipStatus membershipStatus = getMembershipPort.getMembershipStatus(command.getMembershipId());
        if (!membershipStatus.isValid()) {
            throw new MembershipInvalidException();
        }

        // 1. 외부 실제 은행에 등록이 가능한 계좌인지 확인
        // Port -> Adapter -> External System
        // 실제 외부의 은행계좌 정보 get
        ExternalBankAccount externalBankAccount = requestExternalBankAccountInfoPort.getBankAccountInfo(
                command.getMembershipId(),
                new GetBankAccountRequest(
                        command.getBankName(),
                        command.getBankAccountNumber()
                )
        );

        // 2. 등록가능한 계좌라면 등록한다. 성공하면 등록에 성공한 등록 정보를 리턴함
        // 2-1. 등록가능하지 않은 계좌라면 에러발생
        if (externalBankAccount.getIsValid()) {
            RegisteredBankAccountJpaEntity savedAccountInfo = registeredBankAccountPort.createRegisteredBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                    externalBankAccount
            );
            return registeredBankAccountMapper.mapToDomainEntity(savedAccountInfo);
        } else {
            throw new RegisteredBankAccountNotFoundException();
        }
    }
}
