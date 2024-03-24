package io.lucky.server.user.service;

import io.lucky.server.user.entity.Member;
import io.lucky.server.user.exception.EntityNotFoundException;
import io.lucky.server.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberSimpleQuery {

    private final MemberRepository memberRepository;
    public Member findOneByIdOrThrow(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Member.class, id));
    }

    public Member findOneByEmailOrThrow(String email){
        return memberRepository.findOneByEmail(email)
                .orElseThrow(() -> {
                    log.info("msg : {}, email : {}", "user not found", email);
                    return new EntityNotFoundException(Member.class);
                });
    }

    public boolean existsByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    public Long findIdByEmailOrThrow(String email){
        return findOneByEmailOrThrow(email).getId();
    }
}
