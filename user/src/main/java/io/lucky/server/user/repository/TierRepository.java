package io.lucky.server.user.repository;

import io.lucky.server.user.entity.TierEntity;
import io.lucky.server.user.entity.TierKey;
import io.lucky.server.user.entity.TierValue;
import io.lucky.server.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TierRepository extends JpaRepository<TierEntity, Long> {
    Optional<TierEntity> findOneByTierKeyAndTierValue(TierKey key, TierValue value);
}
