package com.green.greengram.feed.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FeedSelVo {
    private int ifeed;
    private String contents;
    private String location;
    private int iuser; // 작성자 pk
    private String writer; //작성자 이름
    private String createdAt; //피드 등록 날짜
    private List<String> pics = new ArrayList();
}
