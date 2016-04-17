package com.springcookbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springcookbook.bo.User;
import com.springcookbook.dao.UserDAO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserDAO userDAO;	
	
	@RequestMapping("/{id}/{field}")
	public void showUserField (@PathVariable("id") Long userId, 
			@PathVariable("field") String field){
	}

	@RequestMapping("/list")
	public void userList(Model model) {
		List<User> users = userDAO.findAll();
		
		//model.addAttribute("nbUsers", 13);
		model.addAttribute("users", users);
	}
	
	@RequestMapping("/add")
	public void addUser(Model model){
		String name = "Tom";		
		User user = new User();
		user.setFirstName(name);
		user.setAge(25);	
		userDAO.add(user);
		model.addAttribute("nbUsers", name);
	}
	
	@RequestMapping("/addUser")
	public String addUser(){
		return "addUser";
	}

//	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
//	public String addUserSubmit(HttpServletRequest request){
//		String firstName = request.getParameter("firstName");
//		
//		return "redirect:/home";
//	}
}
