package com.maultex.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import quiz.data.Quiz;
import quiz.data.QuizDB;
import quiz.data.QuizInMemory;




@Controller
@SessionAttributes
@RequestMapping("/quiz")
public class QuizController
{

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView onGet() 
	{
		Quiz quiz = new QuizInMemory();

		return new ModelAndView("viewquizzes", "quiz", quiz);
		
	}
	
}