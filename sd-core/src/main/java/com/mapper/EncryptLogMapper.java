package com.mapper;

import com.common.entity.EncryptLog;

/**
 * @author sukang
 */
public interface EncryptLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EncryptLog record);

    int insertSelective(EncryptLog record);

    EncryptLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EncryptLog record);

    int updateByPrimaryKey(EncryptLog record);
}