package com.explicit.redditCloneBackend.Repository;

import com.explicit.redditCloneBackend.Model.Post;
import com.explicit.redditCloneBackend.Model.Subreddit;
import com.explicit.redditCloneBackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
