package com.explicit.redditCloneBackend.Exception;

public class SubredditNotFoundException extends RuntimeException{
    public SubredditNotFoundException (String message, Exception exception){
        super(message);
    }
    public SubredditNotFoundException(String message) {
        super(message);
    }
}
