package com.esprit.weBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.weBank.entities.Comment;
import com.esprit.weBank.services.CommentService;

@RestController
public class CommentRestController {

	@Autowired
	private CommentService commentService;
	
	@PutMapping(value = "/createComment")
    public Comment createComment(@RequestBody Comment post) {
		return commentService.saveComment(post);
	}
	
	@GetMapping(value = "/getAllComments")
	public List<Comment> getAllComments() {
		return commentService.findAllComment();
	}
	
	@DeleteMapping(value ="/deleteComment/{id}")
	public void deleteComment(@PathVariable(value = "id") int id) {
		commentService.deleteCommentById(id);
	}
	
}
