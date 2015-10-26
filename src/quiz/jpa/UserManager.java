package quiz.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import quiz.jpa.User;

public class UserManager implements Authenticate
{
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;
	/**
	 * Checks against datasource for correct username/password combo
	 * 
	 * @param username
	 * @param password
	 * @return a new User object if credentials were found
	 */
	public quiz.jpa.User login(String username, String password)
	{
		emf = Persistence.createEntityManagerFactory("QuizPersistenceUnit");
		em = emf.createEntityManager();
		return getUserByEmailAndPassword(em, username, password);

	}

	public User getUserByEmailAndPassword(EntityManager em, String email, String password) {
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