package next.support.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {
	private static final Logger logger = LoggerFactory.getLogger(Utility.class);
	
	public static void rsClose(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.debug(e.getMessage());
			}
		}
	}
	
	public static void pstmtClose(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				logger.debug(e.getMessage());
			}
		}
	}

	public static void conClose(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				logger.debug(e.getMessage());
			}
		}
	}
	
	

	
}
