package Controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.df.controller.UserController;
import com.zensar.df.dto.UserDto;
import com.zensar.df.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void testLoginUser() throws Exception{
		UserDto userDto = new UserDto();
		userDto.setUsername("anand");
		userDto.setPassword("anand123");
		
		
		
		
	
}
}

	
	
	
