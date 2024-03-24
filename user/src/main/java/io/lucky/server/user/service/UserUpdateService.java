package io.lucky.server.user.service;

import io.lucky.server.user.entity.Tier;
import io.lucky.server.user.entity.TierKey;
import io.lucky.server.user.entity.TierValue;
import io.lucky.server.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserUpdateService {
    private final UserSimpleQuery userSimpleQuery;
    private final TierSimpleQuery tierSimpleQuery;
    public boolean addTier(Long userId, TierKey key, TierValue value) {
        User user = userSimpleQuery.findByIdOrThrow(userId);
        Tier tier = tierSimpleQuery.findByIdOrThrow(key, value);
        user.addTier(tier);
        return true;
    }

    public boolean removeTier(Long userId, TierKey key, TierValue value) {
        User user = userSimpleQuery.findByIdOrThrow(userId);
        Tier tier = tierSimpleQuery.findByIdOrThrow(key, value);
        user.removeTier(tier);
        return true;
    }
}
