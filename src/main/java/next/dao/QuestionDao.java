package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import next.dao.template.JdbcTemplate;
import next.dao.template.PreparedStatementSetter;
import next.dao.template.RowMapper;
import next.model.Question;

public class QuestionDao {
	
	public void insert(final Question question) {
		JdbcTemplate t = new JdbcTemplate();
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, question.getWriter());
				pstmt.setString(2, question.getTitle());
				pstmt.setString(3, question.getContents());
				pstmt.setTimestamp(4, new Timestamp(question.getTimeFromCreateDate()));
				pstmt.setInt(5, question.getCountOfComment());
			}
		};
		
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfComment) VALUES (?, ?, ?, ?, ?)");
		
		t.execute(query.toString(), pss);
	}
	
	
	public void updateCountOfComment(final long questionId) {
		JdbcTemplate t = new JdbcTemplate();
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, questionId);
			}
		};
		
		StringBuilder query = new StringBuilder();
		query.append("UPDATE questions SET countOfComment = countOfComment+1 WHERE questionId = ?");
		
		t.execute(query.toString(), pss);
	}

	
	public List<Question> findAll() {
		JdbcTemplate t = new JdbcTemplate();
		
		RowMapper rm = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				List<Question> questions = new ArrayList<Question>();
				Question question = null;
				while (rs.next()) {
					question = new Question(
							rs.getLong("questionId"),
							rs.getString("writer"),
							rs.getString("title"),
							null,
							rs.getTimestamp("createdDate"),
							rs.getInt("countOfComment"));
					questions.add(question);
				}
				return questions;
			}
			
		};
		
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT questionId, writer, title, createdDate, countOfComment FROM QUESTIONS ");
		query.append("order by questionId desc");
		
		return (List<Question>) t.execute(query.toString(), rm);
	}
	
	
	public Question findById(final long questionId) {
		JdbcTemplate t = new JdbcTemplate();
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, questionId);
			}
		};
		
		RowMapper rm = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				Question question = null;
				while (rs.next()) {
					question = new Question(
							rs.getLong("questionId"),
							rs.getString("writer"),
							rs.getString("title"),
							rs.getString("contents"),
							rs.getTimestamp("createdDate"),
							rs.getInt("countOfComment"));
				}
				return question;
			}
			
		};
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT questionId, writer, title, contents, createdDate, countOfComment FROM QUESTIONS ");
		query.append("WHERE questionId = ?");
		
		return (Question) t.execute(query.toString(), pss, rm);
	}
}
