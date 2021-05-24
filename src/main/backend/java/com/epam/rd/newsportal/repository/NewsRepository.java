package com.epam.rd.newsportal.repository;

import com.epam.rd.newsportal.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsRepository extends JpaRepository<News, Long> {

}
