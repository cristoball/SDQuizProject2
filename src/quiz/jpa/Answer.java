package quiz.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table
public class Answer
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String text;
	
	@Column
	private String isCorrect;
	
	@ManyToOne
	@JoinColumn(name="QUESTION_ID")
	private Question question;

	public int getID()
	{
		return this.id;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String answerTxt) 
	{
		this.text = answerTxt;
	}

	public boolean isCorrect() 
	{
		if (isCorrect.equalsIgnoreCase("Y")) 
		{
			return true;
		}
		return false;
	}
	
	public void setIsCorrect(boolean correct) {
		if (correct == true) 
		{
			isCorrect = "Y";
		} 
		else 
		{
			isCorrect = "N";
		}
	}

	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) 
	{
		this.question = question;
		if (!question.getAnswers().contains(this)) 
		{
			question.addAnswer(this);
		}
	}
	
	
	
	@Override
	public String toString() {
		return "Answer [text=" + text + ", correct=" + isCorrect + "]";
	}
}
