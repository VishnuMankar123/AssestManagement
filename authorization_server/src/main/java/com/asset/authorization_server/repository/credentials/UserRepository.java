package com.asset.authorization_server.repository.credentials;

import com.asset.authorization_server.entity.credentials.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = """
            SELECT user
            FROM User user
            WHERE user.username = :username
            """)
    Optional<User> findByUsername(@Param(value = "username") String username);
}
