package io.lucky.server.user.service;

import io.lucky.server.user.entity.Member;
import io.lucky.server.user.exception.BusinessException;
import io.lucky.server.user.repository.MemberRepository;
import io.lucky.server.user.service.dto.UserCreateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberCreateService {
    private final MemberSimpleQuery memberSimpleQuery;
    private final MemberRepository memberRepository;
    public Long create(UserCreateForm form) {
        boolean existsByEmail = memberSimpleQuery.existsByEmail(form.getEmail());
        if (existsByEmail) {
            throw new BusinessException("Email is already in use.");
        }
        Member entity = Member.newInstance(form.getEmail(), form.getNickname(), form.getPassword());
        Member saved = memberRepository.save(entity);
        log.info("msg : {}, id : {}, email : {}, nickname : {}",
                "create user", saved.getId(), saved.getEmail(), saved.getNickname());
        return saved.getId();
    }
}
