/*
화면 상단의 choice 창에서 유저별 조회, 내역 별 조회 옵션 선택 시
해당 테이블 활성화, 이전 테이블 비활성화

유저별 조회 화면에서는 active 변경을 원하는 유저의 줄을 선택 후 active,dactive 버튼 클릭 시 
해당 줄의 userid를 받아 와 db에 접속해서 is_active를 변경

검색 창에 텍스트 입력 시 user의 정보에 컬럼 상관 없이 해당 텍스트가 들어있는 user의 정보만 필터링해서 출력

내역 별 조회 화면에서는 내역이 출력되고, 후원자 또는 수헤자 이름을 클릭 시
해당 user의 이름을 가져와 db에서 정보를 받아 온 뒤 별도의 팝업으로 출력

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
import javax.swing.DefaultListModel;
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
import java.util.List;

public class AdminAccess extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnActive,btnDactive;
	private JTable userTable;
	private JScrollPane userScrollPane;
	private JTable historyTable;
	private JScrollPane historyScrollPane;
	private JTextField txtSearch;
	
	/**
	 * Launch the application.
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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public AdminAccess() throws ClassNotFoundException, SQLException {
		adminDAO dao = new adminDAO(); //dao 클래스에 대한 객체 생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Choice choice = new Choice();//유저 창, 내역 창 전환
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selected = choice.getSelectedItem();
				if(selected.equals("유저별 조회")) {//유저 별 옵션 선택 시 유저 창 활성화 및 내역 창 비활성화
					btnActive.setVisible(true);
					btnDactive.setVisible(true);
					txtSearch.setVisible(true);
					userTable.setVisible(true);
					userScrollPane.setVisible(true);
					historyScrollPane.setVisible(false);
					historyTable.setVisible(false);

				}else {//내역 창 활성화,유저 창 비활성화
					btnActive.setVisible(false);
					btnDactive.setVisible(false);
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
		
		
		btnActive = new JButton("Active");//유저 조회 창에서 선택한 유저를 Active 하는 버튼
		btnActive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String id = (String) userTable.getValueAt(userTable.getSelectedRow(), 0);
					int userid = Integer.parseInt(id);
				JOptionPane.showMessageDialog(null, "id:"+userid+"번 유저 Active");
				dao.activeUpdate(userid, "Y");//가져온 id 값을 이용해 db에서 해당 user의 isactive 값 변경
				
				}catch(ArrayIndexOutOfBoundsException e1){
					JOptionPane.showMessageDialog(null, "변경할 user가 선택되지 않았습니다.");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnActive.setBounds(186, 513, 97, 23);
		contentPane.add(btnActive);
		
		
		btnDactive = new JButton("Dactive");//유저 조회 창에서 선택한 유저를 Dactive 하는 버튼
		btnDactive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				String id = (String) userTable.getValueAt(userTable.getSelectedRow(), 0);
				int userid = Integer.parseInt(id);
				JOptionPane.showMessageDialog(null, "id:"+userid+"번 유저 Dactive");
				dao.activeUpdate(userid, "N");//가져온 id 값을 이용해 db에서 해당 user의 isactive 값 변경
				
				}catch(ArrayIndexOutOfBoundsException e1){
					JOptionPane.showMessageDialog(null, "변경할 user가 선택되지 않았습니다.");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDactive.setBounds(547, 513, 97, 23);
		contentPane.add(btnDactive);
		
		
		userScrollPane = new JScrollPane();
		userScrollPane.setBounds(77, 165, 660, 296);
		contentPane.add(userScrollPane);

		
		userTable = new JTable();//유저 조회 창
		userTable.setModel(new DefaultTableModel(//테스트용 임시 테이블
			new Object[][] {
				{"1", "aaa", "\uD64D\uAE38\uB3D9", null, null, null},
				{"2", "bbb", "\uAE40\uACF5\uC218", null, null, null},
				{"3", "ccc", "\uC774\uD574\uC194", null, null, null},
				{"4", "ddd", "\uBC15\uAE38\uC218", null, null, null},
				{"5", "eee", "\uC720\uC9C0\uC740", null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "LOGIN_ID", "\uC774\uB984", "\uD0C0\uC785", "\uC804\uD654\uBC88\uD638", "IS_ACTIVE"
			}
		));
		
        
		userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userScrollPane.setViewportView(userTable);
		
		historyScrollPane = new JScrollPane();
		historyScrollPane.setBounds(77, 165, 660, 296);
		contentPane.add(historyScrollPane);
		historyScrollPane.setVisible(false);//시작 화면이 유저 조회 창 이므로 내역 창 비활성화
		
		
		
		historyTable = new JTable();//내역 조회 창
		DefaultTableModel historyModel = new DefaultTableModel();
		historyTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//상세정보 출력 이벤트
				 int row = historyTable.rowAtPoint(e.getPoint());
		         int column = historyTable.columnAtPoint(e.getPoint());
		         if(row != -1 && ( column == 0 || column == 1 )) {//후원자,수혜자 칸 클릭시에만 반응
		        	 
		        	 String user = (String) historyTable.getValueAt(row, column);//선택된 유저의 이름을 추출
		        	 try {
		        		 List<HistoryVO> hislist = dao.getUserHistory(user);
		        		 JOptionPane.showMessageDialog(null, user+"의 상세정보\n"+hislist);//실제로 실행해 보고 팝업 창 디자인 수정할 것
		        		 
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "오류 발생");
					}
		        	 //JOptionPane.showMessageDialog(null, user+"의 상세정보");//동작 테스트용 코드
		        	 
		        	 
		         }
			}
		});
		historyTable.setModel(new DefaultTableModel(
			new Object[][] {//테스트용 임시 테이블
				{"a", "b", null, null, null},
				{"c", "d", null, null, null},
				{"e", null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\uD6C4\uC6D0\uC790", "\uC218\uD61C\uC790", "\uAE08\uC561", "\uC77C\uC790", "\uC218\uD61C \uC5EC\uBD80"
			}
		));
		historyTable.setVisible(false);//시작 화면이 유저 조회 창 이므로 내역 창 비활성화
		
		historyScrollPane.setViewportView(historyTable);
		
		
		txtSearch = new JTextField();
		//검색 기능에 필요한 테이블 선언
		DefaultTableModel userModel = (DefaultTableModel) userTable.getModel();
		// RowSorter 설정
        TableRowSorter<TableModel> userSorter = new TableRowSorter<>(userModel);
        userTable.setRowSorter(userSorter);
        
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {//검색 기능
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
		
		
		txtSearch.setBounds(77, 144, 660, 21);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
	}
}






