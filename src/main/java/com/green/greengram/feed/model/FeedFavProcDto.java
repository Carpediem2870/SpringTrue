package com.green.greengram.feed.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class FeedFavProcDto {
    private int ifeed;
    private int iuser;
}
