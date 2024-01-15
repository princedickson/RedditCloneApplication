package com.explicit.redditCloneBackend.Mapper;

import com.explicit.redditCloneBackend.Config.Dto.PostRequest;
import com.explicit.redditCloneBackend.Config.Dto.PostResponse;
import com.explicit.redditCloneBackend.Model.Post;
import com.explicit.redditCloneBackend.Model.Subreddit;
import com.explicit.redditCloneBackend.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface PostMapper {


    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "username", source = "user.username")
    PostResponse mapToDto(Post post);
}
