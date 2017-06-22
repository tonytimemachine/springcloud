package com.timemachine.springcloud;

import com.timemachine.springcloud.entity.Department;
import com.timemachine.springcloud.entity.User;
import com.timemachine.springcloud.service.DepartmentService;
import com.timemachine.springcloud.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.timemachine.springcloud")
public class SpringcloudUserServiceApplicationTests {


	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SpringcloudUserServiceApplicationTests.class);


	@Test
	public void contextLoads() {
	}



	@Autowired
	private DepartmentService departmentService;


	@Autowired
	private UserService userService;


	@Test
	public void testDepartmentService(){

		Department department=new Department();
		department.setName("dev");
		Department result =departmentService.saveDepartment(department);

		LOGGER.info("add result "+result);


		Long id =1L;
		result =departmentService.getDepartmentById(id);
		LOGGER.info("get department "+result);
	}


	@Test
	public void testUserRegister()throws Exception{
		User user =new User();
		user.setName("tony");
		user.setPassword("666666");
		user.setCreateDate(new Date());
		Department department=departmentService.getDepartmentById(1L);
		user.setDepartment(department);
		User result =userService.register(user);
		LOGGER.info("register result "+result);

	}


	@Test
	public void testWriteOff()throws Exception{
		User user =new User();
		user.setName("tony");
		user.setPassword("666666");
		userService.writeOff(user);

	}



	@Test
	public void testUserLogin()throws Exception{
		User user =new User();
		user.setName("tony");
		user.setPassword("666666");
		User result =userService.login(user.getName(),user.getPassword());
		LOGGER.info("login  "+result);

	}

	@Test
	public void testUserIsExist()throws Exception{

		User user =new User();
		user.setId(4L);
		boolean result =userService.isExists(user);
		LOGGER.info("isExist  "+result);

	}

}
