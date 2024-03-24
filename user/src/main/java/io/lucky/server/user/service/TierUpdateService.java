package io.lucky.server.user.service;

import io.lucky.server.user.entity.Tier;
import io.lucky.server.user.entity.TierKey;
import io.lucky.server.user.entity.TierValue;
import io.lucky.server.user.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TierUpdateService {
    private final MemberSimpleQuery memberSimpleQuery;
    private final TierSimpleQuery tierSimpleQuery;
    public boolean addTier(Long memberId, TierKey key, TierValue value) {
        Member member = memberSimpleQuery.findOneByIdOrThrow(memberId);
        Tier tier = tierSimpleQuery.findByIdOrThrow(key, value);
        member.addTier(tier);
        return true;
    }

    public boolean removeTier(Long memberId, TierKey key, TierValue value) {
        Member member = memberSimpleQuery.findOneByIdOrThrow(memberId);
        Tier tier = tierSimpleQuery.findByIdOrThrow(key, value);
        member.removeTier(tier);
        return true;
    }
}
