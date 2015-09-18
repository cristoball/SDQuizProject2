package quiz.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private boolean isCorrect;
	
	@ManyToOne
	private Question question;
	
	public Answer(String answerTxt, boolean correct) 
	{
		this.text = answerTxt;
		this.isCorrect = correct;
	}

	public int getID()
	{
		return this.id;
	}
	
	public String getText() {
		return text;
	}

	void setValue(String answerTxt) {
		this.text = answerTxt;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	void setCorrect(boolean correct) {
		this.isCorrect = correct;
	}

	@Override
	public String toString() {
		return "Answer [text=" + text + ", correct=" + isCorrect + "]";
	}
}
