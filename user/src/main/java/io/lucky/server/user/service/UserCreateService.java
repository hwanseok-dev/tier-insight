package io.lucky.server.user.service;

import io.lucky.server.user.entity.User;
import io.lucky.server.user.exception.BusinessException;
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
    private final UserSimpleQuery userSimpleQuery;
    private final UserRepository userRepository;
    public Long create(UserCreateForm form) {
        boolean existsByEmail = userSimpleQuery.existsByEmail(form.getEmail());
        if (existsByEmail) {
            throw new BusinessException("Email is already in use.");
        }
        User entity = User.newInstance(form.getEmail(), form.getNickname(), form.getPassword());
        User saved = userRepository.save(entity);
        log.info("msg : {}, id : {}, email : {}, nickname : {}",
                "create user", saved.getId(), saved.getEmail(), saved.getNickname());
        return saved.getId();
    }
}
