package com.epam.rd.newsportal.service;

import com.epam.rd.newsportal.entity.Comment;
import com.epam.rd.newsportal.repository.CommentRepository;
import com.epam.rd.newsportal.repository.NewsRepository;
import com.epam.rd.newsportal.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

class CommentServiceTest {

    CommentService commentService;

    @Mock
    CommentRepository commentRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    NewsRepository newsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        commentService = new CommentService(commentRepository, userRepository, newsRepository);

    }

//TODO change names
    @Test
    void getComments() {
        Comment comment = new Comment();
        ArrayList<Comment> commentData = new ArrayList<>();
        commentData.add(comment);
        Long newsId = 138L;
        when(commentRepository.findByNewsId(newsId)).thenReturn(commentData);
        List<Comment> comments = commentService.getComments(newsId);
        assertEquals(comments.size(), 1);
        verify(commentRepository, times(1)).findByNewsId(newsId);
    }

    @Test
    void addComment() {

    }

    @Test
    void deleteCommentById() {
    }
}