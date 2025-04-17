package template;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import admin.HistoryVO;
import java.awt.FlowLayout;
import java.awt.GridLayout;

/* **
 * 파일 설명: FE 검색기능 있는 테이블 기본 템플릿
 * 파일 상세: 모든 열 검색 기능 있는 테이블
 *  - 2차원 배열 테이블과 열 리스트를 넣으면 테이블이 렌더링된다.
 *	- 테이블이 필요한 모든 상황에서 사용 가능. 
 * Input: 2차원 배열 테이블(Object[][]), 열 리스트(Object[])
 * Feature: 
 *  - 열 리스트를 맨 위에 뿌리고, 2차원 배열 테이블을 그에 맞게 출력함.
 * 	- input textfield 에는 모든 열, 모든 행에 대하여 검색 기능 구현됨
 * Output: 검색 기능 있는 테이블 JPanel
 * 
 */



public class TableTemplate extends JPanel {
	private JTable historyTable;
	private JScrollPane historyScrollPane;
	private JTextField txtSearch;
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextField textField;
	
	// 테스트용 데이터. 없으면 이걸로 생성됨
	private static Object[] testTableColumn = {"Column A", "Column B", "Column C", "Column D", "Column E"};
	private static Object[][] testTableData = new Object[][] {//테스트용 임시 테이블
		{"a", "b", null, null, null},
		{"c", "d", null, null, null},
		{"e", null, null, null, null},
		{null, null, null, null, null},
	};

	/**
	 * Create the panel.
	 */
	public TableTemplate() {
		this(testTableData, testTableColumn);
	}
	public TableTemplate(Object[][] tableData,Object[] tableColumn) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		historyScrollPane = new JScrollPane();
		this.add(historyScrollPane);
		historyScrollPane.setVisible(false);//시작 화면이 유저 조회 창 이므로 내역 창 비활성화
		
		historyTable = new JTable();//내역 조회 창
//		DefaultTableModel historyModel = new DefaultTableModel();
		historyTable.setModel(new DefaultTableModel(testTableData,testTableColumn));
		
		historyScrollPane.setViewportView(historyTable);
		//검색 기능에 필요한 테이블 선언
		DefaultTableModel userModel = (DefaultTableModel) historyTable.getModel();
		// RowSorter 설정
        TableRowSorter<TableModel> userSorter = new TableRowSorter<>(userModel);
        historyTable.setRowSorter(userSorter);
        
        
        
        txtSearch = new JTextField();
        
        historyScrollPane.setColumnHeaderView(txtSearch);
        
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
		txtSearch.setColumns(10);
	}

}
