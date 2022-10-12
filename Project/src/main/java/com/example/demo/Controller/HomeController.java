 package com.example.demo.Controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.User;
import com.example.demo.repository.ProjectRepo;

@Controller
public class HomeController {

	@Autowired
	ProjectRepo repo;
	
	@RequestMapping("/")
	public String getHome()
	{
		return "Home.html";
	}
	
	@RequestMapping("/login")
	public String login()
	{
		return "Login.html";
	}
	
	@RequestMapping("/new")
	public String register()
	{
		return "Register.html";
	}
	
	@PostMapping(value = "/save")
	public String saveUser(@ModelAttribute("user") User user) {
        repo.save(user);
        return "redirect:/";
    }
	
	@PostMapping("/welcome")
	public ModelAndView welcome(@ModelAttribute("user") User user)
	{
		ModelAndView mv=new ModelAndView();
		if(Objects.isNull(repo.findByEmail(user.getEmail())))
		{
			mv.addObject("nn", "User with given email is not present!! Please Register");
			mv.setViewName("Login.html");
			return mv;
		}
		else {
			String email=user.getEmail();
			User dataUser=repo.findByEmail(email);
			if(user.getPass().equals(dataUser.getPass()))
			{
			
			mv.addObject("result",repo.findByEmail(user.getEmail()).getFname());
			mv.setViewName("Welcome.html");
			return mv;
			}
			else {
				mv.addObject("nn", "Password or Email incorrect");
				mv.setViewName("Login.html");
				return mv;
			}
	}
	}
}
