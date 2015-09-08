package com.maultex.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.maultex.data.Authenticate;
import com.maultex.data.User;
import com.maultex.data.UserManager;


@Controller
@SessionAttributes
//@RequestMapping("/login")
public class UserController
{
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView onSubmit(String username, String password) 
	{
		System.out.println(username + " " + password);
		Authenticate authenticator = new UserManager();
		User user = null;
		user = authenticator.login(username, password);
		if (user != null)
		{
			//Go to Welcome page
			return new ModelAndView("welcome", "user", user);
		}
		else
		{
			//Go back to Login page
			String errorMsg = "Error: username and password were not found<br/>Please try again!";
			return new ModelAndView("signin", "error", errorMsg);
		}
		
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String onGet() 
	{
		return "signin";
		
	}
}
