package com.maultex.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import quiz.data.DataSource;
import quiz.data.Question;
import quiz.data.Quiz;
import quiz.data.QuizDB;
import quiz.data.QuizInMemory;

@Controller
@SessionAttributes
@RequestMapping("/questions")
public class QuestionController
{
	/**
	 * Handle start of Questions
	 * 
	 * @param startQuiz
	 * @param quesID
	 * @param answer
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView onGetQuestion(String startQuiz, HttpSession session) throws Exception
	{

		System.out.println("Creating new Quiz object");
		//Quiz quiz = new QuizInMemory();
		
		Connection conn = DataSource.getConnection();
		Quiz quiz = (Quiz)session.getAttribute("quiz");//new QuizDB(conn);
		session.setAttribute("quiz", quiz);
		// session.setAttribute("count", 1);
		
		int nQuestionID = 1;
		int nQuestionIndex = 0;
		
		Question question = quiz.getQuestions().get(nQuestionIndex);

		System.out.println("Question Index=" + nQuestionIndex);
		System.out.println("question.getId()=" + question.getId());

		return new ModelAndView("showquestions", "question", question);
	}

	/**
	 * Handle Submission of Questions
	 * 
	 * @param ans
	 * @param quesID
	 * @param session
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onPostQuestion(String ans, String currentQuestionID, HttpSession session) throws SQLException, Exception
	{
		System.out.println("Answer = " + ans);
		System.out.println("quesID = " + currentQuestionID);
		int questionID = Integer.parseInt(currentQuestionID);
		int questionListIndex = questionID - 1;

		Quiz quiz = (Quiz) session.getAttribute("quiz");
		System.out.println("Quiz from session =" + quiz + "\n");

		if (ans != null) // need to save answer
		{
			Question question = quiz.getQuestions().get(questionListIndex);
			System.out.println("Saving answer: " + ans + ", for Question List Index = " + (questionListIndex) + "\n");
			question.setGivenAnswer(ans);
			System.out.println("Question List Index =" + (questionListIndex) + ", has correct answer of : " + question.getCorrectAnswer().getValue() + "\n");
		}

		// Do we have more questions to show?
		int nQuestionListSize = quiz.getQuestions().size();
		System.out.println("Current Question:" + questionListIndex + ", Question List Size:" + nQuestionListSize + "\n");
		
		if (questionListIndex == nQuestionListSize - 1)// No more questions
		{
			DataSource.getConnection().close();
			return new ModelAndView("results", "quiz", quiz);	
		} 
		else 
		{
			Question nextQuestion = quiz.getQuestions().get(questionListIndex + 1);

			return new ModelAndView("showquestions", "question", nextQuestion);			
		
		}

	}
}
