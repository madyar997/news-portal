package com.epam.rd.newsportal.controller;


import com.epam.rd.newsportal.entity.Comment;
import com.epam.rd.newsportal.entity.News;
import com.epam.rd.newsportal.entity.User;
import com.epam.rd.newsportal.payload.request.CommentRequest;
import com.epam.rd.newsportal.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {
    public final CommentService commentService;


    @GetMapping("/{newsId}")
    public ResponseEntity<List<Comment>> getAllNews(
            @PathVariable(name = "newsId") Long newsId) {
        List<Comment> comments = commentService.getComments(newsId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/{newsId}/{userId}")
    public ResponseEntity<Comment> addNews(@Curreent User user,
                                           @RequestBody CommentRequest commentRequest,
                                           @PathVariable(name = "newsId") Long newsId,
                                           @PathVariable(name = "userId") Long userId) {
        Comment newComment = commentService.addComment(commentRequest, newsId, userId);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable("id") Long id) {
        commentService.deleteCommentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
