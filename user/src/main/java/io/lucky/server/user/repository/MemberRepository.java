package io.lucky.server.user.repository;

import io.lucky.server.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findOneByEmail(String email);
    boolean existsByEmail(String email);
}
