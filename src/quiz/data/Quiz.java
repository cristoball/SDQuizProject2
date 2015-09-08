package quiz.data;

import java.util.List;

public interface Quiz {
	
	public int getQuizID();
	public String getQuizName();
	public int getNumberOfQuestions();
	public List<Question> getQuestions();
	public String getResults();
}
