package signup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.ConnectDB;

public class SignUpDAO {

    private Connection con;
    private PreparedStatement pstmt;
    private PreparedStatement pstmt2;
    private PreparedStatement seqStmt = null;
    private ResultSet rs;

    public SignUpDAO() {
        try {
            ConnectDB connectDB = new ConnectDB();
            con = connectDB.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(String loginId, String loginPw, String name, String phone, int userType, String account, String reason) throws SQLException {
    	seqStmt = con.prepareStatement("SELECT user_seq.NEXTVAL FROM dual");
        rs = seqStmt.executeQuery();
        
        int userId = -1;
        if (rs.next()) {
            userId = rs.getInt(1);  // user_seq에서 얻은 고유 ID
        }
    	
    	String sql = "INSERT INTO USER_TABLE (ID, LOGIN_ID, LOGIN_PW, NAME, PHONE, USER_TYPE, ACCOUNT) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO RECEIVER (RECEIVER_ID, REASON) VALUES (?, ?)";
        try {
        	
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, (int)userId);
            pstmt.setString(2, loginId);
            pstmt.setString(3, loginPw);
            pstmt.setString(4, name);
            pstmt.setString(5, phone);
            pstmt.setInt(6, userType);
            pstmt.setString(7, account);
            
            int result = pstmt.executeUpdate();
            
            if (userType == 1) {
            pstmt2 = con.prepareStatement(sql2);
            pstmt2.setInt(1, (int) userId);
            pstmt2.setString(2, reason);
            int result2 = pstmt2.executeUpdate();
            return  result2 > 0;
            }
            
          
            
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean checkIdExists(String loginId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM USER_TABLE WHERE LOGIN_ID = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, loginId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
