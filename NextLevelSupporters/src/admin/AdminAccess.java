/*
화면 상단의 choice 창에서 유저별 조회, 내역 별 조회 옵션 선택 시
해당 테이블 활성화, 이전 테이블 비활성화

유저별 조회 화면에서는 active 변경을 원하는 유저의 줄을 선택 후 active,dactive 버튼 클릭 시 
해당 줄의 userid를 받아 와 db에 접속해서 is_active를 변경

아직 검색 기능 미구현

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
	 */
	public static void main(String[] args) {
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
	 */
	public AdminAccess() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Choice choice = new Choice();
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
				String userid = (String) userTable.getValueAt(userTable.getSelectedRow(), 0);
				JOptionPane.showMessageDialog(null, "id:"+userid+"번 유저 Active");
				//가져온 id 값을 이용해 db에서 해당 user의 isactive 값 변경할 코드가 필요
			}
		});
		btnActive.setBounds(186, 513, 97, 23);
		contentPane.add(btnActive);

		btnDactive = new JButton("Dactive");//유저 조회 창에서 선택한 유저를 Dactive 하는 버튼
		btnDactive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String userid = (String) userTable.getValueAt(userTable.getSelectedRow(), 0);
				JOptionPane.showMessageDialog(null, "id:"+userid+"번 유저 Dactive");
				//가져온 id 값을 이용해 db에서 해당 user의 isactive 값 변경할 코드가 필요
			}
		});
		btnDactive.setBounds(547, 513, 97, 23);
		contentPane.add(btnDactive);
		
		userScrollPane = new JScrollPane();
		userScrollPane.setBounds(77, 165, 660, 296);
		contentPane.add(userScrollPane);

		userTable = new JTable();//유저 조회 창
		//검색 기능 미구현으로 주석 처리한 코드
		/*
		DefaultTableModel userModel = (DefaultTableModel) userTable.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(userModel);
        userTable.setRowSorter(sorter);
        */
		userTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", null, "a", null, null, null},
				{"2", null, "b", null, null, null},
				{"3", null, "c", null, null, null},
				{"4", null, null, null, null, null},
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
		
		
		DefaultTableModel historyModel = new DefaultTableModel();
		historyTable = new JTable();//내역 조회 창
		historyTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//상세정보 출력 이벤트
				 int row = historyTable.rowAtPoint(e.getPoint());
		         int column = historyTable.columnAtPoint(e.getPoint());
		         if(row != -1 && ( column == 0 || column == 1 )) {//후원자,수혜자 칸 클릭시에만 반응
		        	 
		        	 String user = (String) historyTable.getValueAt(row, column);//선택된 유저의 이름을 추출
		        	 JOptionPane.showMessageDialog(null, user+"의 상세정보");
		         }
			}
		});
		historyTable.setModel(new DefaultTableModel(
			new Object[][] {
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
		
		//검색 기능 미구현으로 주석 처리한 코드
		/*
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
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
                    sorter.setRowFilter(null); // 텍스트가 비어 있으면 필터를 해제
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // 정규식으로 필터링
                }
            }
        });
		*/
		
		txtSearch.setBounds(77, 144, 660, 21);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
	}
}






