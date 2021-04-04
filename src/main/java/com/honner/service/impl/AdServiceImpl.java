package com.honner.service.impl;

import com.honner.mapper.AdMapper;
import com.honner.po.Ad;
import com.honner.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @version 1.0
 * @ClassName: AdServiceImpl
 * @Description: TODO
 * @date 2021/4/4 5:24
 */
@Service
public class AdServiceImpl implements AdService {
    @Autowired
    private AdMapper adMapper;

    @Override
    public Ad findById(Integer id) {
        return adMapper.findById(id);
    }

    @Override
    public int updateById(Integer id, String content) {
        Ad ad = new Ad();
        ad.setId(id);
        ad.setContent(content);

        return adMapper.updateById(ad);
    }
}
