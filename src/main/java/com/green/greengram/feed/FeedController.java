package com.green.greengram.feed;

import com.green.greengram.ResVo;
import com.green.greengram.feed.model.FeedFavProcDto;
import com.green.greengram.feed.model.FeedInsDto;
import com.green.greengram.feed.model.FeedSelVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
    private final FeedService service;

    @PostMapping
    public ResVo insFeed(@RequestBody FeedInsDto dto) {
        System.out.println(dto);
        return service.insFeed(dto);
    }

    @GetMapping
    public List<FeedSelVo> getFeed(int page, int iuser) {
        System.out.println(page);
        return service.getFeed(page, iuser, 0); // 타겟유저가 없는 경우 0으로 사용
    }

    @GetMapping("/{targetIuser}")
    public List<FeedSelVo> getProfileFeed(@PathVariable int targetIuser, int page, int loginedIuser) {
        return service.getFeed(page, loginedIuser, targetIuser); // 타겟유저가 없는 경우 0으로 사용
    }

    @GetMapping("/{ifeed}/fav")
    public ResVo procFav(@PathVariable int ifeed, int iuser) {
        FeedFavProcDto dto = FeedFavProcDto.builder()
                .ifeed(ifeed)
                .iuser(iuser)
                .build();
        return service.procFav(dto);
    }


}
