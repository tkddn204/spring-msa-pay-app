package net.rightpair.money.adapter.axon.aggregate;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.rightpair.money.adapter.axon.command.CreateMoneyCommand;
import net.rightpair.money.adapter.axon.command.IncreaseMoneyRequestEventCommand;
import net.rightpair.money.adapter.axon.event.IncreaseMoneyRequestEvent;
import net.rightpair.money.adapter.axon.event.MemberMoneyCreateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Getter
@Table(name = "member_money")
@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberMoneyAggregate {
    @AggregateIdentifier
    private String id;
    private Long membershipId;
    private Integer balance;

    @CommandHandler
    public MemberMoneyAggregate(@NotNull CreateMoneyCommand command) {
        apply(new MemberMoneyCreateEvent(command.getMembershipId()));
    }

    @CommandHandler
    public String handle(@NotNull IncreaseMoneyRequestEventCommand command) {
        this.id = command.getAggregateIdentifier();

        apply(new IncreaseMoneyRequestEvent(id, command.getTargetMembershipId(), command.getAmount()));
        return this.id;
    }

    @EventSourcingHandler
    public void on(MemberMoneyCreateEvent event) {
        this.id = UUID.randomUUID().toString();
        this.membershipId = Long.parseLong(event.membershipId());
        this.balance = 0;
    }

    @EventSourcingHandler
    public void on(IncreaseMoneyRequestEvent event) {
        this.id = event.aggregateIdentifier();
        this.membershipId = Long.parseLong(event.targetMembershipId());
        this.balance = event.amount();
    }
}
