package com.natwest.bmrest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.natwest.bmrest.model.Blog;

@Repository
public interface IBlogRepository extends MongoRepository<Blog, Integer> {

	
	
}
