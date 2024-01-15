package com.explicit.redditCloneBackend.Repository;

import com.explicit.redditCloneBackend.Model.Comment;
import com.explicit.redditCloneBackend.Model.Post;
import com.explicit.redditCloneBackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findPostById(Post post);

    List<Comment> findAllByUser(User user);
}
