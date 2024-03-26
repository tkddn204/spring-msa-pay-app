package net.rightpair.banking.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "registered_bank_account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class RegisteredBankAccountJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private Boolean isValid;

    @Builder
    public RegisteredBankAccountJpaEntity(String membershipId, String bankName, String bankAccountNumber, Boolean isValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.isValid = isValid;
    }
}
