package org.triathlongirls.doranssam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.triathlongirls.doranssam.domain.user.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
