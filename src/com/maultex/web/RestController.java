package com.maultex.web;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import quiz.data.Question;
import quiz.data.QuizInterface;

@Controller
@RequestMapping("/Rest")
public class RestController
{

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView onGetQuestion(String startQuiz, HttpSession session) throws Exception
	{
		//start quiz not currently used
		QuizInterface quiz = (QuizInterface)session.getAttribute("quiz");//new QuizDB(conn);
		session.setAttribute("counter", 0);
			
		int nQuestionIndex = 0; //start with first question in quiz.questions (0 based List)
		
		Question question = quiz.getQuestions().get(nQuestionIndex);
		
		return new ModelAndView("questionsRest", "question", question);
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onPostQuestion(String ans, String currentCounter, HttpSession session) throws SQLException, Exception
	{
		System.out.println("Answer = " + ans);
		System.out.println("currentCounter = " + currentCounter);
		int nCounter = Integer.parseInt(currentCounter);

		QuizInterface quiz = (QuizInterface) session.getAttribute("quiz");
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
			int nSkipped = getTotalSkipped(quiz);
			ModelAndView mv = new ModelAndView("results");
			mv.addObject("TotalCorrect", nCorrect);
			mv.addObject("TotalSkipped", nSkipped);
			return mv;
		} 
		else //show next question
		{
			Question nextQuestion = quiz.getQuestions().get(++nCounter);//next question, increment counter
			session.setAttribute("counter", nCounter);
			return new ModelAndView("showquestions", "question", nextQuestion);			
		}
	}
	
	
	//Ajax REST get Quiz as JSON object
	@RequestMapping(method = RequestMethod.GET, params="question")
	public @ResponseBody String onNextQuestion(HttpSession session) throws Exception
	{
		QuizInterface quiz = (QuizInterface) session.getAttribute("quiz");
		
//		TestJson test = new TestJson();
//		System.out.println("HIT call for Quiz json: ");
//		return test;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();  //FORCING OBJECT TO JSON SINCE SPRING IS NOT PICKING UP JACKSON
		String json = ow.writeValueAsString(quiz);
		return json;
	}
	
	//Ajax REST get Quiz as JSON object
	@RequestMapping(method = RequestMethod.GET, params="quiz")
	public @ResponseBody String onGetQuiz(HttpSession session) throws Exception
	{
		QuizInterface quiz = (QuizInterface) session.getAttribute("quiz");
		
//		TestJson test = new TestJson();
//		System.out.println("HIT call for Quiz json: ");
//		return test;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();  //FORCING OBJECT TO JSON SINCE SPRING IS NOT PICKING UP JACKSON
		String json = ow.writeValueAsString(quiz);
		return json;
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

	@RequestMapping(method=RequestMethod.GET, params="correct")
	@ResponseBody
	public String getTotalCorrect(HttpSession session)
	{
		QuizInterface quiz = (QuizInterface) session.getAttribute("quiz");
		System.out.println("Ajax REST get quiz correct for quiz =" + quiz) ;
		int nCorrect = getTotalCorrect(quiz);
		String sCorrect = Integer.toString(nCorrect);
		System.out.println("Total Correct: " + sCorrect);
		return sCorrect;
		
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
	
}


