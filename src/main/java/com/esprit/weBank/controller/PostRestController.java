package com.esprit.weBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.esprit.weBank.entities.Post;
import com.esprit.weBank.services.PostService;

@RestController
public class PostRestController {
	@Autowired
	private PostService postService;

	@PutMapping(value = "/createPost")
    public Post createPost(@RequestBody Post post) {
		return postService.savePost(post);
	}
	
	@GetMapping(value = "/getPostById/{id}")
	public Post getPostById(@PathVariable(value = "id") int id) {
		return postService.findPostById(id);
	}

	@GetMapping(value = "/getAllPosts")
	public List<Post> getAllPosts() {
		
		return postService.findAllPosts();
	}
	
	@DeleteMapping(value ="/deletePost/{id}")
	public void deletePost(@PathVariable(value = "id") int id) {
		postService.deletePostById(id);
	}
	
	@PostMapping(value ="/updatePost/{id}")
	public Post updatePost(@PathVariable(value = "id") int id, @RequestBody Post post) {
		return postService.updatePost(post, id);
	}
	
    @GetMapping(value = "/getNbrPost")
	public int getNombreEmployeJPQL() {
		return postService.getNbrPost();
	}
	
    @GetMapping(value = "/getNbrLikes/{idPost}")
	public int getNbrLikes(@PathVariable(value = "idPost") int idPost) {
		return postService.getLikesByPost(idPost);
	}
    
    @GetMapping(value = "/getNbrDislikes/{idPost}")
	public int getNbrDislikes(@PathVariable(value = "idPost") int idPost) {
		return postService.getDislikesByPost(idPost);
	}
}
