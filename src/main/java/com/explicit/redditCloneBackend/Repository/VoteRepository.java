package com.explicit.redditCloneBackend.Repository;

import com.explicit.redditCloneBackend.Model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
