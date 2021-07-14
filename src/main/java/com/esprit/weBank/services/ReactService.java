package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.Post;
import com.esprit.weBank.entities.React;
import com.esprit.weBank.repository.IPostRepository;
import com.esprit.weBank.repository.IReactRepository;
import com.esprit.weBank.util.ReactType;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service

public class ReactService {

	@Autowired
	private IReactRepository reactRepository;

	@Autowired
	private IPostRepository postRepository;
	
	PostService postService;
	
	public static final String ACCOUNT_SID = "ACb54e4537bc0c89c12793d12999cc4f16";
	public static final String AUTH_TOKEN = "682bac31ba7914533077f96a92b252b5";

	public React saveReact(React react) {
			if (react.getReactType().toString().equals(ReactType.LIKE.toString())){
				int nbLikes = postRepository.findReactsByPost(ReactType.LIKE.toString(), react.getPost().getId());
				react.getPost().setNbrLikes(nbLikes+1);
				
			}else{
				int nbDislikes = postRepository.findReactsByPost(ReactType.DISLIKE.toString(), react.getPost().getId());
				react.getPost().setNbrDislikes(nbDislikes+1);
			}
			//postRepository.save(react.getPost());
			String rType = react.getReactType().toString();
			String content = postRepository.findById(react.getPost().getId()).get().getContent();
			int numberRL = postRepository.findById(react.getPost().getId()).get().getNbrLikes();
			int numberRD = postRepository.findById(react.getPost().getId()).get().getNbrDislikes();
			if (rType.equals(ReactType.LIKE.toString())){
				numberRL +=1 ;
			}else if ((rType.equals(ReactType.DISLIKE.toString()))) {
				//numberRL =numberRL ;
				numberRD +=1 ;

			}
			
			

			   Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
		        Message message = Message.creator( 
		                new com.twilio.type.PhoneNumber("+21625360770"),  
		                "MGe97d6676521e559f39129a2743666c43", 
		                "\n \n you got new "+rType+ " \n on post that said : "+content+" \n so you got "+ numberRL+ " likes and "+numberRD+" dislikes")      
		            .create(); 
		 
		        System.out.println(message.getSid()); 


		return reactRepository.save(react);
	}

	public List<React> findAllReact() {
		return (List<React>) reactRepository.findAll();
	}

	public React findReactById(int id) {
		return reactRepository.findById(id).orElse(null);
	}

	public void deleteReactById(int id) {
		React react = findReactById(id);
		updateReactNull(react, id);
		reactRepository.deleteById(id);
	}
	
	public React updateReactNull(React react, int id) {
		React existingReact = findReactById(id);
		if (existingReact != null) {
			existingReact.setPost(null);
			return reactRepository.save(existingReact);
		}
		return null;
	}

}
