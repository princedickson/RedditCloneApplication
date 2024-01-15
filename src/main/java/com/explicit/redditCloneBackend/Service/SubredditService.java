package com.explicit.redditCloneBackend.Service;

import com.explicit.redditCloneProject.Config.Dto.SubredditDto;
import com.explicit.redditCloneBackend.Exception.RedditErrorException;
import com.explicit.redditCloneBackend.Mapper.SubredditMapper;
import com.explicit.redditCloneBackend.Model.Subreddit;
import com.explicit.redditCloneBackend.Repository.SubredditRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@Slf4j
public class SubredditService {

    @Autowired
    private final SubredditRepository subredditRepository;
    @Autowired
    private final SubredditMapper subredditMapper;

    public SubredditService(SubredditRepository subredditRepository, SubredditMapper subredditMapper) {
        this.subredditRepository = subredditRepository;
        this.subredditMapper = subredditMapper;
    }

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(subredditMapper.mapToSubredditDto(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    @Transactional
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapToDto)
                .collect(toList());
    }

    public SubredditDto getSubreddit(Long id) throws RedditErrorException {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(()-> new RedditErrorException("No user found with id -" + id));
        return subredditMapper.mapToDto(subreddit);
    }
}
