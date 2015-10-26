package quiz.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizDB implements QuizInterface
{

	private int id;
	private List<Question> questions;
	private String name = "State Capitals";
	//private Connection conn = null;

	public QuizDB(Connection conn)
	{
		// String URL = "jdbc:derby://localhost:1527/quiz";
		// String URL = "jdbc:sqlite:" + System.getProperty("user.home") +
		// "/SD/Databases/quiz.db";
		//String URL = "jdbc:derby:" + System.getProperty("user.home") + "/SD/Databases/quiz";

		try
		{
			loadQuestions(conn);
		} catch (SQLException e)
		{
			System.err.println(e);
			throw new RuntimeException(e);
		}
	}

	public int getId()
	{
		return id;
	}

	@Override
	public String getName()
	{
		return name;
	}

	private void loadQuestions(Connection conn) throws SQLException
	{

		String questionSQL = "SELECT q.id, qu.id, text " + 
							"FROM quiz q, question qu, quiz_question qq " +
							"WHERE q.id = qq.quiz_id " +
							"AND qu.id = qq.question_id " + 
							"AND q.name = ?";

		PreparedStatement pstmt = conn.prepareStatement(questionSQL);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		System.out.println(questionSQL + "\n");

		
		
		questions = new ArrayList<>();
		while (rs.next())
		{
			this.id = rs.getInt(1);
			
			int questionID = rs.getInt(2);
			String questionText = rs.getString(3);

			List<Answer> answers = new ArrayList<>();
			String answerSQL = "SELECT text, iscorrect " + 
								"FROM answer " + 
								"WHERE question_id = ?";
			
			PreparedStatement pstmt2 = conn.prepareStatement(answerSQL);
			pstmt2.setInt(1, questionID);
			ResultSet rs2 = pstmt2.executeQuery();
			while (rs2.next())
			{
				String answerText = rs2.getString(1);
				boolean isCorrect = rs2.getBoolean(2);
				answers.add(new Answer(answerText, isCorrect));
			}
			rs2.close();
			pstmt2.close();

			Question question = new Question(questionID, questionText, answers);
			questions.add(question);
		}
		rs.close();
		pstmt.close();
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
}
