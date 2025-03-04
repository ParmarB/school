package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    // testing for adding 2 binary numbers but no operands provided and result is a string
    @Test
    public void addTest3() throws Exception {
        this.mvc.perform(get("/add").param("operand1","").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    // testing for adding 2 operands and resultant string output
    @Test
    public void addTest4() throws Exception {
        this.mvc.perform(get("/add").param("operand1","011")
        .param("operand2","000"))
            .andExpect(status().isOk())
            .andExpect(content().string("11"));
    }

    //testing OR with 2 operands
    @Test
    public void bitWiseOrTest() throws Exception {
        this.mvc.perform(get("/or").param("operand1","011")
        .param("operand2","000"))
            .andExpect(status().isOk())
            .andExpect(content().string("11"));
    }

    //testing AND with 2 operands
    @Test
    public void ANDtest() throws Exception {
        this.mvc.perform(get("/and").param("operand1","011")
        .param("operand2","000"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    //tesitng multiply with 2 operands
    @Test
    public void mulitplyTest() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1","011")
        .param("operand2","000"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }




    //testing for adding 2 operands and resultant json object as output
    @Test
    public void addTest5() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","11")
        .param("operand2","000"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(11))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(000))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(11))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    //testing for OR of 2 operands and resultant json object as output
    @Test
    public void bitWiseOrTest2() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","11")
        .param("operand2","000"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(11))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(000))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(11))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    //testing for AND of 2 operands and resultant json object as output
    @Test
    public void ANDtest2() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","11")
        .param("operand2","000"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(11))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(000))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }

    //testing for Multiply of 2 operands and resultant json object as output
    @Test
    public void mulitplyTest2() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","11")
        .param("operand2","000"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(11))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(000))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }
}