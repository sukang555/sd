package com.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.beans.Transient;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author sukang
 * 
 * @date 2020-09-30
 */
@Setter
@Getter
public class SysMenu {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 父级编号
     */
    private Long pid;

    /**
     * 所有父级编号
     */
    private String pids;

    /**
     * URL地址
     */
    private String url;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型（1:一级菜单,2:子级菜单,3:不是菜单）
     */
    private Byte type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 创建用户
     */
    private Long createBy;

    /**
     * 更新用户
     */
    private Long updateBy;

    /**
     * 状态（1:正常,2:冻结,3:删除）
     */
    private Byte status;

    @JsonIgnore
    private Map<Long, SysMenu> children = new HashMap<>();


}