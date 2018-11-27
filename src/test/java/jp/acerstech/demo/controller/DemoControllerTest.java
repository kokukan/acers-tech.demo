package jp.acerstech.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoControllerTest {

    private MockMvc mockMvc;
    @Autowired
    DemoController target;

    @Before
    public void setup(){

        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
    }
    @Test
    public void getAllUsers() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("userlist"));
    }

    @Test
    public void getUserList() {
    }
}