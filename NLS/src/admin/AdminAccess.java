/*
화면 상단의 choice 창에서 유저별 조회, 내역 별 조회 옵션 선택 시
해당 테이블 활성화, 이전 테이블 비활성화

유저별 조회 화면에서는 active 변경을 원하는 유저의 줄을 선택 후 active,dactive 버튼 클릭 시 
해당 줄의 userid를 받아 와 db에 접속해서 is_active를 변경
검색 창에 텍스트 입력 시 컬럼 상관 없이 해당 텍스트가 들어있는 user의 정보만 필터링해서 테이블을 실시간 갱신
숫자를 입력해 id로 검색도 이론상 가능한데 login_id랑 phone에 들어있는 숫자를 같이 인식해서 사실상 불가능
id,이름,is_active 로만 검색 가능하도록 해도 좋을듯?

내역 별 조회 화면에서는 후원 내역이 출력되고, 후원자 또는 수헤자 이름을 클릭 시
해당 user의 id를 가져가서 db에서 정보를 받아 온 뒤 그 사람과 관련된 후원 내역을 JOptionPane으로 출력

*/
package admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Choice;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AdminAccess extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnActive, btnDeactive;
	private JTable userTable;
	private JScrollPane userScrollPane;
	private JTable historyTable;
	private JScrollPane historyScrollPane;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAccess frame = new AdminAccess();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public AdminAccess() throws ClassNotFoundException, SQLException {
		setTitle("admin");
		adminDAO dao = new adminDAO(); // dao 클래스에 대한 객체 생성

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		
		
		Choice choice = new Choice();// 유저 창, 내역 창 전환
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selected = choice.getSelectedItem();
				if (selected.equals("유저별 조회")) {// 유저 별 옵션 선택 시 유저 창 활성화 및 내역 창 비활성화
					//btnActive.setVisible(true);
					//btnDeactive.setVisible(true);
					txtSearch.setVisible(true);
					userTable.setVisible(true);
					userScrollPane.setVisible(true);
					historyScrollPane.setVisible(false);
					historyTable.setVisible(false);

				} else {// 내역 창 활성화,유저 창 비활성화
					//btnActive.setVisible(false);
					//btnDeactive.setVisible(false);
					txtSearch.setVisible(false);
					userTable.setVisible(false);
					userScrollPane.setVisible(false);
					historyScrollPane.setVisible(true);
					historyTable.setVisible(true);

				}
			}
		});

		choice.setBounds(186, 73, 458, 21);
		choice.add("유저별 조회");
		choice.add("내역별 조회");
		contentPane.add(choice);

		
		
		
		JLabel lblTitle = new JLabel("관리자 화면");
		lblTitle.setBounds(385, 31, 85, 15);
		contentPane.add(lblTitle);

		
		
		
		btnActive = new JButton("Active");// 유저 조회 창에서 선택한 유저를 Active 하는 버튼
		btnActive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Object id = userTable.getValueAt(userTable.getSelectedRow(), 0);// 선택한 줄에 해당하는 user의 id를 추출
					int userid = Integer.parseInt(id.toString());// db에 검색하기 위해 int로 변환
					JOptionPane.showMessageDialog(null, "id:" + userid + "번 유저 Active");
					dao.activeUpdate(userid, "Y");// 가져온 id 값을 이용해 db에서 해당 user의 isactive 값 변경
					getUserTable();// 값이 변경된 이후에 다시 테이블을 갱신해서, 변경 여부를 즉시 반영
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "변경할 user가 선택되지 않았습니다.");
				}catch(IndexOutOfBoundsException e1){
					JOptionPane.showMessageDialog(null, "변경할 user가 선택되지 않았습니다.");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		/*
		btnActive.setBounds(186, 513, 97, 23);
		contentPane.add(btnActive);
		
		btnDeactive = new JButton("Deactive");// 유저 조회 창에서 선택한 유저를 Deactive 하는 버튼
		btnDeactive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Object id = userTable.getValueAt(userTable.getSelectedRow(), 0);// 선택한 줄에 해당하는 user의 id를 추출
					int userid = Integer.parseInt(id.toString());
					JOptionPane.showMessageDialog(null, "id:" + userid + "번 유저 Deactive");
					dao.activeUpdate(userid, "N");// 가져온 id 값을 이용해 db에서 해당 user의 isactive 값 변경
					getUserTable();// 값이 변경된 이후에 다시 테이블을 갱신해서, 변경 여부를 즉시 반영
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "변경할 user가 선택되지 않았습니다.");
				}catch(IndexOutOfBoundsException e1){
					JOptionPane.showMessageDialog(null, "변경할 user가 선택되지 않았습니다.");
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDeactive.setBounds(547, 513, 97, 23);
		contentPane.add(btnDeactive);
		*/
		
		
		
		userScrollPane = new JScrollPane();
		userScrollPane.setBounds(77, 165, 660, 296);
		contentPane.add(userScrollPane);

		// 유저 목록 조회 창
		userTable = new JTable();
		userTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Object id = userTable.getValueAt(userTable.getSelectedRow(), 0);// 선택한 줄에 해당하는 user의 id를 추출
					int userid = Integer.parseInt(id.toString());
					Object is_active = userTable.getValueAt(userTable.getSelectedRow(), 5);
					String isactive = is_active.toString();
					if(isactive.equals("Y")){
						JOptionPane.showMessageDialog(null, "id:" + userid + "번 유저 Deactive");
						dao.activeUpdate(userid, "N");// 가져온 id 값을 이용해 db에서 해당 user의 isactive 값 변경
						getUserTable();// 값이 변경된 이후에 다시 테이블을 갱신해서, 변경 여부를 즉시 반영
					}else {
						JOptionPane.showMessageDialog(null, "id:" + userid + "번 유저 Active");
						dao.activeUpdate(userid, "Y");// 가져온 id 값을 이용해 db에서 해당 user의 isactive 값 변경
						getUserTable();// 값이 변경된 이후에 다시 테이블을 갱신해서, 변경 여부를 즉시 반영
					}
					
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "변경할 user가 선택되지 않았습니다.");
				}catch(IndexOutOfBoundsException e1){
					JOptionPane.showMessageDialog(null, "변경할 user가 선택되지 않았습니다.");
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		getUserTable();
		userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userScrollPane.setViewportView(userTable);

		
		
		
		historyScrollPane = new JScrollPane();
		historyScrollPane.setBounds(77, 165, 660, 296);
		contentPane.add(historyScrollPane);
		historyScrollPane.setVisible(false);// 시작 화면이 유저 조회 창 이므로 내역 창 비활성화

		historyTable = new JTable();// 내역 조회 창
		DefaultTableModel historyModel = new DefaultTableModel();
		historyTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {// 상세정보 출력 이벤트
				int row = historyTable.rowAtPoint(e.getPoint());
				int column = historyTable.columnAtPoint(e.getPoint());
				int user = 0;
				String information = null;
				if (row != -1 && (column == 0 || column == 2)) {// 후원자,수혜자 칸 클릭시에만 반응

					Object userid = historyTable.getValueAt(row, column);// 선택된 유저 ID를 추출
					user = Integer.parseInt(userid.toString());// int로 변환
					Object login_id = historyTable.getValueAt(row, column+1);
					String logid = login_id.toString();
					information = logid + " 의 후원/수혜 내역\n";
					
					// JOptionPane.showMessageDialog(null, user+"의 상세정보");//동작 테스트용 코드

				}else if (row != -1 && (column == 1 || column == 3)) {
					Object userid = null;
					if (column == 1) {
					userid = historyTable.getValueAt(row, 0);
					}else if(column == 3) {
					userid = historyTable.getValueAt(row, 2);
					}
					user = Integer.parseInt(userid.toString());// int로 변환
					Object login_id = historyTable.getValueAt(row, column);
					String logid = login_id.toString();
					information = logid + " 의 후원/수혜 내역\n";
				}
				
				try {
					List<HistoryVO> hislist = dao.read(user);
					for (HistoryVO his : hislist) {
						int giverid = his.getGiver_id();
						int receiveid = his.getReceiver_id();
						int amount = his.getAmount();
						Date date = his.getDate();
						String isreceived = his.getIs_received();
						String onehistory = "후원자 id:" + giverid + "    수혜자 id:" + receiveid + "    금액:" + amount
								+ "    일자:" + date + "    수혜 여부:" + isreceived + "\n";
						information += onehistory;
					}
					if(column >= 0 && column <=3) {
					JOptionPane.showMessageDialog(null, information);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "오류 발생");
				}
			}
		});

		List<HistoryVO> datafromDB = dao.historyRead(); // DB에서 데이터 읽기
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
		historyTable.setModel(model);// 테이블에 위에 작성한 사항 적용

		historyTable.setVisible(false);// 시작 화면이 유저 조회 창 이므로 내역 창 비활성화

		historyScrollPane.setViewportView(historyTable);

		
		
		
		txtSearch = new JTextField();
		// 검색 기능에 필요한 테이블 선언
		DefaultTableModel userModel = (DefaultTableModel) userTable.getModel();
		// RowSorter 설정
		TableRowSorter<TableModel> userSorter = new TableRowSorter<>(userModel);
		userTable.setRowSorter(userSorter);

		txtSearch.getDocument().addDocumentListener(new DocumentListener() {// 검색 기능
			public void insertUpdate(DocumentEvent e) {
				filterTable(); // 텍스트가 변경될 때마다 필터링
			}

			public void removeUpdate(DocumentEvent e) {
				filterTable(); // 텍스트가 변경될 때마다 필터링
			}

			public void changedUpdate(DocumentEvent e) {
				filterTable(); // 텍스트가 변경될 때마다 필터링
			}

			// 필터링 메서드
			private void filterTable() {
				String searchText = txtSearch.getText().toLowerCase(); // 검색 텍스트 소문자로 변환
				if (searchText.trim().length() == 0) {
					userSorter.setRowFilter(null); // 입력이 없으면 필터를 해제
				} else {
					userSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // 필터링
				}
			}
		});
		
		//로그아웃 버튼,누르면 현재 창을 종료하고, 로그인 창으로 이동
		/*
		txtSearch.setBounds(77, 144, 660, 21);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnLogout = new JButton("로그아웃");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				setVisible(false);
				new LoginFrame().setVisible(true);
			}
		});
		btnLogout.setBounds(677, 27, 97, 23);
		contentPane.add(btnLogout);
		*/
	}
	
	
	
	
	//db의 user 테이블을 읽어와 userTable에 출력해주는 메소드
	private void getUserTable() throws ClassNotFoundException, SQLException {// 해당 코드가 여러 번 사용되어 history 테이블과 달리 메소드로 따로 분리함

		adminDAO dao = new adminDAO();
		List<UserVO> datafromDB = dao.read(); // DB에서 데이터 읽기

		String[] columnNames = { "ID", "LOGIN_ID", "이름", "타입", "전화번호", "IS_ACTIVE" };// 테이블에 출력될 컬럼명
		DefaultTableModel model = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 모든 셀 편집 불가
            }
        };
        
		for (UserVO row : datafromDB) {// 테이블에 db 값 옮기기
			Object[] rowData = { row.getId(), row.getLogin_id(), row.getName(), row.getUser_type(), row.getPhone(),
					row.getIs_active() };
			model.addRow(rowData);
		}

		userTable.setModel(model);// 테이블에 위에 작성한 사항 적용
	}
}
