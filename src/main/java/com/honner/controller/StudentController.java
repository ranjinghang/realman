package com.honner.controller;

import com.github.pagehelper.PageInfo;
import com.honner.exception.CustomException;
import com.honner.po.Ad;
import com.honner.po.CourseCustom;
import com.honner.po.Disposal;
import com.honner.po.SelectedCourseCustom;
import com.honner.po.Student;
import com.honner.po.StudentCustom;
import com.honner.service.AdService;
import com.honner.service.CourseService;
import com.honner.service.DisposalService;
import com.honner.service.SelectedCourseService;
import com.honner.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;

    @Autowired
    private DisposalService disposalService;
    @Autowired
    private AdService adService;

    //展示所有课程
    @RequestMapping(value = "/showCourse")
    public ModelAndView showCourse(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                   @RequestParam(value = "pageSize", required = true, defaultValue = "4") Integer pageSize) throws Exception {

        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        Student student = studentService.findById(Integer.valueOf(userName));

        List<CourseCustom> list = courseService.findByPaging(page, pageSize);

        PageInfo CoursePageInfo = new PageInfo(list);

        ModelAndView mv = new ModelAndView();
        mv.addObject("CoursePageInfo", CoursePageInfo);
        if (student.getCourse() <= 70) {
            mv.addObject("chufenFlag", "1");
        } else {
            mv.addObject("chufenFlag", "0");
        }
        mv.setViewName("student/showCourse");

        return mv;

    }

    //将查询的 课程名称存入session中
    @RequestMapping("/searchCourseName")
    public void searchCourseName(@RequestBody Student student, HttpServletRequest request){
        String username=student.getUsername();
        //将查询的 课程名称存入session中
        request.getSession().setAttribute("findCourseByName",username);
    }

    //搜索课程
    @RequestMapping(value = "/searchCourse")
    private ModelAndView searchCourse(HttpServletRequest request,
                                      @RequestParam(value = "page",required = true,defaultValue = "1")Integer page,
                                      @RequestParam(value = "pageSize",required = true,defaultValue = "4")Integer pageSize) throws Exception {


        String  findCourseByName = (String) request.getSession().getAttribute("findCourseByName");

        List<CourseCustom> list = courseService.findByName(findCourseByName,page,pageSize);
        PageInfo searchCourseInfo=new PageInfo(list);


        ModelAndView mv=new ModelAndView();
        mv.addObject("searchCourseInfo",searchCourseInfo);
        mv.setViewName("student/searchCourse");

        return mv;

    }

    // 选课操作
    @RequestMapping(value = "/stuSelectedCourse")
    public String stuSelectedCourse(int id) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));

        SelectedCourseCustom s = selectedCourseService.findOne(selectedCourseCustom);

        if (s == null) {
            selectedCourseService.save(selectedCourseCustom);
        } else {
            throw new CustomException("该门课程你已经选了，不能再选");
        }

        return "redirect:/student/selectedCourse";
    }

    // 退课操作
    @RequestMapping(value = "/outCourse")
    public String outCourse(int id) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));

        selectedCourseService.remove(selectedCourseCustom);

        return "redirect:/student/selectedCourse";
    }

    // 已选课程
    @RequestMapping(value = "/selectedCourse")
    public String selectedCourse(Model model) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        model.addAttribute("selectedCourseList", list);

        return "student/selectCourse";
    }

    // 已修课程
    @RequestMapping(value = "/overCourse")
    public String overCourse(Model model) throws Exception {

        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        model.addAttribute("selectedCourseList", list);

        return "student/overCourse";
    }

    @RequestMapping(value = "/chufen")
    public ModelAndView chufen(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                               @RequestParam(value = "pageSize", required = true, defaultValue = "4") Integer pageSize) throws Exception {

        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        Student student = studentService.findById(Integer.valueOf((String) subject.getPrincipal()));

        List<Disposal> list = disposalService.findDisposalByPaging(student.getUserid(), page, pageSize);

        ModelAndView mv = new ModelAndView();
        PageInfo disposalInfo = new PageInfo(list);
        mv.addObject("disposalInfo", disposalInfo);
        mv.setViewName("student/disposal");
        return mv;
    }

    //修改密码
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "student/passwordRest";
    }


    // 给与学生处分
    @RequestMapping(value = "/shensu", method = {RequestMethod.GET})
    @ResponseBody
    @Transactional
    private Map<String, Object> shensu(@RequestParam Integer id, @RequestParam String shensuContent) throws Exception {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> result = new HashMap<>();
        result.put("flag", "success");
        if (id == null) {
            //加入没有带学生id就进来的话就返回学生显示页面
            result.put("flag", "error");
            result.put("msg", "没有找到用户信息");
            return result;
        }
        Disposal disposal = new Disposal();
        disposal.setId(id);
        disposal.setShensu(shensuContent);
        disposalService.update(disposal);
        result.put("msg", "申诉成功");
        return result;
    }

    @RequestMapping(value = "gonggao", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> gongago() {
        Map<String, Object> result = new HashMap<>();
        Ad ad = adService.findById(1);
        result.put("msg", ad.getContent());
        return result;
    }
}
