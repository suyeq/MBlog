package com.cyc.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
public class IndexControllerTest
{

    @InjectMocks
    private IndexController indexController;

    private MockMvc mockMvc;

//    @Autowired
//    protected WebApplicationContext webApplicationContext;

    @Before
    public void setup()
    {

        // initialize mock object
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
//        MockMvcBuilders.webAppContextSetup().build();
    }

    @Test
    public void showIndex() throws Exception
    {
        String responseString = mockMvc.perform(get("/index.html")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("pcode", "root"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println("--------返回的json = " + responseString);
    }
}