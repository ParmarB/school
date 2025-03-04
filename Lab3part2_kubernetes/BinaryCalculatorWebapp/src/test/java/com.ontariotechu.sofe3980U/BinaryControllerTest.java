package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.beans.Transient;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }

    // testing for the get request with an operand
    @Test
    public void getParameter1() throws Exception {
        this.mvc. perform(get("/").param("operand1", "011"))
        .andExpect(status().isOk())
        .andExpect(view().name("calculator"))
        .andExpect(model().attribute("operand1", "011"))
        .andExpect(model().attribute("operand1Focused", true));
    }

    // testing for adding 2 operands
    @Test
    public void postParameter1() throws Exception {
        this.mvc.perform(post("/").param("operand1","011")
        .param("operator","+").param("operand2","000"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "11"))
			.andExpect(model().attribute("operand1", "011"));
    }

     // testing for empty operands in addition
     @Test
     public void postEmptyOperandsAdd() throws Exception {
         this.mvc.perform(post("/").param("operand1","")
         .param("operator","+").param("operand2",""))
             .andExpect(status().isOk())
             .andExpect(view().name("result"))
             .andExpect(model().attribute("result", "0"));
     }

    //testing for post bitwiseOr operation with 2 operands
     @Test
     public void postBitwiseOR() throws Exception {
         this.mvc.perform(post("/").param("operand1","000")
         .param("operator","|").param("operand2","111"))
             .andExpect(status().isOk())
             .andExpect(view().name("result"))
             .andExpect(model().attribute("result", "111"));
     }

     //testing for post AND operation with 2 operands
     @Test
     public void postAND() throws Exception {
         this.mvc.perform(post("/").param("operand1","000")
         .param("operator","&").param("operand2","111"))
             .andExpect(status().isOk())
             .andExpect(view().name("result"))
             .andExpect(model().attribute("result", "0"));
     }

     //testing for post Multiply operation with 2 operands
     @Test
     public void postMultiply() throws Exception {
         this.mvc.perform(post("/").param("operand1","000")
         .param("operator","*").param("operand2","111"))
             .andExpect(status().isOk())
             .andExpect(view().name("result"))
             .andExpect(model().attribute("result", "0"));
     }
}