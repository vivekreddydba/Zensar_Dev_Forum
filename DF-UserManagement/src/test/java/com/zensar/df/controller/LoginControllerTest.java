package com.zensar.df.controller;



import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;




import com.zensar.df.dto.UserDto;
import com.zensar.df.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.df.controller.UserController;
import com.zensar.df.utils.JwtUtils;


@WebMvcTest( UserController.class)
public class LoginControllerTest {
    @Autowired MockMvc mvc;
    @MockBean AuthenticationManager authenticationManager;
    @MockBean Authentication authentication;
    
      @MockBean UserDetailsService userDetailsService;
      @MockBean JwtUtils jwtutils;
      @MockBean com.zensar.df.service.UserService UserService;
      @Autowired
      ObjectMapper objectMapper;
      
      @Test
      public void addEmployeeTest() throws Exception  {
          
          UserDto auth = new UserDto();
          when(this.authenticationManager.authenticate(any())).thenReturn(null);
          when(this.jwtutils.generateToken(any())).thenReturn("djhsuidhdojiodcisj");
          MvcResult mvcResult = this.mvc.perform(post("http://localhost:8000/devforum/user/authenticate")
                  .contentType("application/json")
                  .content(objectMapper.writeValueAsString(auth))
                  ).andExpect(status().isOk())
                  //.andExpect(content().string(containsString("")))
                  .andReturn();



         String token = mvcResult.getResponse().getContentAsString();
          assertEquals(token.length()>0, true);
          
      }
}
               
            
                  
          