package com.green.greengram.feed;

import com.green.greengram.ResVo;
import com.green.greengram.feed.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//dd
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;

    public ResVo insFeed(FeedInsDto dto) {
        FeedInsProcDto pDto = new FeedInsProcDto(dto);
        System.out.println(dto);
        System.out.println(pDto);
        int result = mapper.insFeed(pDto);
        System.out.println(pDto);

        FeedPicsInsProcDto p2Dto = new FeedPicsInsProcDto(pDto.getIfeed(), dto.getPics());
        int result2 = mapper.insFeedPic(p2Dto);
        System.out.println("result2 : " + result2);
        return new ResVo(pDto.getIfeed());
    }

    public List<FeedSelVo> getFeed(int page, int iuser) {
        final int ROW_COUNT = 30;
        FeedSelDto dto = FeedSelDto.builder()
                .iuser(iuser)
                .startIdx((page - 1) * ROW_COUNT)
                .rowCount(ROW_COUNT)
                .build();

        // 맵 : 중복 허용X 순서도 보장하지 않음.
        // 순서 보장받으려면 LinkedHashMap 사용
        List<FeedSelVo> feedSelVoList = mapper.selFeed(dto);
        List<Integer> iFeedList = new ArrayList();
        Map<Integer, FeedSelVo> feedMap = new HashMap(); // MAP<key, Value>
        for(FeedSelVo vo : feedSelVoList) {
            System.out.println(vo);
            iFeedList.add(vo.getIfeed());
            feedMap.put(vo.getIfeed(), vo);
        }
        System.out.println("--------------");
        if(iFeedList.size() > 0) {

            List<FeedPicsVo> feedPicsList = mapper.selFeedPics(iFeedList);

            for(FeedPicsVo vo : feedPicsList) {
                System.out.println(vo);
                FeedSelVo feedVo = feedMap.get(vo.getIfeed());
                List<String> strPicsList = feedVo.getPics();
                strPicsList.add(vo.getPic());
            }
        }
        return feedSelVoList;
    }

    // 1. 아래 (FeedFavProcDto dto)에있는 파라미터로 데이터가 들어와서
    // 2. { 구현부에서 가공해서 다시 리턴해서 보냄 }
    public ResVo procFav(FeedFavProcDto dto){
        int result = mapper.delFeedFav(dto);

        if (result == 0){
            mapper.insFeedFav(dto);
            return new ResVo(1);

        } else if (result == 1){
            return new ResVo(2);
        }
        return null;
    }
}