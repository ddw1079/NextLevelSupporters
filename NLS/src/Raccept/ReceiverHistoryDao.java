package Raccept;

import db.ConnectDB;
import interfaces.Readable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 수혜 내역 DAO: HISTORY 테이블에서
 * 특정 수혜자의 미수락 후원내역을 조회
 */
public class ReceiverHistoryDao implements Readable<ReceiverHistoryVo> {

    private Connection con;

    /** 기본 생성자: ConnectDB 로부터 커넥션을 획득합니다. */
    public ReceiverHistoryDao() throws SQLException, ClassNotFoundException {
        con = new ConnectDB().getConnection();
    }

    private Connection ConnectDB() {
		// TODO Auto-generated method stub
		return null;
	}

	private void getConnection() {
		// TODO Auto-generated method stub
		
	}

	/** 외부에서 커넥션을 주입할 때 사용하는 생성자 */
    public ReceiverHistoryDao(Connection con) {
        this.con = con;
    }

    /** 특정 수혜자 ID의 후원 내역 조회 */
    @Override
    public ArrayList<ReceiverHistoryVo> read(int receiverId) throws SQLException {
        ArrayList<ReceiverHistoryVo> list = new ArrayList<>();

        String sql = """
        	    SELECT H.GIVER_ID,
        	           U.NAME AS GIVER_NAME,
        	           H.AMOUNT,
        	           H.CREATE_DATE,
        	           H.MESSAGE
        	    FROM   HISTORY H
        	    JOIN   USER_TABLE U ON H.GIVER_ID = U.ID
        	    WHERE  H.RECEIVER_ID = ?
        	      AND  H.IS_RECEIVED = 'N'
        	    ORDER  BY H.CREATE_DATE DESC
        	""";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, receiverId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReceiverHistoryVo vo = new ReceiverHistoryVo();
                    vo.setGiverId(rs.getInt("GIVER_ID"));
                    vo.setGiverName(rs.getString("GIVER_NAME"));
                    vo.setAmount(rs.getInt("AMOUNT"));
                    vo.setCreateDate(rs.getDate("CREATE_DATE"));
                    vo.setMessage(rs.getString("MESSAGE"));
                    list.add(vo);
                }
            }
        }

        return list;
    }

    /** 전체 조회(테스트용) */
    @Override
    public List<ReceiverHistoryVo> read() throws SQLException {
        throw new UnsupportedOperationException("파라미터 없는 read()는 지원하지 않습니다.");
    }
}
