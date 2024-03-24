package io.lucky.server.user.repository;

import io.lucky.server.user.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Team, Long> {
    Optional<Team> findOneByName(String name);
    boolean existsByName(String name);
}
