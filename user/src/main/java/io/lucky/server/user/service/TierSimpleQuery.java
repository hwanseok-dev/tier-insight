package io.lucky.server.user.service;

import io.lucky.server.user.entity.TierEntity;
import io.lucky.server.user.entity.TierKey;
import io.lucky.server.user.entity.TierValue;
import io.lucky.server.user.exception.EntityNotFoundException;
import io.lucky.server.user.repository.TierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TierSimpleQuery {

    private final TierRepository tierRepository;

    public TierEntity findByIdOrThrow(TierKey key, TierValue value) {
        return tierRepository.findOneByTierKeyAndTierValue(key, value)
                .orElseThrow(() -> {
                    log.info("msg : {}, key : {}, value : {}", "tier not found", key, value);
                    return new EntityNotFoundException(TierEntity.class);
                });
    }
}
