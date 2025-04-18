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

package Rlist;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Raccept.ReceiverHistoryVo;


public class Temlist3 extends JPanel {

public Temlist3(List<ReceiverHistoryVo> vos) {
    setLayout(new BorderLayout());

    String[] columnNames = {"ID", "후원자명", "일시", "금액", "메세지"};
    Object[][] data = new Object[vos.size()][5];

    for (int i = 0; i < vos.size(); i++) {
        ReceiverHistoryVo vo = vos.get(i);
        data[i][0] = vo.getGiverID();
        data[i][1] = vo.getGiverName();
        data[i][2] = vo.getCreateDate().toString();
        data[i][3] = String.format("%,d", vo.getAmount());
        data[i][4] = vo.getMessage();
    }

    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    JTable table = new JTable(model);
    table.setEnabled(false); // 테이블 수정 불가

    // 칼럼 너비 설정
    TableColumn column;
    column = table.getColumnModel().getColumn(0); column.setPreferredWidth(40);  // ID
    column = table.getColumnModel().getColumn(1); column.setPreferredWidth(100); // 후원자명
    column = table.getColumnModel().getColumn(2); column.setPreferredWidth(100); // 일시
    column = table.getColumnModel().getColumn(3); column.setPreferredWidth(80);  // 금액
    column = table.getColumnModel().getColumn(4); column.setPreferredWidth(300); // 메세지

    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);
	}
}
