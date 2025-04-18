package Raccept;

import db.ConnectDB;
import interfaces.Readable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 수혜 내역 DAO: HISTORY 테이블에서
 * 특정 수혜자의 후원내역을 조건에 따라 조회
 */
public class ReceiverHistoryDao implements Readable<ReceiverHistoryVo> {

    private Connection con;

    /** 기본 생성자 */
    public ReceiverHistoryDao() throws SQLException, ClassNotFoundException {
        con = new ConnectDB().getConnection();
    }

    /** 외부에서 커넥션을 주입받을 경우 */
    public ReceiverHistoryDao(Connection con) {
        this.con = con;
    }

    /** 특정 수혜자의 후원 내역 (is_received='N')만 조회 → 기본 받기 전 목록 */
    @Override
    public ArrayList<ReceiverHistoryVo> read(int receiverId) throws SQLException {
        return readByStatus(receiverId, "N");
    }

    /** 특정 수혜자의 후원 내역을 is_received 상태에 따라 조회 */
    public ArrayList<ReceiverHistoryVo> readByStatus(int receiverId, String isReceived) throws SQLException {
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
              AND  H.IS_RECEIVED = ?
            ORDER  BY H.CREATE_DATE DESC
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, receiverId);
            ps.setString(2, isReceived);
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

    /** 전체 조회는 비활성화 */
    @Override
    public List<ReceiverHistoryVo> read() throws SQLException {
        throw new UnsupportedOperationException("파라미터 없는 read()는 지원하지 않습니다.");
    }
}
