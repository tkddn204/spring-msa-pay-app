package net.rightpair.remittance.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataRemittanceRequestRepository extends JpaRepository<RemittanceRequestJpaEntity, Long> {
    @Query("select e from RemittanceRequestJpaEntity e " +
            "where e.fromMembershipId = :membershipId or e.toMembershipId = :membershipId")
    List<RemittanceRequestJpaEntity> findAllByMembershipId(@Param("membershipId") String membershipId);
}
