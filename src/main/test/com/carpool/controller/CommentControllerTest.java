package com.carpool.controller;

import com.carpool.website.controller.CommentController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by qi on 2016/11/30.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration({"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class CommentControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    CommentController commentController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testController_GetComment() throws Exception {
        mockMvc.perform(get("/comment/makeComment?sourceUser.id=1452779&targetUser.id=1452778&journey.id=2&credit=5.0"));
    }

    @Test
    public void test_getReceivedComments() throws Exception {
        mockMvc.perform(get("/comment/getReceivedComment/"+"1452779")).andDo(print());
    }

    @Test
    public void test_getOthersComments()throws Exception
    {
        mockMvc.perform(get("/comment/getOthersComment/1452681")).andDo(print());
    }

    @Test
    public void test_getSendedComments() throws Exception
    {
 //       mockMvc.perform(get("/comment/getSendedComment/1452778")).andDo(print());
     mockMvc.perform(post("/comment/makeComment?journey.id=1000&sourceUser.id=1452681&targetUser.id=1452779&credit=4&commentText=+++++++++++++sd+++++++++++++++"));
    }

}
