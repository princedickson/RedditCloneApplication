package com.explicit.redditCloneBackend.Controller;


import com.explicit.redditCloneBackend.Config.Dto.PostRequest;
import com.explicit.redditCloneBackend.Config.Dto.PostResponse;
import com.explicit.redditCloneBackend.Exception.RedditErrorException;
import com.explicit.redditCloneBackend.Service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/post")
@Slf4j
@AllArgsConstructor
public class PostController {


    private PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPost() {
        return ResponseEntity.status(HttpStatus.OK)
                .body((postService.getAllPosts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) throws RedditErrorException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.getPost(id));
    }
    @GetMapping(params = "subredditId")
    public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@RequestParam Long subredditId) {
        return status(HttpStatus.OK).body(postService.getPostsBySubreddit(subredditId));
    }

    @GetMapping(params = "username")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@RequestParam String username) {
        return status(HttpStatus.OK).body(postService.getPostsByUsername(username));
    }
}
