package net.rightpair.membership.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "membership")
@ToString
public class MembershipJpaEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long membershipId;
    private String name;
    private String email;
    private String address;
    private boolean isValid;
    private boolean isCorp;

    @Builder
    public MembershipJpaEntity(String name, String email, String address, boolean isValid, boolean isCorp) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;
        this.isCorp = isCorp;
    }

    public void update(String name, String email, String address, Boolean isValid, Boolean isCorp) {
        if (name != null) this.name = name;
        if (email != null) this.email = email;
        if (address != null) this.address = address;
        if (isValid != null) this.isValid = isValid;
        if (isCorp != null) this.isCorp = isCorp;
    }
}
