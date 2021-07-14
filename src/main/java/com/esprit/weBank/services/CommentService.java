package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.Comment;
import com.esprit.weBank.entities.React;
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

	public void deleteCommentById(int id) {
		Comment Comment = findCommentById(id);
		updateCommentNull(Comment, id);
		commentRepository.deleteById(id);
	}
	
	/*public void resetCommentPostById(int id)
	{
		Comment comment = commentRepository.findById(id).get();
		comment.setPost(null);			
	}*/
	
	
	public Comment findCommentById(int id) {

		return commentRepository.findById(id).orElse(null);
	}
	
	public Comment updateComment(Comment comment, int id) {
		Comment existingComment = findCommentById(id);
		if (existingComment != null) {
			existingComment.setUpdatedDate(comment.getUpdatedDate());
			existingComment.setContent(comment.getContent());
			return commentRepository.save(existingComment);
		}		
		return null;
	}
	
	public Comment updateCommentNull(Comment comment, int id) {
		Comment existingComment = findCommentById(id);
		if (existingComment != null) {
			existingComment.setPost(null);
			return commentRepository.save(existingComment);
		}
		return null;
	}
}
