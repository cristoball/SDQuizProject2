package quiz.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table
public class Quiz implements quiz.jpa.QuizInterface
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany
    @JoinTable(name = "QUIZ_QUESTION",
    			joinColumns = @JoinColumn(name="QUIZ_ID"),
    			inverseJoinColumns = @JoinColumn(name="QUESTION_ID"))	
	private List<Question> questions;
	
	@Column
	private String name;

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String quizName)
	{
		this.name = quizName;
	}


	public int getNumberOfQuestions()
	{
		return questions.size();
	}

	public List<Question> getQuestions()
	{
		return questions;
	}

	public void setQuestions(List<Question> questions)
	{
		this.questions = questions;
	}

	public String getResults()
	{
		StringBuilder builder = new StringBuilder(1024);
		for (Question question : questions)
		{
			builder.append("Question: " + question.getText() + "\n");
			
			List<Answer> answers = question.getAnswers();
			
			for (Answer answer : answers)
			{
				if (answer.isCorrect()) {
					builder.append("  *");
				} else {
					builder.append("   ");
				}
				builder.append("Answer: " + answer.getText() + "\n");
			}
			
			builder.append("User Answer: " + question.getGivenAnswer());
			
			if (question.getCorrectAnswer().getText().equals(question.getGivenAnswer())) {
				builder.append(" --> CORRECT" + "\n");
			} 
			else {
				builder.append(" --> INCORRECT" + "\n");
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	public void addQuestion(quiz.jpa.Question question)
	{
		// TODO Auto-generated method stub
		
	}
}
