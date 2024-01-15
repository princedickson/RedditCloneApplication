package com.explicit.redditCloneBackend.Repository;

import com.explicit.redditCloneBackend.Model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<Object> findByToken(String token);

    void deleteByToken(String token);
}
