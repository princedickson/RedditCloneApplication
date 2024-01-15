package com.explicit.redditCloneBackend.Model;

public enum VoteType {
    Upvote (1), DownVote(-1),
    ;

    VoteType(int direction) {
    }
}
