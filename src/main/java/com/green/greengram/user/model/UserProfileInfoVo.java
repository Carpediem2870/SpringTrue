package com.green.greengram.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileInfoVo {
    private int favCnt;
    private int feedCnt;
    private String nm;
    private String createdAt;
}
