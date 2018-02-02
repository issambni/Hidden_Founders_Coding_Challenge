package com.hidden.founders;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hidden.founders.model.UserAccount;
import com.hidden.founders.repository.IUserRepository;
import com.hidden.founders.service.IUserService;
import com.hidden.founders.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserServiceTest {
	
	@TestConfiguration
	static class IUserServiceTestConfiguration {
		@Bean
		public IUserService userService()
		{
			return new UserService();
		}
	}
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IUserRepository userRepository;
	
	@Test
	public void succes_whenNameIsFound()
	{
		List<UserAccount> found= userService.findAll();
		assertThat(found.get(0).getEmail().equals("example@email.com"));
	}

}
