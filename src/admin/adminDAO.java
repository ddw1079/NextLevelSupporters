package admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class adminDAO{
	private Connection con;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public adminDAO() throws ClassNotFoundException, SQLException {
		con = new AdminDBconn().getConnection();
	}
	
	public void activeUpdate(int id, String bool) throws SQLException {//is_active 변경 DAO
		String sql = "update user_table set is_active = ? where id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1,bool);
		pstmt.setInt(2, id);
		pstmt.executeUpdate();
	}
	
	public List<HistoryVO> getUserHistory(String name) throws SQLException{//user 한 명의 history 를 리턴하는 DAO
		List<HistoryVO> hisarray  = new ArrayList<HistoryVO>();
		String sql1 = "select * from user_table where name = ?";
		pstmt = con.prepareStatement(sql1);
		rs = pstmt.executeQuery();
		//받아온 이름으로 id와 유저 타입을 알아냄
		//PK인 id가 아니라 name을 가져와서 검색하는 방식이라 동명이인일 경우는 문제 발생
		//이대로 동명이인을 배제하거나,수정하려면 프론트엔드 설계를 일부 변경해야 할 듯
		int userid = rs.getInt("id");
		int usertype = rs.getInt("USER_TYPE");
		
		if(usertype == 0) {//선택한 user가 후원자일 경우
			String sql2 = "select * from history where giver_id = ? ORDER BY CREATE_DATE";
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int gid = rs.getInt("giver_id");		
				int rid = rs.getInt("receiver_id");
				int amount = rs.getInt("amount");
				Date d = rs.getDate("date");
				String b = rs.getString("is_received");
				HistoryVO hv = new HistoryVO(gid,rid,amount,d,b);
				hisarray.add(hv);
			}
		}else if(usertype == 1) {//선택한 user가 수혜자일 경우
			String sql2 = "select * from history where receiver_id = ? ORDER BY CREATE_DATE";
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int gid = rs.getInt("giver_id");		
				int rid = rs.getInt("receiver_id");
				int amount = rs.getInt("amount");
				Date d = rs.getDate("date");
				String b = rs.getString("is_received");
				HistoryVO hv = new HistoryVO(gid,rid,amount,d,b);
				hisarray.add(hv);
			}
		}

		
		return hisarray;
		
	}
	
}
