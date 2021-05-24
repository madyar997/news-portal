package com.epam.rd.newsportal.controller;

import com.epam.rd.newsportal.entity.News;
import com.epam.rd.newsportal.exception.NewsNotFoundException;
import com.epam.rd.newsportal.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
@CrossOrigin("*")
public class NewsController {

    public final NewsService newsService;


    @GetMapping("/all")
    public ResponseEntity<List<News>> getAllNews(){
        List<News> news = newsService.getNews();
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<News> getNews(@PathVariable("id") Long id){
        News news = null;
        try {
            news = newsService.getNewsById(id);
        } catch (NewsNotFoundException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<News> addNews(@RequestBody News news){
        News newNews = newsService.addNews(news);
        return new ResponseEntity<>(newNews, HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<News> updateNews(@RequestBody News news){
        News updatedNews = newsService.updateNews(news);
        return new ResponseEntity<>(updatedNews, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteNews(@PathVariable("id") Long id){
        newsService.deleteNewsById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

