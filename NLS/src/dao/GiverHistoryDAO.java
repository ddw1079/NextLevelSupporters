package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.Readable;
import vo.GiverHistoryVO;
import db.ConnectDB;


/* **
 * 파일 설명: 후원자 후원내역에서 사용할 DB Access Object.
 * 파일 상세: 후원자 후원내역에서 테이블을 출력하기 위한 DAO
 *  - 후원내역 테이블은 History 테이블의 후원자를 로그인한 사용자의 ID로 검색하여 출력한다. 
 *  	- ID, 수혜자명, 일자, 금액, 메시지로 되어있다.
 *  	- ID: 숫자, 자동 increment
 *  	- 수혜자명: 후원한 수혜자 명
 *  	- 일자: 후원한 날짜
 *  	- 금액: 후원한 금액
 *  	- 메시지: 후원 시 보낸 메시지
 *  Input: Select id 수혜자명 일자 금액 메시지 from History Left Join id=후원자id Left Join id=수혜자id
 * 	Output: 후원내역 테이블 List
 * 
 * */

public class GiverHistoryDAO implements Readable{
	private Connection con;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	// 생성자. DB Connect
	public GiverHistoryDAO() throws ClassNotFoundException, SQLException {
		con = new ConnectDB().getConnection();
	}

	// Readable 인터페이스에 존재하는 read(). 비어도 되지만  필수로 작성해야함.
	@Override
	public List read() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	// int값을 받는 read(int target)
	// ArrayList로 반환한다.
	@Override
	public ArrayList<GiverHistoryVO> read(int targetGiverID) throws SQLException {
		// VO 타입인 ArrayList를 선언한다.
		ArrayList<GiverHistoryVO> ghList = new ArrayList<>();
		
		// SQL 문 설명: 후원자 ID 로 검색. 
		// 자동 증가 idx, 생성일, 후원자 아이디와 이름, 수혜자 아이디와 이름, 후원금액, 후원메시지, 수령받음 char 값이 Y면 1 아니면 0
		String sql = """
	            SELECT ROW_NUMBER() OVER (ORDER BY H.CREATE_DATE ASC) AS idx,
	                   H.CREATE_DATE,
	                   H.GIVER_ID,
	                   G.NAME AS GIVER_NAME,
	                   H.RECEIVER_ID,
	                   R.NAME AS RECEIVER_NAME,
	                   H.AMOUNT,
	                   H.MESSAGE,
	                   CASE WHEN H.IS_RECEIVED = 'Y' THEN 1 ELSE 0 END AS is_received
	            FROM   HISTORY H
	            LEFT JOIN user_table G ON H.GIVER_ID = G.ID
	            LEFT JOIN user_table R ON H.RECEIVER_ID = R.ID
	            WHERE  H.GIVER_ID = ?
	            ORDER BY H.CREATE_DATE DESC
	            """;
		// SQL문을 PrepareStatement 에 넣고 물음표 부분을 채운다.
		ps = con.prepareStatement(sql);
		ps.setInt(1, targetGiverID);
		
		// 쿼리를 실행하고 내부 데이터를 한줄씩 리스트에 넣는다.
		rs = ps.executeQuery();
		while(rs.next()) {
			int idx = rs.getInt("idx");
			Date create_date = rs.getDate("CREATE_DATE");
			int giver_id = rs.getInt("GIVER_ID");
			String giver_name = rs.getString("GIVER_NAME");
			int receiver_id = rs.getInt("RECEIVER_ID");
			String receiver_name = rs.getString("RECEIVER_NAME");
			int amount = rs.getInt("AMOUNT");
			String message = rs.getString("MESSAGE");
			boolean is_received = (rs.getInt("is_received") == 1);
			
			GiverHistoryVO ghv = new GiverHistoryVO(idx, create_date, giver_id, giver_name, receiver_id, receiver_name, amount, message, is_received);
			ghList.add(ghv);
		}
		
		// 결과값 리턴
		return ghList;
	}


}
