package com.ps.invoker;

import com.ps.ents.User;
import com.ps.invoker.config.HessianClientConfig;
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

@ContextConfiguration(classes = {HessianClientConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class HessianInvokerTests {

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
