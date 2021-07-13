package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.Post;
import com.esprit.weBank.repository.IPostRepository;
import com.esprit.weBank.util.ReactType;

import javassist.expr.NewArray;

@Service

public class PostService {
	@Autowired
	private IPostRepository postRepository;
	

	public Post savePost(Post post) {
		return postRepository.save(post);
	}

	public List<Post> findAllPosts() {
		List<Post> posts = (List<Post>) postRepository.findAll();
		for (int i = 0; i < posts.size(); i++) {
		    posts.get(i).setNbrLikes(getLikesByPost(posts.get(i).getId()));
		    posts.get(i).setNbrDislikes(getDislikesByPost(posts.get(i).getId()));
		    postRepository.save(posts.get(i));
		}
		
		return posts;
	}

	public Post findPostById(int id) {
		postRepository.findById(id).orElse(null).setNbrLikes(getLikesByPost(id));
		postRepository.findById(id).orElse(null).setNbrDislikes(getDislikesByPost(id));
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
	
	/*public void updateReacts(int id) {
		Post existingPost = findPostById(id);
		if (existingPost != null) {
			existingPost.setNbrLikes(existingPost.getNbrLikes()+1);
			existingPost.setNbrDislikes(existingPost.getNbrDislikes()+1);
			 postRepository.save(existingPost);
		}		
	}*/
	
	/*public void updateNbrLikes(int id) {
		Post existingPost = findPostById(id);
		if (existingPost != null) {
			existingPost.setNbrLikes(existingPost.getNbrLikes()+1);
			 postRepository.save(existingPost);
		}		
	}*/
	
	public int getNbrPost() {
		return postRepository.countPost();
	}
	
	public int getLikesByPost(int idPost){
		return postRepository.findReactsByPost(ReactType.LIKE.toString(),idPost);
	}
	
	public int getDislikesByPost(int idPost){
		return postRepository.findReactsByPost(ReactType.DISLIKE.toString(),idPost);
	}
}
