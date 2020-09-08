package com.mapper;

import com.common.entity.EncryptLog;

/**
 * @author sukang
 */
public interface EncryptLogMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(EncryptLog record);

    EncryptLog selectByPrimaryKey(Integer id);

}