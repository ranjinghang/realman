package com.honner.service;

import com.honner.po.Ad;

/**
 * @author ranjinghang
 * @version 1.0
 * @ClassName: AdService
 * @Description: TODO
 * @date 2021/4/4 5:22
 */
public interface AdService {

    Ad findById(Integer id);

    int updateById(Integer id, String content);
}
