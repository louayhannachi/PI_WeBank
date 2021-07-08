package com.esprit.weBank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.esprit.weBank.entities.Post;


public interface IPostRepository extends CrudRepository<Post, Integer> {

	Optional <Post> findById(int id);
	
	@Query("SELECT count(*) FROM post")
    public int countPost();
	
	
	@Query(value="select count(*) list  from ( "
			+ "SELECT post.id, react.react_Type FROM post "
			+ "INNER JOIN react ON post.id = react.post_fk "
			+ "and react.react_Type=:reactType "
			+ "and post.id=:idPost) reactCount", nativeQuery = true)
	public int findReactsByPost(@Param("reactType") String reactType, @Param("idPost") int idPost);
}
