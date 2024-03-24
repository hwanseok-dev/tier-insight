package io.lucky.server.user.service;

import io.lucky.server.user.entity.Group;
import io.lucky.server.user.entity.Tier;
import io.lucky.server.user.entity.TierKey;
import io.lucky.server.user.entity.TierValue;
import io.lucky.server.user.exception.EntityNotFoundException;
import io.lucky.server.user.repository.GroupRepository;
import io.lucky.server.user.repository.TierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupSimpleQuery {

    private final GroupRepository groupRepository;

    public Group findByIdOrThrow(String name) {
        return groupRepository.findOneByName(name)
                .orElseThrow(() -> {
                    log.info("msg : {}, name : {}", "group not found", name);
                    return new EntityNotFoundException(Tier.class);
                });
    }

    public boolean existsByName(String name){
        return groupRepository.existsByName(name);
    }

}
