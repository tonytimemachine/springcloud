package com.timemachine.springcloud.service;


import com.timemachine.springcloud.entity.Department;

/**
 * Created by tony on 2017/6/19.
 */
public interface DepartmentService {




     Department saveDepartment(Department department);


     Department getDepartmentById(Long id);
}
