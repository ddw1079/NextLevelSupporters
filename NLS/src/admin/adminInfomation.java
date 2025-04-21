package admin;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class adminInfomation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminInfomation frame = new adminInfomation(0,"");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public adminInfomation(int user,String logid) throws ClassNotFoundException, SQLException {
		adminDAO dao = new adminDAO(); // dao 클래스에 대한 객체 생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		List<HistoryVO> datafromDB = dao.read(user); // DB에서 데이터 읽기
		String[] columnNames = { "후원자 번호","후원자 아이디" ,"수혜자 번호","수혜자 아이디", "금액", "일자", "수혜 여부" };// 테이블에 출력될 컬럼명
		DefaultTableModel model = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 모든 셀 편집 불가
            }
        };

		for (HistoryVO row : datafromDB) {// 테이블에 db 값 옮기기
			Object[] rowData = { row.getGiver_id(),row.getGiver_logid(), row.getReceiver_id(),row.getReceiver_logid() ,row.getAmount(), row.getDate(),
					row.getIs_received() };
			model.addRow(rowData);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 110, 604, 383);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		JLabel lblNewLabel = new JLabel("로그인 아이디: "+logid+"의 상세정보");
		lblNewLabel.setBounds(100, 42, 494, 22);
		contentPane.add(lblNewLabel);
		
		JButton btnReturn = new JButton("관리자 화면으로 돌아가기");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				setVisible(false);
				
			}
		});
		btnReturn.setBounds(535, 42, 214, 23);
		contentPane.add(btnReturn);
	}
}
