package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.Comment;
import com.esprit.weBank.repository.ICommentRepository;

@Service

public class CommentService {
	
	@Autowired
	private ICommentRepository commentRepository;

	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public List<Comment> findAllComment() {
		return (List<Comment>) commentRepository.findAll();
	}

}
