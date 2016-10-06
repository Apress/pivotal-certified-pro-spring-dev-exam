package com.ps.hessian;

import com.ps.ents.User;
import com.ps.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(locations = {"classpath:spring/hessian-client-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HessianTests {

	@Autowired
	@Qualifier("userServiceHessian")
	private UserService userService;

	public void setUp() {
		assertNotNull(userService);
	}

	@Test
	public void testRmiAll() {
		List<User> users = userService.findAll();
		assertEquals(5, users.size());
	}

	@Test
	public void testRmiJohn() {
		User user = userService.findByEmail("john.cusack@pet.com");
		assertNotNull(user);
	}

}
