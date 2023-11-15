package com.green.greengram.user;

import com.green.greengram.ResVo;
import com.green.greengram.user.model.UserInsDto;
import com.green.greengram.user.model.UserSigninProcVo;
import com.green.greengram.user.model.UserSigninVo;
import com.green.greengram.user.model.UserSigninDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    //
    public ResVo insUser(UserInsDto udto){
        int result = mapper.insUser(udto);
        return new ResVo(result);
    }

    //1: 아이디/비번 맞췄음, 2: 아이디 없음, 3: 비밀번호 다름
    public UserSigninVo signin(UserSigninDto dto) {
        UserSigninVo vo = new UserSigninVo();
        vo.setResult(3);

        UserSigninProcVo procVo = mapper.selUserByUid(dto.getUid());
        if(procVo == null) {
            vo.setResult(2);
        } else if(procVo.getUpw().equals(dto.getUpw())) {
            vo.setResult(1);
            vo.setIuser(procVo.getIuser());
            vo.setNm(procVo.getNm());
            vo.setPic(procVo.getPic());
        }
        return vo;
    }
}
