package com.service.impl;

import com.common.dto.UserInfoDTO;
import com.common.entity.UserDetail;
import com.common.entity.UserInfo;
import com.common.util.BeanMapping;
import com.common.util.StrUtil;
import com.datasource.DynamicRouteDataSource;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.mapper.UserDetailMapper;
import com.mapper.UserInfoMapper;
import com.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @Author: sukang
 * @Date: 2020/10/1 14:46
 */
@Service
@DynamicRouteDataSource
public class UserServiceInfoImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserDetailMapper userDetailMapper;

    @Override
    public UserInfo getUserInfoByUserId(Integer userId) {
        return userInfoMapper.selectByUserId(userId);
    }

    @Override
    public UserInfoDTO selectByUserName(String username) {
        UserDetail userDetail = userDetailMapper.selectByuserName(username);
        UserInfo userInfo = getUserInfoByUserId(userDetail.getId());

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserName(userInfo.getUserName());
        userInfoDTO.setHeadImage(userInfo.getHeadImage());
        return userInfoDTO;
    }

    @Override
    public UserInfoDTO getUserDetailByUserName(String username) {
        UserDetail userDetail = userDetailMapper.selectByuserName(username);
        UserInfo userInfo = getUserInfoByUserId(userDetail.getId());
        return BeanMapping.copy(userInfo, UserInfoDTO.class);
    }

    @Override
    public PageInfo<UserInfoDTO> getUserInfos(UserInfoDTO userInfoDTO) {
        startPage(userInfoDTO);

        List<UserInfo> userInfos = userInfoMapper.selectAll(userInfoDTO);
        List<UserInfoDTO> userInfoDtos = BeanMapping.copyList(userInfos, UserInfoDTO.class);

        for (UserInfoDTO infoDTO : userInfoDtos) {
            UserDetail userDetail = userDetailMapper.selectByPrimaryKey(String.valueOf(infoDTO.getUserId()));
            infoDTO.setStatus(userDetail.getStatus());
            infoDTO.setIdNo(StrUtil.idNoHide(infoDTO.getIdNo()));
        }

        return new PageInfo<>(userInfoDtos);
    }





























}
