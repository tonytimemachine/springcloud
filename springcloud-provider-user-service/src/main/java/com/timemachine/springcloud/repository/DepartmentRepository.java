package com.timemachine.springcloud.repository;

import com.timemachine.springcloud.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tony on 2017/6/19.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
