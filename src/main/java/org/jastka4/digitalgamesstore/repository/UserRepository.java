package org.jastka4.digitalgamesstore.repository;

import org.jastka4.digitalgamesstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
