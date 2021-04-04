package com.honner.mapper;

import com.honner.po.Disposal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DisposalMapper {
    int insertDispoal(Disposal disposal);

    List<Disposal> findDispoalByStudentId(Integer studentId);

    List<Disposal> findDispoalByAdminId(Integer adminId);

    Disposal findById(Integer id);

    int updateById(Disposal disposal);
}
