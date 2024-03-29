package com.explicit.redditCloneBackend.Service;

import com.explicit.redditCloneBackend.Config.Dto.CommentDto;
import com.explicit.redditCloneBackend.Exception.PostNotFoundException;

import com.explicit.redditCloneBackend.Mapper.CommentMapper;
import com.explicit.redditCloneBackend.Model.Comment;
import com.explicit.redditCloneBackend.Model.NotificationEmail;
import com.explicit.redditCloneBackend.Model.Post;
import com.explicit.redditCloneBackend.Model.User;
import com.explicit.redditCloneBackend.Repository.CommentRepository;
import com.explicit.redditCloneBackend.Repository.PostRepository;
import com.explicit.redditCloneBackend.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private static final String POST_URL = "";

    private final PostRepository postRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentDto> getAllCommentByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findPostById(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CommentDto> getAllCommentForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username.toString()));
         return commentRepository.findAllByUser(user)
                 .stream()
                 .map(commentMapper::mapToDto)
                 .collect(Collectors.toList());
    }
}
