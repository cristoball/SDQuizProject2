package com.maultex.web;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import quiz.data.Question;
import quiz.data.QuizInterface;

@Controller
@RequestMapping("/Results")
public class ResultsController
{
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String onPost(String answers, HttpSession session) {
		System.out.println("answers = " + answers);
		String[] answersAry = this.split(answers, "|");  //java.lang.String.split() not working on answers param??? so made my own
		//System.out.println("length of answersAry: " + answersAry.length);
				
		QuizInterface quiz = (QuizInterface) session.getAttribute("quiz");
		
		//System.out.println(quizSubmitted);
		int i = 0;
		for (Question ques : quiz.getQuestions())
		{
			//System.out.println(answersAry[i]);
			//ques.setGivenAnswer(answersAry[i++]);
			String givenAns = answersAry[i++];
			ques.setGivenAnswer(givenAns);
			
		}
		int nCorrect = this.getTotalCorrect(quiz);
		int nSkipped = this.getTotalSkipped(quiz);
		session.setAttribute("TotalCorrect", nCorrect);
		session.setAttribute("TotalSkipped", nSkipped);
		
		return "resultsRest.jsp";
	}
	
	//simple 'split' method since java's String.split() method not working on url data
	private String[] split(String text, String splitter)
	{
		StringTokenizer sTok = new StringTokenizer(text, splitter);
		int numTokens = sTok.countTokens();
		String[] aryTokens = new String[numTokens];
		
		//System.out.println("numTokens= " + numTokens);
		for (int i = 0; i < numTokens; i++) {
			aryTokens[i] = sTok.nextToken();
		}
			
		return aryTokens;
	}

	/**
	 * Calculate total number of correctly answered questions
	 * @param quiz
	 * @return
	 */
	public int getTotalCorrect(QuizInterface quiz)
	{
		int totalCorrect = 0;
		for (int i = 0; i < quiz.getNumberOfQuestions(); i++)
		{
			String correct = quiz.getQuestions().get(i).getCorrectAnswer().getValue();
			String givenAnswer = quiz.getQuestions().get(i).getGivenAnswer();
			if (correct.equals(givenAnswer))
			{
				totalCorrect++;
			}
		}
		return totalCorrect;
	}
	
	//Get the number of questions skipped
	private int getTotalSkipped(QuizInterface quiz)
	{
		int totalSkipped = 0;
		for (int i = 0; i < quiz.getNumberOfQuestions(); i++)
		{
			String givenAnswer = quiz.getQuestions().get(i).getGivenAnswer();
			System.out.println("Given Answer" + i + ": " + givenAnswer);
			if (givenAnswer.equals("(You did not answer this question)"))
			{
				totalSkipped++;
			}
		}
		return totalSkipped;
	}
}
