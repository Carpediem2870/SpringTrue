package com.green.greengram.user;

import com.green.greengram.user.model.UserInsDto;
import com.green.greengram.user.model.UserSigninProcVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //
    int insUser(UserInsDto udto);
    UserSigninProcVo selUserByUid(String uid);
}
