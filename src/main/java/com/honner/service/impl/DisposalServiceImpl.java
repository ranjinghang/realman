package com.honner.service.impl;

import com.github.pagehelper.PageHelper;
import com.honner.mapper.DisposalMapper;
import com.honner.mapper.StudentMapper;
import com.honner.po.Disposal;
import com.honner.po.Student;
import com.honner.service.DisposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @ClassName: DisposalServiceImpl
 * @Description: TODO
 * @date 2021/4/2 16:05
 */
@Service
public class DisposalServiceImpl implements DisposalService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DisposalMapper disposalMapper;

    /**
     * 对学生进行处分
     *
     * @param studentId 学生ID
     * @param adminId   管理员ID
     * @param content   处分内容
     * @return
     */
    @Override
    @Transactional
    public boolean chufen(Integer studentId, Integer adminId, String content) {
        Student student = studentMapper.selectByPrimaryKey(studentId);
        if (student == null) {
            return false;
        } else {
            //先进行处分保存
            Disposal disposal = new Disposal();
            disposal.setStudentId(studentId);
            disposal.setAdminId(adminId);
            disposal.setContent(content);

            int flag = disposalMapper.insertDispoal(disposal);

            if (flag == 0) {
                return false;
            } else {
                //处分保存成功后，修改学生的诚信分
                if (student.getCourse() >= 10) {
                    student.setCourse(student.getCourse() - 10);
                } else {
                    student.setCourse(0);
                }
                studentMapper.updateByPrimaryKey(student);
            }
        }
        return true;
    }

    @Override
    public int update(Disposal disposal) {
        return disposalMapper.updateById(disposal);
    }

    @Override
    public Disposal findDisposalById(Integer id) {
        return disposalMapper.findById(id);
    }

    @Override
    public List<Disposal> findDisposalByPaging(Integer studentId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Disposal> list = disposalMapper.findDispoalByStudentId(studentId);

        return list;
    }

    @Override
    public List<Disposal> findDisposalByAmindIdPageing(Integer adminId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Disposal> list = disposalMapper.findDispoalByAdminId(adminId);

        return list;
    }
}
