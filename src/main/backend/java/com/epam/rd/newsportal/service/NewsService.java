package com.epam.rd.newsportal.service;

import com.epam.rd.newsportal.entity.News;
import com.epam.rd.newsportal.exception.NewsNotFoundException;
import com.epam.rd.newsportal.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public News addNews(News news){
        return newsRepository.save(news);
    }

    public List<News> getNews(){
        return newsRepository.findAll();
    }

    public News getNewsById(Long id) throws NewsNotFoundException {
        return newsRepository.findById(id).orElseThrow(()->new NewsNotFoundException("News not found"));
    }

    public void deleteNewsById(Long id){
        newsRepository.deleteById(id);
    }

    public News updateNews(News news){
        return newsRepository.save(news);
    }

}
