package com.mapper;


import com.common.dto.UserInfoDTO;
import com.common.entity.UserDetail;
import com.common.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectByUserId(@Param("userId") Integer userId);

    List<UserInfo> selectAll(UserInfoDTO userInfoDTO);
}