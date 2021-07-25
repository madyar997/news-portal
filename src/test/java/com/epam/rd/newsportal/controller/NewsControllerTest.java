package com.epam.rd.newsportal.controller;

import com.epam.rd.newsportal.entity.News;
import com.epam.rd.newsportal.repository.NewsRepository;
import com.epam.rd.newsportal.security.SecurityConfig;
import com.epam.rd.newsportal.security.jwt.AuthEntryPointJwt;
import com.epam.rd.newsportal.security.jwt.JwtUtils;
import com.epam.rd.newsportal.service.NewsService;
import com.epam.rd.newsportal.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

import javax.ws.rs.core.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(NewsController.class)
@ContextConfiguration(classes = {Application.class, SecurityConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
class NewsControllerTest {


    //    @Autowired
//    private MockMvc mockMvc;
//    @Mock
//    NewsRepository newsRepository;
//    @Mock
//    AuthEntryPointJwt unauthorizedHandler;
    @Mock
    NewsService newsService;
    //    @Mock
//    JwtUtils jwtUtils;
//    @Mock
//    UserDetailsServiceImpl userDetailsService;
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
        ResponseEntity newsResponseEntityActual = new ResponseEntity<>(objectMapper.readValue(resultContent, News.class), HttpStatus.OK);
        Assertions.assertEquals(newsResponseEntityExpected, newsResponseEntityActual);
    }

    @Test
    void ShouldReturnNewsResponseEntities() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
//        News news = new News(1L, "test_title1", "2021-07-21 14:13:15.000000", "text_text1");
////        ResponseEntity<News> newsResponseEntity = new ResponseEntity<>(news, HttpStatus.OK);
//        when(newsService.getNewsById(1L)).thenReturn(news);
        mockMvc.perform(get("/news/all").content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }


    //
//    //harmcrest
//    @Test
//    void getAllNewsShouldReturnNewsEntries() {
////        List<News> newsList = new ArrayList<>();
////        News news = new News("test_title1", "2021-07-21 14:13:15.000000", "text_text1");
////        News news1 = new News("test_title2", "2021-07-21 14:13:15.000000", "text_text2");
////        newsList.add(news);
////        newsList.add(news1);
////        ResponseEntity<List<News>> newsListRE = new ResponseEntity<>(newsList, HttpStatus.OK);
////        when(newsController.getAllNews()).thenReturn(newsListRE);
////        assertEquals(newsController.getAllNews().getBody().size(), newsList.size());
////
////        assertEquals(newsController.getAllNews().getBody().get(0).getTitle(), newsList.get(0).getTitle());
////        assertEquals(newsController.getAllNews().getBody().get(0).getCreatedDate(), newsList.get(0).getCreatedDate());
////        assertEquals(newsController.getAllNews().getBody().get(0).getText(), newsList.get(0).getText());
////
////        assertEquals(newsController.getAllNews().getBody().get(1).getTitle(), newsList.get(1).getTitle());
////        assertEquals(newsController.getAllNews().getBody().get(1).getCreatedDate(), newsList.get(1).getCreatedDate());
////        assertEquals(newsController.getAllNews().getBody().get(1).getText(), newsList.get(1).getText());
//
//    }
//
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
        ResponseEntity newsResponseEntityActual = new ResponseEntity<>(objectMapper.readValue(resultContent, News.class), HttpStatus.OK);
        Assertions.assertEquals(newsResponseEntityExpected, newsResponseEntityActual);
    }
//
//    @Test
//    void updateNews() {
//    }
//
//    @Test
//    void deleteNews() {
//    }
}