package com.honner.service;

import com.honner.po.Disposal;

import java.util.List;

public interface DisposalService {
    boolean chufen(Integer studentId, Integer adminId, String content);

    int update(Disposal disposal);

    Disposal findDisposalById(Integer id);

    List<Disposal> findDisposalByPaging(Integer studentId, Integer page, Integer pageSize);

    List<Disposal> findDisposalByAmindIdPageing(Integer adminId, Integer page, Integer pageSize);
}
