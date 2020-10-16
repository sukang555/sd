package com.service;

import com.common.dto.UserInfoDTO;
import com.common.entity.UserInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.Map;
import java.util.PrimitiveIterator;

/**
 * @Author: sukang
 * @Date: 2020/10/1 14:46
 */
public interface UserInfoService extends BaseService{

    UserInfo getUserInfoByUserId(Integer id);

    UserInfoDTO selectByUserName(String username);


    UserInfoDTO getUserDetailByUserName(String username);

    PageInfo<UserInfoDTO> getUserInfos(UserInfoDTO userInfoDTO);
}
