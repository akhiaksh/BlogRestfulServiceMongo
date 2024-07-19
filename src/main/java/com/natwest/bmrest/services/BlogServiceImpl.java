package com.natwest.bmrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natwest.bmrest.exception.BlogAlreadyExistsException;
import com.natwest.bmrest.exception.BlogNotFoundException;
import com.natwest.bmrest.model.Blog;
import com.natwest.bmrest.repository.IBlogRepository;

@Service
public class BlogServiceImpl implements IBlogService {

	
	@Autowired
	private IBlogRepository blogrepo;
	
	@Override
	public Blog saveBlog(Blog blogObj) throws BlogAlreadyExistsException
	{
		Optional<Blog> optionalblog = this.blogrepo.findById(blogObj.getBlogId());
		
		Blog blogadded=null;
		
		if(optionalblog.isPresent())
		{
			System.out.println("Blog with the following blogId is already exists ...");
			throw new BlogAlreadyExistsException("Blog Details Already Available inside the database..");
		}
		else
		{
			blogadded = this.blogrepo.save(blogObj);
		}

		return blogadded;
	}

	@Override
	public Blog updateBlog(Blog blogObj, int blogId) throws BlogNotFoundException
	{
		
		Optional<Blog> optionalblog = this.blogrepo.findById(blogId);

		Blog blogObjdata=null;
		Blog blogupdated=null;
		
		if(optionalblog.isPresent())
		{
			blogObjdata =  optionalblog.get();
			
			blogObjdata.setBlogTitle(blogObj.getBlogTitle());
			blogObjdata.setBlogContent(blogObj.getBlogContent());

			blogupdated = this.blogrepo.save(blogObjdata);
		}
		else
		{
			System.out.println("Blog with the following blogId does not exists ...");
			throw new BlogNotFoundException("Blog Details Does Not Exists inside the database..");
		}
		
		return blogupdated;
	}

	@Override
	public Blog getBlogById(int blogId) throws BlogNotFoundException
	{
		
		Optional<Blog> optionalblog = this.blogrepo.findById(blogId);
		
		Blog blogdata=null;
		
		if(optionalblog.isPresent())
		{
			System.out.println("Blog with the following blogId is already exists ...");
			blogdata = optionalblog.get();
		}
		else
		{
			System.out.println("Blog with the following blogId does not exists ...");
			throw new BlogNotFoundException("Blog Details Does Not Exists inside the database..");
		}
		
		return blogdata;
	}

	@Override
	public boolean deleteBlogById(int blogId) throws BlogNotFoundException
	{
		
		Optional<Blog> optionalblog = this.blogrepo.findById(blogId);
		
		boolean blogstatus= false;
		
		if(optionalblog.isPresent())
		{
			this.blogrepo.delete(optionalblog.get());
			blogstatus = true;
		}
		else
		{
			System.out.println("Blog with the following blogId does not exists ...");
			throw new BlogNotFoundException("Blog Details Does Not Exists inside the database..");
		}
		
		return blogstatus;
		
	}

	@Override
	public List<Blog> getAllBlogs() {
		
		return this.blogrepo.findAll();
		
	}
}
