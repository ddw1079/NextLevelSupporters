package giver;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import giver.GiverHistoryDAO;
import giver.GiverHistoryVO;

public class GiverHistoryTable extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * 파일 설명: 후원자의 후원 내역 테이블
	 * 파일 상세: 후원자의 후원 내역을 출력한다.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public GiverHistoryTable() throws ClassNotFoundException, SQLException {
		this(1);
	}
	public GiverHistoryTable(int user_id) throws SQLException, ClassNotFoundException {
		if(user_id == -1) {
			System.out.println("기본 클래스 선언됨.");
		}
        setLayout(new BorderLayout());  // BorderLayout을 사용하여 레이아웃 관리
        GiverHistoryDAO ghdao = new GiverHistoryDAO();
        ArrayList<GiverHistoryVO> ghList = ghdao.read(user_id);
        // 테이블 데이터와 컬럼 이름 설정
        String[] columnNames = {"ID", "수혜자명", "일자", "금액", "메세지"};

        ArrayList<Object[]> dataList = new ArrayList<>();
        
        
        for(GiverHistoryVO temp: ghList) {
        	// 여기에 데이터를 넣고싶어...
        	Object[] newRow = {
        		temp.getIdx(),
        		temp.getReceiver_id(),
        		temp.getCreate_date(),
        		temp.getAmount(),
        		temp.getMessage()
        	};
        	dataList.add(newRow);
        }
        Object[][] data = dataList.toArray(new Object[0][]);
        

        // DefaultTableModel을 이용하여 테이블 모델 설정
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // JTable 생성
        JTable table = new JTable(model);

        // 각 칼럼의 너비를 수동으로 설정
        TableColumn column = null;

        // 첫 번째 칼럼(ID) 너비 설정
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(25);  // ID 칼럼 너비 50
        
        // 세번째 칼럼(메세지) 너비 설정
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(100); // 메세지 칼럼 너비 500

        // 다섯 번째 칼럼(메세지) 너비 설정
        column = table.getColumnModel().getColumn(4);
        column.setPreferredWidth(450); // 메세지 칼럼 너비 500
        

        // 테이블을 JScrollPane에 추가하여 스크롤 가능하게 만들기
        JScrollPane scrollPane = new JScrollPane(table);
        
        // 테이블 비활성화 (수정 불가능)
        table.setEnabled(false);  // 테이블을 비활성화하여 수정 불가!

        // JScrollPane을 패널에 추가
        add(scrollPane, BorderLayout.CENTER);

        // 테이블의 열 너비 변경을 적용한 후 새로 고침
        table.getTableHeader().repaint(); // 헤더 새로 고침
        table.repaint(); // 테이블 내용 새로 고침
	}

}
