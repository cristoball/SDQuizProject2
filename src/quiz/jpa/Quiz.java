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

import quiz.data.Answer;
import quiz.data.Question;

@Entity
@Table
public class Quiz
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany(mappedBy="quiz")
    @JoinTable(name="quiz_question",
    joinColumns=@JoinColumn(name="quiz_id"),
    inverseJoinColumns=@JoinColumn(name="question_id"))
	private List<Question> questions;
	
	@Column
	private String name;


	public Quiz(String quizName)
	{
		this.name = quizName;
	}

	public int getQuizID()
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

	void setQuestions(List<Question> questions)
	{
		this.questions = questions;
	}

	public String getResults()
	{
		StringBuilder builder = new StringBuilder(1024);
		for (Question question : questions)
		{
			builder.append("Question: " + question.getValue() + "\n");
			List<Answer> answers = question.getAnswers();
			for (Answer answer : answers)
			{
				if (answer.isCorrect())
				{
					builder.append("  *");
				} else
				{
					builder.append("   ");
				}
				builder.append("Answer: " + answer.getValue() + "\n");
			}
			builder.append("User Answer: " + question.getGivenAnswer());
			if (question.getCorrectAnswer().getValue().equals(question.getGivenAnswer()))
			{
				builder.append(" --> CORRECT" + "\n");
			} else
			{
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
