package net.rightpair.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SpringDataMemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {
    @Query("SELECT e FROM MemberMoneyJpaEntity e WHERE e.membershipId = :membershipId LIMIT 1")
    Optional<MemberMoneyJpaEntity> findByMembershipId(@Param("membershipId") Long membershipId);
}
