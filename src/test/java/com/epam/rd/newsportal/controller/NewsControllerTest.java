package com.epam.rd.newsportal.controller;

import com.epam.rd.newsportal.entity.News;
import com.epam.rd.newsportal.exception.NewsNotFoundException;
import com.epam.rd.newsportal.security.SecurityConfig;
import com.epam.rd.newsportal.service.NewsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.ws.rs.core.Application;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(classes = {Application.class, SecurityConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
class NewsControllerTest {

    @Mock
    NewsService newsService;

    ObjectMapper objectMapper = new ObjectMapper();
    NewsController newsController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        newsController = new NewsController(newsService);

    }

    @Test
    void shouldReturnResponseEntity() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
        News news = new News(1L, "test_title1", "2021-07-21 14:13:15.000000", "text_text1");
        ResponseEntity<News> newsResponseEntityExpected = new ResponseEntity<>(news, HttpStatus.OK);
        when(newsService.getNewsById(1L)).thenReturn(news);
        MvcResult result = mockMvc.perform(get("/news/find/1").content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ResponseEntity<News> newsResponseEntityActual = new ResponseEntity<>(objectMapper.readValue(resultContent, News.class), HttpStatus.OK);
        Assertions.assertEquals(newsResponseEntityExpected, newsResponseEntityActual);
    }

    @Test
    void ShouldReturnNewsResponseEntities() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
        News news = new News(1L, "test_title1", "2021-07-21 14:13:15.000000", "text_text1");
        News news1 = new News(2L, "test_title1", "2021-07-21 14:13:15.000000", "text_text1");
        List<News> newsListExpected = new ArrayList<>();
        newsListExpected.add(news);
        newsListExpected.add(news1);
        when(newsService.getNews()).thenReturn(newsListExpected);
        MvcResult result = mockMvc.perform(get("/news/all").content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<News> newsListActual = objectMapper.readValue(
                resultContent, objectMapper.getTypeFactory().constructParametricType(List.class, News.class));
        Assertions.assertEquals(newsListExpected, newsListActual);
    }

    @Test
    void shouldReturnNewsNotFoundException() throws NewsNotFoundException {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
        when(newsService.getNewsById(1L)).thenThrow(NewsNotFoundException.class);
        Assertions.assertThrows(NewsNotFoundException.class, () -> {
            MvcResult result = mockMvc.perform(get("/news/find/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
        });
    }

    @Test
    void addNews() throws Exception {
        News news = new News(1L, "test_title1", "2021-07-21 14:13:15.000000", "text_text1");
        ResponseEntity<News> newsResponseEntityExpected = new ResponseEntity<>(news, HttpStatus.OK);
        String jsonRequest = objectMapper.writeValueAsString(news);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
        when(newsService.addNews(news)).thenReturn(news);
        MvcResult result = mockMvc.perform(
                post("/news/add")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ResponseEntity<News> newsResponseEntityActual = new ResponseEntity<>(objectMapper.readValue(resultContent, News.class), HttpStatus.OK);
        Assertions.assertEquals(newsResponseEntityExpected, newsResponseEntityActual);
    }

}