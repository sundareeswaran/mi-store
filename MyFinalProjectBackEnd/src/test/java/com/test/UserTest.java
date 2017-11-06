package com.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.UserDAO;
import com.model.User;


public class UserTest {
	
static UserDAO userDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext configApplnContext=new AnnotationConfigApplicationContext();
		configApplnContext.scan("com");
		configApplnContext.refresh();
		
		//SessionFactory sessionFactory=(SessionFactory)configApplnContext.getBean("DBConfig.class");
		
		userDAO=(UserDAO)configApplnContext.getBean("userDAO");
	}
	
	@Ignore
	@Test
	public void addUserTest()
	{
		User user=new User();
		user.setRole("ROLE_ADMIN");
		user.setEnabled("TRUE");
		user.setUserName("karthickvnr");
		user.setUserPassword("manjulpalan1982");
		user.setUserEmail("karthickvnr@gmail.com");
		user.setUserAddress("India");
		user.setUserPhone("9080852125");
		assertTrue(userDAO.addUser(user));
	}
}
