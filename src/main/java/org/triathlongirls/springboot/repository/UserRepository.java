package org.triathlongirls.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.triathlongirls.springboot.domain.user.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}
