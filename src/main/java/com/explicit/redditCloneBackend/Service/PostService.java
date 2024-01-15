package com.explicit.redditCloneBackend.Service;

import com.explicit.redditCloneBackend.Config.Dto.PostRequest;
import com.explicit.redditCloneBackend.Config.Dto.PostResponse;
import com.explicit.redditCloneBackend.Exception.PostNotFoundException;
import com.explicit.redditCloneBackend.Exception.SubredditNotFoundException;
import com.explicit.redditCloneBackend.Mapper.PostMapper;
import com.explicit.redditCloneBackend.Model.Post;
import com.explicit.redditCloneBackend.Model.Subreddit;
import com.explicit.redditCloneBackend.Model.User;
import com.explicit.redditCloneBackend.Repository.PostRepository;
import com.explicit.redditCloneBackend.Repository.SubredditRepository;
import com.explicit.redditCloneBackend.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {

    @Autowired
    private final SubredditRepository subredditRepository;
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final AuthService authService;
    @Autowired
    private final PostMapper postMapper;
    @Autowired
    private final UserRepository userRepository;

    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
