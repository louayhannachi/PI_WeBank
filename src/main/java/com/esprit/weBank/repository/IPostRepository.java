package com.esprit.weBank.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.esprit.weBank.entities.Post;

public interface IPostRepository extends CrudRepository<Post, Integer> {

	Optional <Post> findById(int id);
}
