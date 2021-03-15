package com.revature;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;



public class UserServiceTest {
	
		private static UserService u;
		
		@Before
		public void setUpUserServiceImpl() {
			u = new UserServiceImp();
		}
		
		@Test
		public void testHashedPassword() {
			String password = u.hashPassword("123456");
			assertEquals( "ef4dafda494ad517e9823ae7d102a4c8",password);
		}
}
