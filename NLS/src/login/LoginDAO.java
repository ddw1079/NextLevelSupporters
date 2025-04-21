package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.ConnectDB;

public class LoginDAO {

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public LoginDAO() {
        try {
            ConnectDB connectDB = new ConnectDB();
            con = connectDB.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validateLogin(int userId, String password) throws SQLException {
        String sql = "SELECT LOGIN_PW FROM USER_TABLE WHERE ID = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, userId);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            String storedPassword = rs.getString("LOGIN_PW");
            return storedPassword.equals(password);
        }
        return false;
    }

    public int getUserType(int userId) throws SQLException {
        String sql = "SELECT USER_TYPE FROM USER_TABLE WHERE ID = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, userId);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            int userType = rs.getInt("USER_TYPE");
            if (rs.wasNull()) {
                System.out.println("사용자 타입이 설정되지 않았습니다.");
                return -1;
            }
            return userType;
        }
        System.out.println("사용자를 찾을 수 없습니다: " + userId);
        return -1;
    }

    public String getUserName(int userId) throws SQLException {
        String sql = "SELECT NAME FROM USER_TABLE WHERE ID = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, userId);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            String userName = rs.getString("NAME");
            if (rs.wasNull()) {
                System.out.println("사용자 이름이 설정되지 않았습니다.");
                return null;
            }
            return userName;
        }
        System.out.println("사용자를 찾을 수 없습니다: " + userId);
        return null;
    }

    public int getUserId(String LOGIN_ID) throws SQLException {
        String sql = "SELECT ID FROM USER_TABLE WHERE LOGIN_ID = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, LOGIN_ID);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            int userId = rs.getInt("ID");
            if (rs.wasNull()) {
                System.out.println("사용자 ID가 설정되지 않았습니다.");
                return -1;
            }
            return userId;
        }

        System.out.println("사용자를 찾을 수 없습니다: " + LOGIN_ID);
        return -1;
    }

    public void close() {
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
