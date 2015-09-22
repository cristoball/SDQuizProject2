package com.maultex.web;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import quiz.data.Question;
import quiz.data.Quiz;

@Controller
@RequestMapping("/questions")
public class QuestionController
{

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView onGetQuestion(String startQuiz, HttpSession session) throws Exception
	{
		//start quiz not currently used
		Quiz quiz = (Quiz)session.getAttribute("quiz");//new QuizDB(conn);
		session.setAttribute("counter", 0);
			
		int nQuestionIndex = 0; //start with first question in quiz.questions (0 based List)
		
		Question question = quiz.getQuestions().get(nQuestionIndex);

		System.out.println("Question Index=" + nQuestionIndex);
		System.out.println("question.getId()=" + question.getId());

		return new ModelAndView("showquestions", "question", question);
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onPostQuestion(String ans, String currentCounter, HttpSession session) throws SQLException, Exception
	{
		System.out.println("Answer = " + ans);
		System.out.println("currentCounter = " + currentCounter);
		int nCounter = Integer.parseInt(currentCounter);

		Quiz quiz = (Quiz) session.getAttribute("quiz");
		System.out.println("Quiz from session =" + quiz + "\n");

		// need to save answer
		if (ans == null)
		{
			ans = "(You did not answer this question)";
		}
		Question question = quiz.getQuestions().get(nCounter);
		System.out.println("Saving given answer: " + ans + ", for Question List Index = " + (nCounter) + "\n");
		question.setGivenAnswer(ans);
		System.out.println("Question List Index =" + (nCounter) + ", has correct answer of : " + question.getCorrectAnswer().getValue() + "\n");
		

		// Do we have more questions to show?
		int nQuestionListSize = quiz.getQuestions().size();
		System.out.println("Current Question:" + nCounter + ", Question List Size:" + nQuestionListSize + "\n");
		
		if (nCounter == nQuestionListSize - 1)// No more questions, so show results
		{
			//DataSource.getConnection().close();
			int nCorrect = getTotalCorrect(quiz);
			ModelAndView mv = new ModelAndView("results");
			mv.addObject("TotalCorrect", nCorrect);
			return mv;
		} 
		else //show next question
		{
			Question nextQuestion = quiz.getQuestions().get(++nCounter);//next question, increment counter
			session.setAttribute("counter", nCounter);
			return new ModelAndView("showquestions", "question", nextQuestion);			
		}
	}
	
	/**
	 * Calculate total number of correctly answered questions
	 * @param quiz
	 * @return
	 */
	public int getTotalCorrect(Quiz quiz)
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
	
}
