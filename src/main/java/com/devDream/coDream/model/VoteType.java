package com.devDream.coDream.model;

import com.devDream.coDream.exceptions.coDreamException;

import java.util.Arrays;

public enum VoteType {
    UPVOTE(1), DOWNVOTE(-1),
    ;

    private int direction;

    VoteType(int direction) {
    }

    public static VoteType lookup(Integer direction) {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new coDreamException("Vote not found"));
    }

    public Integer getDirection() {
        return direction;
    }
}