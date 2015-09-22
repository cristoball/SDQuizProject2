package com.maultex.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import quiz.data.DataSource;
import quiz.data.Quiz;
import quiz.data.QuizDB;
import quiz.data.QuizInMemory;




@Controller
@RequestMapping("/quiz")
public class QuizController
{

	@RequestMapping(method = RequestMethod.GET)
	//public ModelAndView onGet(HttpSession session) throws Exception
	public String onGet(HttpSession session) throws Exception
	{
		//Quiz quiz = new QuizInMemory();
		Quiz quiz = new QuizDB(DataSource.getConnection());
		session.setAttribute("quiz", quiz);
		//return new ModelAndView("viewquizzes", "quiz", quiz);
		return "viewquizzes";
	}
	
}
