package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.Post;
import com.esprit.weBank.repository.IPostRepository;

@Service

public class PostService {
	@Autowired
	private IPostRepository postRepository;

	public Post savePost(Post post) {
		return postRepository.save(post);
	}

	public List<Post> findAllPosts() {
		return (List<Post>) postRepository.findAll();
	}

	public Post findPostById(int id) {

		return postRepository.findById(id).orElse(null);
	}

	public void deletePostById(int id) {
		postRepository.deleteById(id);
	}
	
	public Post updatePost(Post post, int id) {
		Post existingPost = findPostById(id);
		if (existingPost != null) {
			existingPost.setUpdatedDate(post.getUpdatedDate());
			existingPost.setContent(post.getContent());
			return postRepository.save(existingPost);
		}		
		return null;
	}
	
	public int getNbrPost() {
		return postRepository.countPost();
	}
	
	public int getLikesByPost(int idPost){
		return postRepository.findReactsByPost("LIKE",idPost);
	}
	
	public int getDislikesByPost(int idPost){
		return postRepository.findReactsByPost("DISLIKE",idPost);
	}
}
