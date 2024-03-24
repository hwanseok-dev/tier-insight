package io.lucky.server.user.service;

import io.lucky.server.user.entity.Group;
import io.lucky.server.user.exception.BusinessException;
import io.lucky.server.user.repository.GroupRepository;
import io.lucky.server.user.service.dto.GroupCreateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GroupCreateService {
    private final GroupSimpleQuery groupSimpleQuery;
    private final GroupRepository groupRepository;

    public Long create(GroupCreateForm form) {
        boolean existsByName = groupSimpleQuery.existsByName(form.getName());
        if (existsByName) {
            throw new BusinessException("Name is already in use.");
        }
        Group group = Group.newInstance(form.getName());
        Group saved = groupRepository.save(group);
        log.info("msg : {}, id : {}, name : {}", "create group", saved.getId(), saved.getName());
        return saved.getId();
    }
}
