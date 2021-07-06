package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.React;
import com.esprit.weBank.repository.IReactRepository;

@Service

public class ReactService {

	@Autowired
	private IReactRepository reactRepository;
	
	public React saveReact(React react) {
		return reactRepository.save(react);
	}
	
	public List<React> findAllReact() {
		return (List<React>) reactRepository.findAll();
	}
	
	public void deleteReactById(int id) {
		reactRepository.deleteById(id);
	}
}
