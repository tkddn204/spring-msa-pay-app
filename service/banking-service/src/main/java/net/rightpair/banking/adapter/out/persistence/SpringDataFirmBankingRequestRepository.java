package net.rightpair.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataFirmBankingRequestRepository extends JpaRepository<FirmBankingRequestJpaEntity, Long> {
    Optional<FirmBankingRequestJpaEntity> findByAggregateIdentifier(String aggregateIdentifier);
}
