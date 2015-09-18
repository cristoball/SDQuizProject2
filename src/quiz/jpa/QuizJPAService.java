package quiz.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class QuizJPAService
{
	protected EntityManager em;

	public QuizJPAService(EntityManager em)
	{
		this.em = em;
	}

	public List<Question> findAllQuestions()
	{
		Query query = em.createQuery("SELECT q FROM Question q");
		return (List<Question>) query.getResultList();
	}

	public List<Question> findQuestionsByQuiz(Quiz quiz)
	{
		Query query = em.createQuery("SELECT q FROM Question q");
		return (List<Question>) query.getResultList();
	}
	
	

	public Quiz addQuizQuestion(int quizID, int questionID)
	{
		Quiz quiz = em.find(Quiz.class, quizID);
		Question question  = em.find(Question.class, questionID);
		quiz.addQuestion(question);
		return quiz;
	}

	
	public List<Answer> findAllAnswers()
	{
		Query query = em.createQuery("SELECT a FROM Answer a");
		return (List<Answer>) query.getResultList();
	}
}