/*
	 * 파일 설명: 받은 후원 내역 템플릿(Temlist3)///테이블구조**
	 * (수혜내역페이지에서 사용될 템플릿)
	 * 파일 상세: 수혜자가 받은 후원금 내역(후원자명, 일자, 금액, 메세지)정보와 조회기능
	 * Input: 로그인 한 수혜자 정보에 따른 후원자의 정보( 후원자명, 일자, 금액, 메세지)
	 * Feature: 
	 *  - 후원받은 내역을 아이디순?날짜순(최신)?으로 보이게 할지 보류
	 *   >DB에 저장된 칼럼,내용을 보여줌
	 *   >조회창에서 조회시 그 내역을 확인할수있음
	 *  Output: 없음.
	 */

package RECEIVER3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.BorderLayout;

public class Temlist3 extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Create the panel.
     */
    public Temlist3() {
        setLayout(new BorderLayout());  // BorderLayout을 사용하여 레이아웃 관리

        // 테이블 데이터와 컬럼 이름 설정
        String[] columnNames = {"ID", "후원자명", "일시", "금액", "메세지"};
        Object[][] data = {
            {1, "홍길동", "2025-04-16", 10000, "항상 응원합니다! 건강하세요"},
            {2, "김철수", "2025-04-15", 5000, "힘내세요"},
            {3, "이영희", "2025-04-14", 1500, "도움이 되길 바랍니다."}
        };

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

    public static void main(String[] args) {
        // JFrame 생성
        JFrame frame = new JFrame("Table Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(741, 475);

        // Temlist3 패널을 프레임에 추가
        frame.getContentPane().add(new Temlist3());

        // 프레임 보이기
        frame.setVisible(true);
    }
}
