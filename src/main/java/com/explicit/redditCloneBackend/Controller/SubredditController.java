package com.explicit.redditCloneBackend.Controller;

import com.explicit.redditCloneBackend.Exception.RedditErrorException;
import com.explicit.redditCloneBackend.Service.SubredditService;
import com.explicit.redditCloneProject.Config.Dto.SubredditDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@Slf4j
@AllArgsConstructor
public class SubredditController {

    private SubredditService subredditService;
    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto){

        if (subredditDto.getDescription() == null) {
            subredditDto.setDescription("");
        }
        if (subredditDto.getNumberOfPost() == null) {
            subredditDto.setNumberOfPost(0);
        }
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(subredditService.save(subredditDto));
    }
    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddit(){
        return ResponseEntity.status(HttpStatus.OK)
                .body((subredditService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubredditById(@PathVariable  Long id) throws RedditErrorException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(subredditService.getSubreddit(id));
    }
}
