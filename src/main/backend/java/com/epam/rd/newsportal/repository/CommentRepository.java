package com.epam.rd.newsportal.repository;

import com.epam.rd.newsportal.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
