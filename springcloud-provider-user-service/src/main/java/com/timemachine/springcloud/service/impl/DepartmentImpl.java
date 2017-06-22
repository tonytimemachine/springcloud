package com.timemachine.springcloud.service.impl;


import com.timemachine.springcloud.entity.Department;
import com.timemachine.springcloud.repository.DepartmentRepository;
import com.timemachine.springcloud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Department Impl
 *
 * @author Liuguanglei tonytimemachine@gmail.com
 * @create 2017-06-下午3:12
 */
@Transactional
@Service
public class DepartmentImpl implements DepartmentService {


    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findOne(id);
    }
}
