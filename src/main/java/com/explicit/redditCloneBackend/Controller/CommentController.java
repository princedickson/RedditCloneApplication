package com.explicit.redditCloneBackend.Controller;

import com.explicit.redditCloneBackend.Config.Dto.CommentDto;
import com.explicit.redditCloneBackend.Service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/comment")
@RestController
@AllArgsConstructor
public class CommentController {

    @Autowired
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentByPostId(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentByPostId(postId));

    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<CommentDto>> getAllCommentForUser(@PathVariable String username) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentForUser(username));
    }
}
