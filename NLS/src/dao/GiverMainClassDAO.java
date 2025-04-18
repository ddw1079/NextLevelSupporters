package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectDB;
import interfaces.Readable;
import vo.GiverMainClassVO;

/* **
 * 파일 설명: 후원자 후원 가능 리스트 및 후원하기 에서 사용할 DB Access Object
 * 파일 상세: 후원자 후원 가능 리스트 및 후원하기에서 사용
 *  - 후원 가능한 수혜자 리스트와, 그 수혜자의 정보를 가져온다.
 *  - 그 수혜자와 로그인한 후원자의 정보를 바탕으로 후원 History를 생성한다.
 * 
 * 
 * */



public class GiverMainClassDAO implements Readable {
	private Connection con;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public GiverMainClassDAO() throws ClassNotFoundException, SQLException {
		con = new ConnectDB().getConnection();
	}
	
	public ArrayList<GiverMainClassVO> read() throws SQLException {
		
		ArrayList<GiverMainClassVO> gmcList = new ArrayList<>();
		
		String sql = """
				SELECT	ROW_NUMBER() OVER (ORDER BY R.RECEIVER_ID) AS idx,
						R.RECEIVER_ID,
						U.NAME as RECEIVER_NAME,
						R.REASON as RECEIVER_REASON,
						U.PHONE as RECEIVER_PHONE
				FROM	USER_TABLE U
				LEFT JOIN RECEIVER R ON R.RECEIVER_ID = U.ID
				WHERE U.USER_TYPE = 1
				""";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			int idx = rs.getInt("idx");
			int receiver_id = rs.getInt("RECEIVER_ID");
			String receiver_name = rs.getString("RECEIVER_NAME");
			String receiver_reason = rs.getString("RECEIVER_REASON");
			String receiver_phone = rs.getString("RECEIVER_PHONE");
			
			GiverMainClassVO gmcv = new GiverMainClassVO(idx, receiver_id, receiver_name, receiver_reason, receiver_phone);
			gmcList.add(gmcv);
		}
		return gmcList;
	}
	
	public ArrayList<GiverMainClassVO> read(int target) throws SQLException {
		return null;
	}
}
