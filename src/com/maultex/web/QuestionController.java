package com.maultex.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import quiz.data.Question;
import quiz.data.Quiz;
import quiz.data.QuizDB;
import quiz.data.QuizInMemory;




@Controller
@SessionAttributes
@RequestMapping("/questions")
public class QuestionController
{
	//test comments
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView onGetQuestion(String startQuiz, String questionID)
	{
		System.out.println("startQuiz=" + startQuiz + ", questionID=" + questionID);
		int quizID = Integer.parseInt(startQuiz);
		int quesID = Integer.parseInt(questionID);
		
		Quiz quiz = new QuizInMemory();
		
		Question question = quiz.getQuestions().get(0);
		return new ModelAndView("showquestions", "question", question);
	}
}
