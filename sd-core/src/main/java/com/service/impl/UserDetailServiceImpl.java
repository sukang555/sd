package com.service.impl;

import com.common.entity.UserDetail;
import com.datasource.DynamicRouteDataSource;
import com.mapper.UserDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: sukang
 * @Date: 2020/9/29 12:39
 */
@Service
@Slf4j
@DynamicRouteDataSource
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserDetailMapper userDetailMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserDetail userDetail = userDetailMapper.selectByuserName(s);
        if (userDetail == null) {
                throw new BadCredentialsException("用户名不存在");
        }

        return new User(userDetail.getUsername(),userDetail.getPassword(),getGrantedAuthority(userDetail));
    }

    public List<GrantedAuthority> getGrantedAuthority(UserDetail userDetail){
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return list;
    }
}
