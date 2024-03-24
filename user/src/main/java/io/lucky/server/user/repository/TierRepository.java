package io.lucky.server.user.repository;

import io.lucky.server.user.entity.Tier;
import io.lucky.server.user.entity.TierKey;
import io.lucky.server.user.entity.TierValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TierRepository extends JpaRepository<Tier, Long> {
    Optional<Tier> findOneByTierKeyAndTierValue(TierKey key, TierValue value);
}
