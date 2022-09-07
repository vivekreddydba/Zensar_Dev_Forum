package com.zensar.df.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.zensar.df.service.UserService;
import com.zensar.df.utils.JwtUtils;


@WebMvcTest(UserController.class)
public class ValidateControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	UserService userService;
	@MockBean UserDetailsService userDetailsService;
	@MockBean JwtUtils jwtUtils;
	
	
	@Test
    public void testvalidatetoken() throws Exception {
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "ebc12dc");
        
        String jwtToken="ebc12dc";
        
        //when(jwtToken).thenReturn(true);
        when(this.jwtUtils.extractUsername(any())).thenReturn(null);
        when(this.userDetailsService.loadUserByUsername(any())).thenReturn(null);
        //when(this.JwtUtils.generateToken(any())).thenReturn("ebc12dc");
        when(this.jwtUtils.validateToken(any(),any())).thenReturn(true);
        MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8000/devforum/token/validate")
                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        //assertEquals(true,(response.contains("ebc12dc")));
    }
    
    @Test
        public void testInvalidtoken() throws Exception {
            
        String jwtToken="ebc12dc";
        
        //when(jwtToken).thenReturn(jwtToken);
        when(this.jwtUtils.extractUsername(jwtToken)).thenReturn(null);
        when(this.userDetailsService.loadUserByUsername(any())).thenReturn(null);
        //when(this.JwtUtils.generateToken(any())).thenReturn("ebc12dc");
        when(this.jwtUtils.validateToken(any(),any())).thenReturn(true);
        MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8000/devforum/token/validate"))
                .andExpect(status().isBadRequest())
//                .andExpect(content().string(containsString(jwtToken)))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        //assertEquals((response.contains("ebc12dc")), false);
    }
        
	

}
