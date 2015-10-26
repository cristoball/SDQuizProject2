package quiz.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class QuizManager
{
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;
	private static String perisitenceUnit = "QuizPersistenceUnit";
	
	public static Quiz loadQuiz() throws Exception {
		emf = Persistence.createEntityManagerFactory(perisitenceUnit);
		em = emf.createEntityManager();

		Quiz quiz = em.find(Quiz.class, 1);
		System.out.println(quiz.getName());
		return quiz;
	}
}
