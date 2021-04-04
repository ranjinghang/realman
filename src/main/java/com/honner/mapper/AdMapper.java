package com.honner.mapper;

import com.honner.po.Ad;

/**
 * @author ranjinghang
 * @version 1.0
 * @ClassName: AdMapper
 * @Description: TODO
 * @date 2021/4/4 5:25
 */
public interface AdMapper {

    Ad findById(Integer id);

    int updateById(Ad ad);
}
