package io.lucky.server.user.service;

import io.lucky.server.user.entity.Team;
import io.lucky.server.user.entity.Tier;
import io.lucky.server.user.exception.EntityNotFoundException;
import io.lucky.server.user.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupSimpleQuery {

    private final GroupRepository groupRepository;

    public Team findOneByIdOrThrow(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("msg : {}, groupId : {}", "group not found", id);
                    return new EntityNotFoundException(Tier.class);
                });
    }

    public Team findOneByNameOrThrow(String name) {
        return groupRepository.findOneByName(name)
                .orElseThrow(() -> {
                    log.info("msg : {}, name : {}", "group not found", name);
                    return new EntityNotFoundException(Tier.class);
                });
    }

    public boolean existsByName(String name){
        return groupRepository.existsByName(name);
    }

    public Long findIdByNameOrThrow(String name){
        return findOneByNameOrThrow(name).getId();
    }

}
