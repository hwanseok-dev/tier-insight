package io.lucky.server.user.service;

import io.lucky.server.user.entity.TierEntity;
import io.lucky.server.user.entity.TierKey;
import io.lucky.server.user.entity.TierValue;
import io.lucky.server.user.repository.TierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TierCreateService {
    private final TierRepository tierRepository;
    public Long create(TierKey key, TierValue value) {
        TierEntity entity = TierEntity.newInstance(key, value);
        TierEntity saved = tierRepository.save(entity);
        log.info("msg : {}, id : {}, key : {}, value : {}",
                "create tier", saved.getId(), saved.getTierKey(), saved.getTierValue());
        return saved.getId();
    }
}
