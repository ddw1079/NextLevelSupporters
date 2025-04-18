package admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class adminDAO implements Readable{
	private Connection con;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public adminDAO() throws ClassNotFoundException, SQLException {
		con = new ConnectDB().getConnection();
	}
	
	public void activeUpdate(int id, String bool) throws SQLException {//is_active 변경 DAO
		String sql = "update user_table set is_active = ? where id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1,bool);
		pstmt.setInt(2, id);
		pstmt.executeUpdate();
	}
	
	public List<HistoryVO> read(int id) throws SQLException{//user 한 명의 history 를 읽어오는 DAO
		List<HistoryVO> hisarray  = new ArrayList<HistoryVO>();
		String sql1 = "select * from user_table where id = ?";
		pstmt = con.prepareStatement(sql1);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		int usertype = 2;
		while(rs.next()) {
			usertype = rs.getInt("USER_TYPE");
		}
		
		if(usertype == 0) {//선택한 user가 후원자일 경우
			String sql2 = "select * from history where giver_id = ? ORDER BY CREATE_DATE";
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int gid = rs.getInt("giver_id");		
				int rid = rs.getInt("receiver_id");
				int amount = rs.getInt("amount");
				Date d = rs.getDate("CREATE_DATE");
				String b = rs.getString("is_received");
				HistoryVO hv = new HistoryVO(gid,rid,amount,d,b);
				hisarray.add(hv);
			}
		}else if(usertype == 1) {//선택한 user가 수혜자일 경우
			String sql3 = "select * from history where receiver_id = ? ORDER BY CREATE_DATE";
			pstmt = con.prepareStatement(sql3);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int gid = rs.getInt("giver_id");		
				int rid = rs.getInt("receiver_id");
				int amount = rs.getInt("amount");
				Date d = rs.getDate("CREATE_DATE");
				String b = rs.getString("is_received");
				HistoryVO hv = new HistoryVO(gid,rid,amount,d,b);
				hisarray.add(hv);
			}
		}
		
		return hisarray;
		
	}


	@Override
	public List<UserVO> read() throws SQLException {//유저 내역 테이블에 넣을 유저 전체 목록 읽어오는 DAO
		List<UserVO> usarray = new ArrayList<UserVO>();
		String sql = "select * from user_table";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("id");
			String logid = rs.getString("login_id");
			String name = rs.getString("name");
			int type = rs.getInt("user_type");
			String phone = rs.getString("phone");
			String isactive = rs.getString("is_active");
			UserVO uv = new UserVO(id, logid, name, type, phone, isactive);
			usarray.add(uv);
		}
		
		
		return usarray;
	}
	
	
	public List<HistoryVO> historyRead() throws SQLException{//후원 내역 테이블에 넣을 history 테이블을 가져오는 DAO
		List<HistoryVO> hisarray = new ArrayList<HistoryVO>();
		String sql = "select * from history";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			int giver_id = rs.getInt("giver_id");
			int receiver_id = rs.getInt("receiver_id");
			int amount = rs.getInt("amount");
			Date date = rs.getDate("CREATE_DATE");
			String is_received = rs.getString("is_received");
			HistoryVO hv = new HistoryVO(giver_id, receiver_id, amount, date, is_received);
			hisarray.add(hv);
		}
		return hisarray;
		
	}
	
	
	
	
	
	
}
