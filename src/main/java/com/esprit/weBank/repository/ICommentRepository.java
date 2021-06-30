package com.esprit.weBank.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.esprit.weBank.entities.Comment;

public interface ICommentRepository extends CrudRepository<Comment, Integer> {
	Optional<Comment> findById(int id);
}
