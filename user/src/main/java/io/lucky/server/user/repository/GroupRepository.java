package io.lucky.server.user.repository;

import io.lucky.server.user.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findOneByName(String name);
    boolean existsByName(String name);
}
