package com.carpool.controller;

import com.carpool.website.controller.JourneyController;
import com.carpool.website.dao.JourneyEntityRepository;
import com.carpool.website.dao.RoomEntityRepository;
import com.carpool.website.service.RoomService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by qi on 2016/12/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration({"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class JourneyControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    JourneyController journeyController;

    @Autowired
    RoomService roomService;
    @Autowired
    JourneyEntityRepository journeyEntityRepository;
    @Autowired
    RoomEntityRepository roomEntityRepository;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void test_getJourneyCommentDetail() throws Exception {
        mockMvc.perform(get("/journey/getJourneyCommentDetail/3?userid=1452778")).andDo(print());
    }

    @Test
    public void test_getAllJourneyAsHost() throws Exception {
        mockMvc.perform(get("/journey/getMyJourneyAsHost/1452779")).andDo(print())
        .andExpect(view().name("journey/asHostJourneys"));
    }

    @Test
    public void test_getAllJourneys() throws Exception
    {
        mockMvc.perform(get("/journey/getAllJourneys/1452778")).andDo(print())
        .andExpect(view().name("journey/journeys"));
    }

    @Test
    public void test_getMyTrack()throws Exception
    {
        mockMvc.perform(get("/journey/getMyTrack/1452779")).andDo(print());
    }

    @Test
    public void test_generateJourney()throws Exception
    {
        mockMvc.perform(get("/journey/generateJourneyFromRoom/64")).andDo(print());
    }
}
