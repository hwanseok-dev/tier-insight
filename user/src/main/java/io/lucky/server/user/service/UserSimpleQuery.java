package io.lucky.server.user.service;

import io.lucky.server.user.entity.User;
import io.lucky.server.user.exception.EntityNotFoundException;
import io.lucky.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSimpleQuery {

    private final UserRepository userRepository;
    public User findByIdOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, userId));
    }

    public User findByEmailOrThrow(String email){
        return userRepository.findOneByEmail(email)
                .orElseThrow(() -> {
                    log.info("msg : {}, email : {}", "user not found", email);
                    return new EntityNotFoundException(User.class);
                });
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
