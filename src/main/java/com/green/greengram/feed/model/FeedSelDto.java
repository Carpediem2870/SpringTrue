package com.green.greengram.feed.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedSelDto {
    private int startIdx;
    private int rowCount;
}
