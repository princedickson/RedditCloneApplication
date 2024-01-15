package com.explicit.redditCloneBackend.Mapper;

import com.explicit.redditCloneBackend.Config.Dto.CommentDto;
import com.explicit.redditCloneBackend.Model.Comment;
import com.explicit.redditCloneBackend.Model.Post;
import com.explicit.redditCloneBackend.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "createDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    Comment map(CommentDto commentDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
    CommentDto mapToDto(Comment comment);

}
