package com.esprit.weBank.services;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.Post;
import com.esprit.weBank.repository.IPostRepository;
import com.esprit.weBank.util.ReactType;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@EnableScheduling
@Service

public class PostService {
	Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private IPostRepository postRepository;
	@Autowired
	private JavaMailSender emailSender;


	public Post savePost(Post post) throws FileNotFoundException {

		Scanner wordFile = new Scanner(
				new FileInputStream("C:\\Users\\mmahmoudi\\Desktop\\weBank\\PI_WeBank\\badw.txt"));

		String content = post.getContent();
		String init = "";
		while (wordFile.hasNextLine()) {
			String badWordline = wordFile.nextLine();

			if (content.contains(badWordline)) {
				String charRep = "";
				String wordRep = "";

				for (int i = 1; i < badWordline.length() - 1; i++) {
					charRep += "*";
				}
				wordRep = badWordline.charAt(0) + charRep + badWordline.charAt(badWordline.length() - 1);
				content = content.replace(badWordline, wordRep);
				// init=newBad;
			}
		}
		post.setContent(content);
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
		Post post = findPostById(id);
		updatePostNull(post, id);
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

	public Post updatePostNull(Post post, int id) {
		Post existingPost = findPostById(id);
		if (existingPost != null) {
			existingPost.setUser(null);
			return postRepository.save(existingPost);
		}
		return null;
	}

	/*
	 * public void updateReacts(int id) { Post existingPost = findPostById(id);
	 * if (existingPost != null) {
	 * existingPost.setNbrLikes(existingPost.getNbrLikes()+1);
	 * existingPost.setNbrDislikes(existingPost.getNbrDislikes()+1);
	 * postRepository.save(existingPost); } }
	 */

	/*
	 * public void updateNbrLikes(int id) { Post existingPost =
	 * findPostById(id); if (existingPost != null) {
	 * existingPost.setNbrLikes(existingPost.getNbrLikes()+1);
	 * postRepository.save(existingPost); } }
	 */

	public int getNbrPost() {
		return postRepository.countPost();
	}

	public int getLikesByPost(int idPost) {
		return postRepository.findReactsByPost(ReactType.LIKE.toString(), idPost);
	}

	public int getDislikesByPost(int idPost) {
		return postRepository.findReactsByPost(ReactType.DISLIKE.toString(), idPost);
	}

	public String getBestPost() {
		List<Post> posts = (List<Post>) postRepository.findAll();
		for (int i = 0; i < posts.size(); i++) {
			posts.get(i).setNbrLikes(getLikesByPost(posts.get(i).getId()));
			posts.get(i).setNbrDislikes(getDislikesByPost(posts.get(i).getId()));
			postRepository.save(posts.get(i));
		}
		return postRepository.bestPost();
	}

	/*@Transactional
	@Scheduled(fixedRate = 10000)
	public void bestPostMail() {

		 
		String info = getBestPost();
		List<String> infos = Arrays.asList(info.split(","));
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("mmelekmahmoudi@gmail.com");

		msg.setSubject("best post");
		msg.setText("\n id of best post is: "+infos.get(0)+" \n content of post : "+infos.get(1)+" \n got "+infos.get(2)+ " likes ");

		emailSender.send(msg);
		

		 
		    

	    //logger.info(" \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n id of best post is: "+infos.get(0)+" \n content of post : "+infos.get(1)+" \n got "+infos.get(2)+ " likes \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
	}*/

}
