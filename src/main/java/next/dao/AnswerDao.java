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
import next.model.Answer;

public class AnswerDao {
	
	public void insert(final Answer answer) {
		JdbcTemplate t = new JdbcTemplate();
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, answer.getWriter());
				pstmt.setString(2, answer.getContents());
				pstmt.setTimestamp(3, new Timestamp(answer.getTimeFromCreateDate()));
				pstmt.setLong(4, answer.getQuestionId());
			}
		};
		
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)");
		
		t.execute(query.toString(), pss);
	}
	
	
	public List<Answer> findAllByQuestionId(final long questionId) {
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
				List<Answer> answers = new ArrayList<Answer>();
				Answer answer = null;
				while (rs.next()) {
					answer = new Answer(
							rs.getLong("answerId"),
							rs.getString("writer"),
							rs.getString("contents"),
							rs.getTimestamp("createdDate"),
							questionId);
					answers.add(answer);
				}
				return answers;
			}
			
		};
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT answerId, writer, contents, createdDate FROM ANSWERS WHERE questionId = ? ");
		query.append("order by answerId desc");

		
		return (List<Answer>)t.execute(query.toString(), pss, rm);
	}
}
