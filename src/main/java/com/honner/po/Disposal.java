package com.honner.po;

import java.time.LocalDateTime;

/**
 * @author
 * @version 1.0
 * @ClassName: Disposal
 * @Description: TODO
 * @date 2021/4/2 15:34
 */
public class Disposal {
    private Integer id;
    private Integer studentId;
    private Integer adminId;
    private String content;
    private LocalDateTime createTime;
    private String shensu;

    public Disposal() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getShensu() {
        return shensu;
    }

    public void setShensu(String shensu) {
        this.shensu = shensu;
    }
}
