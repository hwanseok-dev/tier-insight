package io.lucky.server.user.config;

import io.lucky.server.user.entity.TierKey;
import io.lucky.server.user.entity.TierValue;
import io.lucky.server.user.service.TierCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitConfig {
    private final TierCreateService tierCreateService;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initDB(){
        initTierEntity();
    }

    private void initTierEntity() {
        for (TierKey key : TierKey.values()) {
            for (TierValue value : TierValue.values()) {
                tierCreateService.create(key, value);
            }
        }
    }
}
