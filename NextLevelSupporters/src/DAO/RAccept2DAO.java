package DonationDAO;

//(후원 내역과 관련된 DB 작업 처리)
//수혜자는 기부 내역을 확인하고 기부금을 수락
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class RAccept2DAO extends BaseDAO<DonationDTO> {

	private Connection con1;

	// 생성자
	// DB 연결 객체를 받음
	public RAccept2DAO(Connection connection) {
		this.con1 = connection; // con1에 저장
	}

	// 1. 후원 내역 수락 (Insert)
	// 후원 내역을 수락하는 메서드
	public boolean acceptDonation(int giverId, int receiverId, double amount, String message) {
		String sql = "INSERT INTO history (CREATE_DATE, GIVER_ID, RECEIVER_ID, AMOUNT, MESSAGE, IS_RECEIVED) "
				+ "VALUES (SYSDATE, ?, ?, ?, ?, 'N')"; // 후원 내역 삽입 (수락 대기 상태)

		try (PreparedStatement pstmt = con1.prepareStatement(sql)) {
			pstmt.setInt(1, giverId); // 후원자 ID
			pstmt.setInt(2, receiverId); // 수혜자 ID
			pstmt.setDouble(3, amount); // 후원 금액
			pstmt.setString(4, message); // 메시지

			pstmt.executeUpdate(); // DB에 insert
			return true; // 성공적으로 insert했으면 true 반환
		} catch (SQLException e) {
			System.out.println("Insertion error: " + e.getMessage());
			return false; // 오류 발생 시 false 반환
		}
	}

	// 2. 후원 내역 조회 (Read) - 수혜자별 후원 내역 조회
	public List<DonationDTO> getDonationsByReceiver(int receiverId) {
		List<DonationDTO> donations = new ArrayList<>();
		String sql = "SELECT h.CREATE_DATE, u.NAME AS DONOR_NAME, h.AMOUNT, h.MESSAGE " + "FROM history h "
				+ "JOIN user_table u ON h.GIVER_ID = u.ID " + "WHERE h.RECEIVER_ID = ? AND h.IS_RECEIVED = 'N'"; // 수혜자

		
		try (PreparedStatement pstmt = con1.prepareStatement(sql)) {
			pstmt.setInt(1, receiverId); // 수혜자 ID 설정
			ResultSet rs = pstmt.executeQuery(); // 쿼리 실행

			while (rs.next()) {
				// 조회된 데이터를 DTO 객체로 변환하여 리스트에 추가
				DonationDTO donation = new DonationDTO(rs.getString("DONOR_NAME"), // 후원자 이름
						rs.getDate("CREATE_DATE"), // 후원 날짜
						rs.getDouble("AMOUNT"), // 후원 금액
						rs.getString("MESSAGE") // 메시지
				);
				donations.add(donation); // 리스트에 추가
			}
		} catch (SQLException e) {
			System.out.println("Read error: " + e.getMessage());
		}

		return donations; // 후원 내역 리스트 반환
	}

// 3. 후원금 수락 처리 (Update) - 수혜자가 후원금 수락
	public boolean acceptDonationUpdate(int donationId) {
		String sql = "UPDATE history SET IS_RECEIVED = 'Y' WHERE DONATION_ID = ?"; // 후원 수락 처리

		try (PreparedStatement pstmt = con1.prepareStatement(sql)) {
			pstmt.setInt(1, donationId); // 후원 내역 ID 설정
			int rowsAffected = pstmt.executeUpdate(); // DB 업데이트

			return rowsAffected > 0; // 성공적으로 업데이트했으면 true 반환
		} catch (SQLException e) {
			System.out.println("Update error: " + e.getMessage());
			return false; // 오류 발생 시 false 반환
		}
	}
}