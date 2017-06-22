package com.timemachine.springcloud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * User Entity
 *
 * @author Liuguanglei tonytimemachine@gmail.com
 * @create 2017-06-下午2:32
 */

public class User {


    private Long id;

    private String name;


    private String password;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;



    @JsonBackReference
    private Department department;




    private List<Role> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("password", password)
                .append("createDate", createDate)
                .append("department", department)
                .append("roleList", roleList)
                .toString();
    }
}
