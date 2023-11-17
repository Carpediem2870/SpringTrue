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

    public List<FeedSelVo> getFeed(int page, int loginedIuser, int targetIuser) {
        final int ROW_COUNT = 30;

        // 해당하는 피드에 들어갔는지, 페이징
        FeedSelDto dto = FeedSelDto.builder()
                .loginedIuser(loginedIuser)
                .targetIuser(targetIuser)
                .startIdx((page - 1) * ROW_COUNT)
                .rowCount(ROW_COUNT)
                .build();

        // 맵 : 중복 허용X 순서도 보장하지 않음.
        // 순서 보장받으려면 LinkedHashMap 사용

        //모든 Feed 다 가지고옴
        List<FeedSelVo> feedSelVoList = mapper.selFeed(dto);
        List<Integer> iFeedList = new ArrayList(); // iFeedList는 100~70번까지 ifeed값을 뽑는작업
        Map<Integer, FeedSelVo> feedMap = new HashMap(); // MAP<key, Value>
        for(FeedSelVo vo : feedSelVoList) {
            System.out.println(vo);
            iFeedList.add(vo.getIfeed()); // ifeed 값만 계속 뽑아내는중
            feedMap.put(vo.getIfeed(), vo); // ifeed값으로 vo값(주소값)을 넣음
        }
        System.out.println("--------------");
        if(iFeedList.size() > 0) {

            List<FeedPicsVo> feedPicsList = mapper.selFeedPics(iFeedList);
            //  ifeed 값을 보내주고 ifeed값의 사진만 feedPicsList로 보내줌

            for(FeedPicsVo vo : feedPicsList) { // 가져온값의 사진 주소를 담음
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