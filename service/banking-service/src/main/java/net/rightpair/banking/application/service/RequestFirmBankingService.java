package net.rightpair.banking.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rightpair.banking.adapter.axon.command.CreateRequestFirmBankingCommand;
import net.rightpair.banking.adapter.axon.command.UpdateRequestFirmBankingCommand;
import net.rightpair.banking.adapter.out.external.firm.ExternalFirmBankingRequest;
import net.rightpair.banking.adapter.out.external.firm.FirmBankingResult;
import net.rightpair.banking.adapter.out.persistence.FirmBankingRequestJpaEntity;
import net.rightpair.banking.adapter.out.persistence.FirmBankingRequestMapper;
import net.rightpair.banking.application.port.in.command.RequestFirmBankingCommand;
import net.rightpair.banking.application.port.in.usecase.RequestFirmBankingUsecase;
import net.rightpair.banking.application.port.out.RequestExternalFirmBankingPort;
import net.rightpair.banking.application.port.out.RequestFirmBankingPort;
import net.rightpair.banking.domain.FirmBankingRequest;
import net.rightpair.common.annotation.UseCase;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static net.rightpair.banking.domain.type.FirmBankingStatusType.*;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RequestFirmBankingService implements RequestFirmBankingUsecase {

    private final FirmBankingRequestMapper firmBankingRequestMapper;
    private final RequestFirmBankingPort requestFirmBankingPort;

    private final RequestExternalFirmBankingPort requestExternalFirmBankingPort;

    private final CommandGateway commandGateway;

    @Override
    public FirmBankingRequest requestFirmBanking(RequestFirmBankingCommand command) {

        // 1. 요청에 대해 정보를 먼저 write - "요청" 상태로
        FirmBankingRequestJpaEntity requestJpaEntity = requestFirmBankingPort.createFirmBankingRequest(
                new FirmBankingRequest.FromBankName(command.getFromBankName()),
                new FirmBankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmBankingRequest.ToBankName(command.getToBankName()),
                new FirmBankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmBankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmBankingRequest.FirmBankingStatus(REQUESTED),
                new FirmBankingRequest.FirmBankingAggregateIdentifier("")
        );

        // 2. 외부 은행에 펌뱅킹 요청
        FirmBankingResult result = requestExternalFirmBankingPort.requestExternalFirmBanking(new ExternalFirmBankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber()
        ));

        // Transactional UUID
        UUID randomUUID = UUID.randomUUID();
        requestJpaEntity.updateUUID(randomUUID.toString());

        // 3. 결과에 따라서 1번에서 작성했던 FirmBankingRequest 정보를 Update
        if (result.resultCode() == 0) {
            requestJpaEntity.updateFirmBankingStatus(SUCCESS);
        } else {
            requestJpaEntity.updateFirmBankingStatus(FAILED);
        }

        // 4. 결과를 리턴하기 전에 바뀐 상태 값을 기준으로 다시 save
        return firmBankingRequestMapper.mapToDomainEntity(requestFirmBankingPort.modifyFirmBankingRequest(requestJpaEntity), randomUUID);
    }

    @Override
    public void requestFirmBankingByEvent(RequestFirmBankingCommand command) {
        commandGateway.send(CreateRequestFirmBankingCommand.builder()
                .fromBankName(command.getFromBankName())
                .fromBankAccountNumber(command.getFromBankAccountNumber())
                .toBankName(command.getToBankName())
                .toBankAccountNumber(command.getToBankAccountNumber())
                .moneyAmount(command.getMoneyAmount())
                .build()
        ).whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("%s ERROR : ".formatted(CreateRequestFirmBankingCommand.class.getSimpleName()), throwable);
                return;
            }
            FirmBankingRequestJpaEntity requestJpaEntity = requestFirmBankingPort.createFirmBankingRequest(
                    new FirmBankingRequest.FromBankName(command.getFromBankName()),
                    new FirmBankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                    new FirmBankingRequest.ToBankName(command.getToBankName()),
                    new FirmBankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                    new FirmBankingRequest.MoneyAmount(command.getMoneyAmount()),
                    new FirmBankingRequest.FirmBankingStatus(REQUESTED),
                    new FirmBankingRequest.FirmBankingAggregateIdentifier(result.toString())
            );

            FirmBankingResult firmBankingResult = requestExternalFirmBankingPort.requestExternalFirmBanking(new ExternalFirmBankingRequest(
                    command.getFromBankName(),
                    command.getFromBankAccountNumber(),
                    command.getToBankName(),
                    command.getToBankAccountNumber()
            ));

            // Transactional UUID
            UUID randomUUID = UUID.randomUUID();
            requestJpaEntity.updateUUID(randomUUID.toString());

            if (firmBankingResult.resultCode() == 0) {
                requestJpaEntity.updateFirmBankingStatus(SUCCESS);
            } else {
                requestJpaEntity.updateFirmBankingStatus(FAILED);
            }
        });
    }

    @Override
    public void updateFirmBankingByEvent(UpdateRequestFirmBankingCommand command) {
        commandGateway.send(command)
                .whenComplete((result, throwable) -> {
                    if (throwable != null) {
                        log.error("%s ERROR : ".formatted(UpdateRequestFirmBankingCommand.class.getSimpleName()), throwable);
                        return;
                    }
                    FirmBankingRequestJpaEntity requestJpaEntity = requestFirmBankingPort.getFirmBankingRequest(
                            new FirmBankingRequest.FirmBankingAggregateIdentifier(result.toString())
                    );
                    requestJpaEntity.updateFirmBankingStatus(command.getFirmBankingStatus());
                    requestFirmBankingPort.modifyFirmBankingRequest(requestJpaEntity);
                });
    }
}
