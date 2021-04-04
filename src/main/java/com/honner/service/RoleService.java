package com.honner.service;

import com.honner.po.Role;

/**
 *  Role 权限表Service层
 */
public interface RoleService {

    Role findByid(Integer id) throws Exception;

}
