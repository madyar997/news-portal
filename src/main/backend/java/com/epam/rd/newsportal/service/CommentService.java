package com.epam.rd.newsportal.service;

import com.epam.rd.newsportal.entity.Comment;
import com.epam.rd.newsportal.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

}
