package com.honner.service.impl;

import com.github.pagehelper.PageHelper;
import com.honner.mapper.CollegeMapper;
import com.honner.mapper.StudentMapper;
import com.honner.mapper.StudentMapperCustom;
import com.honner.po.SelectedCourseCustom;
import com.honner.po.Student;
import com.honner.po.StudentCustom;
import com.honner.po.StudentExample;
import com.honner.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    //使用spring 自动注入
    @Autowired
    private StudentMapperCustom studentMapperCustom;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    public void updataById(Integer id, StudentCustom studentCustom) throws Exception {
        studentMapper.updateByPrimaryKey(studentCustom);
    }

    //删除学生信息
    public void removeById(Integer id) throws Exception {
        studentMapper.deleteByPrimaryKey(id);
    }

    public List<StudentCustom> findByPaging(Integer page, Integer pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        List<StudentCustom> list = studentMapperCustom.findByPaging();
        return list;
    }

    public Boolean save(StudentCustom studentCustoms) throws Exception {
        Student stu = studentMapper.selectByPrimaryKey(studentCustoms.getUserid());
        if (stu == null) {
            studentMapper.insert(studentCustoms);
            return true;
        }

        return false;
    }

    //返回学生总数
    public int getCountStudent() throws Exception {
        //自定义查询对象
        StudentExample studentExample = new StudentExample();
        //通过criteria构造查询条件
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andUseridIsNotNull();

        return studentMapper.countByExample(studentExample);
    }

    public StudentCustom findById(Integer id) throws Exception {

        Student student  = studentMapper.selectByPrimaryKey(id);
        StudentCustom studentCustom = null;
        if (student != null) {
            studentCustom = new StudentCustom();
            //类拷贝
            BeanUtils.copyProperties(student, studentCustom);
        }

        return studentCustom;
    }

    //根据姓名模糊查询
    public List<StudentCustom> findByName(String name, Integer page, Integer pageSize) throws Exception {

        StudentCustom studentCustom=new StudentCustom();

        studentCustom.setUsername("%" + name + "%");


        PageHelper.startPage(page,pageSize);
        List<StudentCustom> list = studentMapperCustom.selectByName(studentCustom);

        return list;

    }

    @Override
    public StudentCustom findStudentAndSelectCourseListByName(String name) throws Exception {

        StudentCustom studentCustom = studentMapperCustom.findStudentAndSelectCourseListById(Integer.parseInt(name));

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        // 判断该课程是否修完
        for (SelectedCourseCustom s : list) {
            if (s.getMark() != null) {
                s.setOver(true);
            }
        }
        return studentCustom;
    }
}
