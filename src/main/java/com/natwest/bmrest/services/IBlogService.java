package com.natwest.bmrest.services;

import java.util.List;

import com.natwest.bmrest.exception.BlogAlreadyExistsException;
import com.natwest.bmrest.exception.BlogNotFoundException;
import com.natwest.bmrest.model.Blog;

public interface IBlogService 
{
	
	public Blog saveBlog(Blog blogObj) throws BlogAlreadyExistsException;

	public Blog updateBlog(Blog blogObj,int blogId) throws BlogNotFoundException;
	
	public Blog getBlogById(int blogId) throws BlogNotFoundException;
	
	public boolean deleteBlogById(int blogId) throws BlogNotFoundException;
	
	public List<Blog> getAllBlogs();

}
