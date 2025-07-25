package giver;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Raccept.RAccept2;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.sql.SQLException;

import template.NLSMenuTemplate;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import template.TableTemplate;

public class GiverHistoryMainClass extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField historySearchTextField;
	private JTable historyTable;
	private JScrollPane historyScrollPane;
	/**
	 * 파일 설명: 후원자 후원 내역 페이지
	 * 파일 상세: 후원자의 후원 내역 정보 출력
	 *  - 후원자인 당신은 후원 내역을 보고자 합니다.
	 *  - 당신의 후원 내역을 검색할 수 있습니다.
	 * Input: 현재 로그인한 인원의 정보(이름, 유저타입), 해당 유저의 후원 내역 테이블
	 * Feature:
	 * 	- 로그인 한 유저의 후원 내역 정보를 테이블로써 보여준다.
	 *  - 후원 정보를 검색할 수 있습니다.
	 * Output: 없음 
	 */
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public GiverHistoryMainClass(int user_id) throws ClassNotFoundException, SQLException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 978, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("당신의 후원 내역");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(242, 61, 466, 41);
		contentPane.add(lblNewLabel_1);
		
		NLSMenuTemplate menuTemplate = new NLSMenuTemplate(user_id);
		menuTemplate.setBounds(12, 10, 938, 41);
		contentPane.add(menuTemplate);
		
		GiverHistoryTable giverHistoryTable = new GiverHistoryTable(user_id);
		giverHistoryTable.setBounds(49, 166, 873, 335);
		contentPane.add(giverHistoryTable);
		
		
	}
	
    /** 테스트용 main */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
				new GiverHistoryMainClass(1).setVisible(true);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }
}
