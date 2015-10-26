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
import javax.persistence.Transient;


@Entity
@Table
public class Question
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String text;

	@ManyToMany(mappedBy="questions")
	private List<Quiz> quizzes;	
	
	@OneToMany(mappedBy="question")
	private List<Answer> answers;

	@Transient
	private String givenAnswer;
	

	public int getId()
	{
		return id;
	}

	public String getText()
	{
		return this.text;
	}

	public void setText(String questionTxt)
	{
		this.text = questionTxt;
	}

	public List<Answer> getAnswers()
	{
		return answers;
	}

	public void setAnswers(List<Answer> answers)
	{
		this.answers = answers;
		for (Answer answer : answers) 
		{
			if (!answer.getQuestion().equals(this))
				answer.setQuestion(this);
		}		
	}
	
	public void addAnswer(Answer answer) {
		if (!answers.contains(answer)) {
			answers.add(answer);
		}
		if(!answer.getQuestion().equals(this))
		answer.setQuestion(this);
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
		return "Question [id=" + id + 
				", "+ "text=" + text + 
				", answers=" + answers + 
				", givenAnswer=" + givenAnswer
				+ "]";
	}
}
