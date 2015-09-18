package quiz.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import quiz.data.Answer;

@Entity
@Table
public class Question
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String text;

	@OneToMany(mappedBy="question")
	private List<Answer> answers;

	private String givenAnswer;
	
	@ManyToMany(mappedBy="quiz")
	private List<Quiz> quizzes;

	public Question(int id, String questionTxt, List<Answer> answers)
	{
		this.id = id;
		this.text = questionTxt;
		this.answers = answers;
	}

	public int getId()
	{
		return id;
	}

	public String getText()
	{
		return this.text;
	}

	void setText(String questionTxt)
	{
		this.text = questionTxt;
	}

	public List<Answer> getAnswers()
	{
		return answers;
	}

	void setAnswers(List<Answer> answers)
	{
		this.answers = answers;
	}

	public String getGivenAnswer()
	{
		return givenAnswer;
	}

	public void setGivenAnswer(String givenAnswer)
	{
		this.givenAnswer = givenAnswer;
	}

	public Answer getCorrectAnswer()
	{
		for (Answer answer : answers)
		{
			if (answer.isCorrect())
				return answer;
		}
		return null;
	}

	@Override
	public String toString()
	{
		return "Question [id=" + id + ", text=" + text + ", answers=" + answers + ", givenAnswer=" + givenAnswer
				+ "]";
	}
}
