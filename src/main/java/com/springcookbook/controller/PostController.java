package com.springcookbook.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springcookbook.bo.Post;
import com.springcookbook.dao.PostDAO;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostDAO postDAO;
	
	@RequestMapping("/list")
	public void postList(Model model) {
		List<Post> posts = postDAO.findAll();
		
		//model.addAttribute("nbUsers", 10);
		model.addAttribute("posts", posts);
	}
	
	@RequestMapping("/add")
	public void addPost(Model model){
		String title = "Some title";		
		Post post = new Post();
		post.setTitle(title);
		long time = System.currentTimeMillis();
		java.sql.Date date = new Date(time);
		post.setDate(date);	
		postDAO.add(post);
		model.addAttribute("nbUsers", title);
	}
	

}
