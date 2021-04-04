package com.honner.mapper;

import com.honner.po.Student;
import com.honner.po.StudentExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentMapper {
    int countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    //删除学生信息
    int deleteByPrimaryKey(Integer userid);

    int insert(Student record);

    int insertSelective(Student record);

    //    之前的根据姓名模糊查找
    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    //修改学生信息
    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}