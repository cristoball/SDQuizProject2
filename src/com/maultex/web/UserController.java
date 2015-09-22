package com.maultex.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.maultex.data.Authenticate;
import com.maultex.data.User;
import com.maultex.data.UserManager;


@Controller
@RequestMapping("/login")
public class UserController
{
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(String username, String password, HttpSession session) 
	{
		System.out.println(username + " " + password);
		Authenticate authenticator = new UserManager();
		User user = null;
		user = authenticator.login(username, password);
		if (user != null)
		{
			//Go to Welcome page
			session.setAttribute("user", user);
			//return new ModelAndView("welcome", "user", user);
			return new ModelAndView("welcome");
		}
		else
		{
			//Go back to Login page
			String errorMsg = "Error: username and password were not found<br/>Please try again!";
			return new ModelAndView("signin", "error", errorMsg);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String onGet(HttpSession session) 
	{
		clearSession(session);
		return "signin";
		
	}

	private void clearSession(HttpSession session)
	{
		session.removeAttribute("user");
		session.removeAttribute("quiz");
	}
}
