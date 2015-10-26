package com.maultex.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import quiz.data.QuizInterface;
import quiz.data.QuizInMemory;

@Controller
@SessionAttributes(value = { "count", "quiz" })
public class QuestionAnswerController
{
	@ModelAttribute("count")
	public int getInitialCount()
	{
		return 0;
	}

	@ModelAttribute("quiz")
	public QuizInterface getInitialQuiz()
	{
		return new QuizInMemory();
	}

	@RequestMapping("/DisplayName.do")
	public ModelAndView getQuizName(@ModelAttribute QuizInterface quiz)
	{
		// Quiz quiz = new QuizInMemory();
		System.out.println(quiz.getName());
		return new ModelAndView("quizname", "name", quiz.getName());

	}

	@RequestMapping("/DisplayNumber.do")
	public ModelAndView getNumberOfQuestions(@ModelAttribute QuizInterface num)
	{
		// Quiz num = new QuizInMemory();
		System.out.println(num.getNumberOfQuestions());
		return new ModelAndView("quiznumberquestions", "number", num.getNumberOfQuestions());

	}

	@RequestMapping("/DisplayQuestions.do")
	public ModelAndView getQuestions(@ModelAttribute("count") int count, @ModelAttribute QuizInterface questions, String answers)
	{
		System.out.println(answers);
		// Quiz questions = new QuizInMemory();

		if (answers != null)
		{
			questions.getQuestions().get(count - 1).setGivenAnswer(answers);
		}

		if (count >= questions.getQuestions().size())
		{
			System.out.println("going to results");
			System.out.println(questions.getResults());
			return new ModelAndView("results", "results", questions.getResults());
		}
		System.out.println(questions.getQuestions());

		ModelAndView mv = new ModelAndView();
		mv.setViewName("questions");
		mv.addObject("question", questions.getQuestions().get(count));
		mv.addObject("count", ++count);

		return mv;
	}

	@RequestMapping("/DisplayResults.do")
	public ModelAndView getResults(@ModelAttribute QuizInterface results)
	{
		// Quiz results = new QuizInMemory();
		System.out.println(results.getResults());
		return new ModelAndView("results", "results", results.getResults());
	}

}
