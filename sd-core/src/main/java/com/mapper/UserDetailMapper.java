package com.mapper;


import com.common.entity.UserDetail;
import org.apache.ibatis.annotations.Param;

/**
 * @author  sukang
 */
public interface UserDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserDetail record);

    int insertSelective(UserDetail record);

    UserDetail selectByPrimaryKey(String id);

    UserDetail selectByuserName(@Param("userName") String userName);

    int updateByPrimaryKeySelective(UserDetail record);

    int updateByPrimaryKey(UserDetail record);
}