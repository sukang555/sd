package com.service;

import com.common.dto.UserInfoDTO;
import com.common.entity.UserInfo;

import java.util.PrimitiveIterator;

/**
 * @Author: sukang
 * @Date: 2020/10/1 14:46
 */
public interface UserInfoService {

    UserInfo getUserInfoByUserId(Integer id);

    UserInfoDTO selectByUserName(String username);
}
