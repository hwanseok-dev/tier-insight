package io.lucky.server.user.service;

import io.lucky.server.user.entity.UserEntity;
import io.lucky.server.user.repository.UserRepository;
import io.lucky.server.user.service.dto.UserCreateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserCreateService {

    private final UserRepository userRepository;
    public Long create(UserCreateForm form) {
        UserEntity entity = UserEntity.newInstance(form.getEmail(), form.getNickname(), form.getPassword());
        UserEntity saved = userRepository.save(entity);
        log.info("id : {}, email : {}, nickname : {}", saved.getId(), saved.getEmail(), saved.getNickname());
        return saved.getId();
    }
}
