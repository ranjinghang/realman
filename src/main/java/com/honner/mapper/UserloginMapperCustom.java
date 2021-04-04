package com.honner.mapper;

import com.honner.po.UserloginCustom;

/**
 *  UserloginMapper扩展类
 */
public interface UserloginMapperCustom {

    UserloginCustom findOneByName(String name) throws Exception;

}
