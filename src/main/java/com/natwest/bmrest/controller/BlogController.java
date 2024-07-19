package com.natwest.bmrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natwest.bmrest.exception.BlogAlreadyExistsException;
import com.natwest.bmrest.exception.BlogNotFoundException;
import com.natwest.bmrest.model.Blog;
import com.natwest.bmrest.services.IBlogService;

@RestController
@RequestMapping("/blogapi/v1")
public class BlogController 
{
	@Autowired
	private IBlogService blogservice;
	
	private ResponseEntity<?> responseentity;
	
	@PostMapping("/addBlog")
	public ResponseEntity<?> saveBlogHandler(@RequestBody Blog blogObj)
	{
		System.out.println("Inside Controller  "+ blogObj);
		
		Blog bObj;
		try {
			bObj = this.blogservice.saveBlog(blogObj);
			responseentity = new ResponseEntity(bObj,HttpStatus.CREATED);
		} catch (BlogAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseentity = new ResponseEntity("Some Internal Error Occured adding the data.." + e.getMessage(),HttpStatus.CONFLICT);
		}
		
		return responseentity;
	}
	
	
	@PutMapping("/updateBlog/{blogId}")
	public ResponseEntity<?> updateBlogHandler(@RequestBody Blog blogObj, @PathVariable int blogId)
	{
		Blog bObj;
		try {
			bObj = this.blogservice.updateBlog(blogObj, blogId);
			responseentity = new ResponseEntity(bObj,HttpStatus.CREATED);
		} catch (BlogNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseentity = new ResponseEntity("Some Internal Error Occured while updating the Data.." + e.getMessage(),HttpStatus.CONFLICT);
		}

		return responseentity;
	}
	
	
	@GetMapping("/getBlogs")
	public ResponseEntity<?> getBlogHandler()
	{
		List<Blog> bObj = this.blogservice.getAllBlogs();
		responseentity = new ResponseEntity(bObj,HttpStatus.OK);
		return responseentity;
	}
	
	@GetMapping("/getBlogById/{blogId}")
	public ResponseEntity<?> getBlogByIdHandler(@PathVariable int blogId)
	{
		Blog bObj;
		try {
			bObj = this.blogservice.getBlogById(blogId);
			responseentity = new ResponseEntity(bObj,HttpStatus.OK);
		} catch (BlogNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseentity = new ResponseEntity("Some Internal Error Occured while getting data by Id .." + e.getMessage(),HttpStatus.NOT_FOUND);
		}

		return responseentity;
	}
	
	@DeleteMapping("/delBlogById/{blogId}")
	public ResponseEntity<?> delBlogByIdHandler(@PathVariable int blogId)
	{
		try {
			boolean status = this.blogservice.deleteBlogById(blogId);
			responseentity = new ResponseEntity("Blog Deleted ....",HttpStatus.OK);
		} catch (BlogNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseentity = new ResponseEntity("Some Internal Error Occured while deleting .." + e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
		return responseentity;
	}
}
