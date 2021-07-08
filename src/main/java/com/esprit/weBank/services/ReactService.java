package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.React;
import com.esprit.weBank.repository.IPostRepository;
import com.esprit.weBank.repository.IReactRepository;
import com.esprit.weBank.util.ReactType;

@Service

public class ReactService {

	@Autowired
	private IReactRepository reactRepository;

	@Autowired
	private IPostRepository postRepository;
	

	public React saveReact(React react) {
			if (react.getReactType().toString().equals(ReactType.LIKE.toString())){
				int nbLikes = postRepository.findReactsByPost(ReactType.LIKE.toString(), react.getPost().getId());
				react.getPost().setNbrLikes(nbLikes);
				
			}else{
				int nbDislikes = postRepository.findReactsByPost(ReactType.DISLIKE.toString(), react.getPost().getId());
				react.getPost().setNbrDislikes(nbDislikes);
			}


		return reactRepository.save(react);
	}

	public List<React> findAllReact() {
		return (List<React>) reactRepository.findAll();
	}

	public void deleteReactById(int id) {
		reactRepository.deleteById(id);
	}
}
