package com.epam.rd.newsportal.controller;


import com.epam.rd.newsportal.entity.Comment;
import com.epam.rd.newsportal.entity.News;
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

    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllNews(){
        List<Comment> comments = commentService.getComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Comment> addNews(@RequestBody Comment Comment){
        Comment newComment = commentService.addComment(Comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable("id") Long id){
        commentService.deleteCommentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
