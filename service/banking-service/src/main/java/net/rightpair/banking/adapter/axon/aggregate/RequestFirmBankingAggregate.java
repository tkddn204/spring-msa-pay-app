package net.rightpair.banking.adapter.axon.aggregate;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rightpair.banking.adapter.axon.command.CreateRequestFirmBankingCommand;
import net.rightpair.banking.adapter.axon.command.UpdateRequestFirmBankingCommand;
import net.rightpair.banking.adapter.axon.event.RequestFirmBankingCreatedEvent;
import net.rightpair.banking.adapter.axon.event.RequestFirmBankingUpdatedEvent;
import net.rightpair.banking.domain.type.FirmBankingStatusType;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Table(name = "request_firm_banking")
@Getter
@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestFirmBankingAggregate {
    @AggregateIdentifier
    private String id;
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private Integer moneyAmount;
    private FirmBankingStatusType firmBankingStatus;

    @CommandHandler
    public RequestFirmBankingAggregate(@NotNull CreateRequestFirmBankingCommand command) {
        // store event
        apply(new RequestFirmBankingCreatedEvent(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount()
        ));
    }

    @CommandHandler
    public String handle(@NotNull UpdateRequestFirmBankingCommand command) {
        this.id = command.getAggregateIdentifier();

        // store event
        apply(new RequestFirmBankingUpdatedEvent(
                command.getFirmBankingStatus()
                ));
        return this.id;
    }

    @EventSourcingHandler
    public void on(RequestFirmBankingCreatedEvent event) {
        this.id = UUID.randomUUID().toString();
        this.fromBankName = event.fromBankName();
        this.fromBankAccountNumber = event.fromBankAccountNumber();
        this.toBankName = event.toBankName();
        this.toBankAccountNumber = event.toBankAccountNumber();
        this.moneyAmount = event.moneyAmount();
    }

    @EventSourcingHandler
    public void on(RequestFirmBankingUpdatedEvent event) {
        this.firmBankingStatus = event.firmBankingStatus();
    }
}
