package net.rightpair.money.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "member_money")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberMoneyJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberMoneyId;
    private Long membershipId;
    private Integer balance;
    private String aggregateIdentifier;

    @Builder
    public MemberMoneyJpaEntity(Long memberMoneyId, Long membershipId, Integer balance, String aggregateIdentifier) {
        this.memberMoneyId = memberMoneyId;
        this.membershipId = membershipId;
        this.balance = balance;
        this.aggregateIdentifier = aggregateIdentifier;
    }

    public void updateBalance(int increaseMoneyAmount) {
        this.balance += increaseMoneyAmount;
    }
}
