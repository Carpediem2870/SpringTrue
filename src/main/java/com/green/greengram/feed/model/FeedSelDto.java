package com.green.greengram.feed.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedSelDto {
    private int loginedIuser; // 로그인한 iuser, 좋아요 했는지 여부를 위해 필수로 기입되어야 함.
    private int targetIuser; // 주인 iuser
    private int startIdx;
    private int rowCount;
}
