package next.dao.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import next.support.db.ConnectionManager;
import next.support.db.Utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcTemplate {
	private static final Logger logger = LoggerFactory.getLogger(JdbcTemplate.class.getName());
	Connection con;
	
	public JdbcTemplate() {
		this.con = ConnectionManager.getConnection();
	}
	
	
	public Object execute (String query, PreparedStatementSetter pss) {
		return execute(query, pss, null);
	}
	
	public Object execute (String query, RowMapper rm) {
		return execute(query, null, rm);
	}
	
	public Object execute (String query, PreparedStatementSetter pss, RowMapper rm) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Object result = null;
		
		try {
			pstmt = this.con.prepareStatement(query);
			
			if (pss != null) {				
				pss.setValues(pstmt);
			}
			
			if (rm == null) {
				result = false;
				pstmt.execute();
				result = true;
			} else {
				rs = pstmt.executeQuery();
				result = rm.mapRow(rs);
			}
			
		} catch (SQLException sqle) {
			logger.debug(sqle.getMessage());
			
		} finally {
			Utility.rsClose(rs);
			Utility.pstmtClose(pstmt);
			Utility.conClose(this.con);
		}
				
		return result;
	}
}
