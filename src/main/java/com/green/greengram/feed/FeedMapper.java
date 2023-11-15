package com.green.greengram.feed;

import com.green.greengram.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    int insFeed(FeedInsProcDto dto);
    List<FeedSelVo> selFeed(FeedSelDto dto);

    //t_feed_pics
    int insFeedPic(FeedPicsInsProcDto dto);
    List<FeedPicsVo> selFeedPics(List<Integer> list);
}
