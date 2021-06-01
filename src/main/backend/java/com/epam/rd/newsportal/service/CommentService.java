package com.epam.rd.newsportal.service;

import com.epam.rd.newsportal.entity.Comment;
import com.epam.rd.newsportal.entity.News;
import com.epam.rd.newsportal.entity.User;
import com.epam.rd.newsportal.payload.request.CommentRequest;
import com.epam.rd.newsportal.repository.CommentRepository;
import com.epam.rd.newsportal.repository.NewsRepository;
import com.epam.rd.newsportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final NewsRepository newsRepository;

    public List<Comment> getComments(Long newsId) {
        return commentRepository.findByNewsId(newsId);
    }

    public Comment addComment(CommentRequest commentRequest, Long newsId, Long userId) {
        News news = newsRepository.findById(newsId).
                orElseThrow(() -> new RuntimeException("Could not find news with newsId=" + newsId));
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException(("Could not find the user with userId=" + userId)));
        Comment comment = new Comment();
        comment.setBody(commentRequest.getBody());
        comment.setUser(user);
        comment.setNews(news);
        comment.setCreatedDate(new Date());
        return commentRepository.save(comment);
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }
}
