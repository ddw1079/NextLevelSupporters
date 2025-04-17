//수혜 내역 DAO (데이터베이스 접근 객체)
//DB의 HISTORY 테이블에서 특정 수혜자의 후원 내역(후원자명, 일시, 금액, 메시지)을 가져오는 기능을 함

package Raccept;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.Readable;

/**
 * 수혜 내역 DAO (데이터베이스 접근 객체)
 * DB의 HISTORY 테이블에서 특정 수혜자의 후원 내역(후원자명, 일시, 금액, 메시지)을 가져오는 기능을 함
 */
public class ReceiverHistoryDao implements Readable<ReceiverHistoryVo> {

    private Connection con; // DB 연결 객체

    public ReceiverHistoryDao(Connection con) {
        this.con = con;
    }

    public ReceiverHistoryDao() {
		// TODO Auto-generated constructor stub
	}

	/**
     * 특정 수혜자 ID에 해당하는 후원 내역 리스트 조회 (수락 안한 건만)
     */
    @Override
    public ArrayList<ReceiverHistoryVo> read(int receiverId) throws SQLException {
    	ArrayList<ReceiverHistoryVo> list = new ArrayList<>();

        String sql = "SELECT H.GIVER_ID, U.NAME AS GIVER_NAME, H.AMOUNT, H.CREATE_DATE, H.MESSAGE " +
                     "FROM HISTORY H " +
                     "JOIN USERS U ON H.GIVER_ID = U.ID " +
                     "WHERE H.RECEIVER_ID = ? AND H.IS_RECEIVED = 'N' " +
                     "ORDER BY H.CREATE_DATE DESC";
        

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, receiverId);
            try (ResultSet rs = pstmt.executeQuery()) {
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

    /**
     * 전체 후원 내역을 가져오는 메서드 (optional, 보통 테스트용)
     */
    @Override
    public List<ReceiverHistoryVo> read() throws SQLException {
        List<ReceiverHistoryVo> list = new ArrayList<>();

        String sql = "SELECT H.GIVER_ID, U.NAME AS GIVER_NAME, H.AMOUNT, H.CREATE_DATE, H.MESSAGE " +
                     "FROM HISTORY H " +
                     "JOIN USERS U ON H.GIVER_ID = U.ID " +
                     "WHERE H.IS_RECEIVED = 'N' " +
                     "ORDER BY H.CREATE_DATE DESC";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
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

        return list;
    }
}
