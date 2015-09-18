package quiz.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class QuizJPATest
{
	public static void main(String[] a) throws Exception
	{

//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProfessorService");
//		EntityManager em = emf.createEntityManager();
//		ProfessorService service = new ProfessorService(em);
//
//		em.getTransaction().begin();
//		Professor emp = service.createProfessor("empName", 100);
//
//		Project proj = service.createProject("projName");
//
//		emp = service.addProfessorProject(emp.getId(), proj.getId());
//
//		Collection<Professor> emps = service.findAllProfessors();
//		if (emps.isEmpty())
//		{
//			System.out.println("No Professors found ");
//		} else
//		{
//			System.out.println("Found Professors:");
//			for (Professor emp1 : emps)
//			{
//				System.out.println(emp1);
//			}
//		}
//
//		Collection<Project> projs = service.findAllProjects();
//		if (projs.isEmpty())
//		{
//			System.out.println("No Projects found ");
//		} else
//		{
//			System.out.println("Found Projects:");
//			for (Project proj1 : projs)
//			{
//				System.out.println(proj1);
//			}
//		}
//
//		util.checkData("select * from Professor");
//
//		util.checkData("select * from Project");
//		em.getTransaction().commit();
//		em.close();
//		emf.close();
	}
}