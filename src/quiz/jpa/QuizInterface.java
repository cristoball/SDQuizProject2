package quiz.jpa;

import java.util.List;

public interface QuizInterface {
	
	public int getId();
	public String getName();
	public int getNumberOfQuestions();
	public List<Question> getQuestions();
	public String getResults();
}
