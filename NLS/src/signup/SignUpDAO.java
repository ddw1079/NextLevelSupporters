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
        String sql = "INSERT INTO USER_TABLE (LOGIN_ID, LOGIN_PW, NAME, PHONE, USER_TYPE, ACCOUNT) VALUES (?, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO RECEIVER (REASON) VALUES (?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, loginId);
            pstmt.setString(2, loginPw);
            pstmt.setString(3, name);
            pstmt.setString(4, phone);
            pstmt.setInt(5, userType);
            pstmt.setString(6, account);
            
            pstmt2 = con.prepareStatement(sql2);
            pstmt2.setString(1, reason);

            int result = pstmt.executeUpdate();
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
