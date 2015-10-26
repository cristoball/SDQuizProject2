package quiz.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import quiz.jpa.Answer;
import quiz.jpa.Question;
import quiz.jpa.Quiz;

public class QuizJPATest 
{
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;

	public static void main(String[] args) throws Exception {
		emf = Persistence.createEntityManagerFactory("QuizPersistenceUnit");
		em = emf.createEntityManager();

		Quiz quiz = em.find(Quiz.class, 1);
		System.out.println(quiz.getName());

		List<Question> lsQuestions = quiz.getQuestions();
		for (Question question : lsQuestions) {
			System.out.println(question.getText());

			List<Answer> answers = question.getAnswers();
			for (Answer an : answers) {
				System.out.println(an.getText() + " " + an.isCorrect());
			}

		}
		
		User user = getUserByEmailAndPassword(em, "test@test.net", "test");
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(user.getID());
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getAdmin());
		
		em.close();
		emf.close();
	}
	
	public static User getUserByEmailAndPassword(EntityManager em, String email, String password) {
	    try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
		    query.setParameter("email", email);
		    query.setParameter("password", password);
		    User user = query.getSingleResult();
		    return user;
	    }
	    catch (Exception ex) {
	    	System.out.println(ex);
	    }
	    return null;
	} 
}
