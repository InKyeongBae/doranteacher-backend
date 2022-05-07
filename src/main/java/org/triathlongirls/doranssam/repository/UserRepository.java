package org.triathlongirls.doranssam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.triathlongirls.doranssam.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);
    boolean existsByUsername(String email);
}
