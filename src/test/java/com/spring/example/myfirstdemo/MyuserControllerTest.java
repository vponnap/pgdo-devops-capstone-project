package com.spring.example.myfirstdemo;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class MyuserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getNames() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hellodevopscontents").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Welcome to Dockers and K8S, Jenkins, GIT ")));
    }
    @Test
    public void getGreetings() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hellowelcometeam").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Welcome to pg devops folks and K8S and simpli team")));
    }
    @Test
    public void getTeamDetails() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getteamdetails").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"Venkata\",\"desc\":\"Devops\"},{\"name\":\"Vignesh\",\"desc\":\"Devops\"}]"));
    }
}