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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Raccept.ReceiverHistoryVo;

public class Temlist3 extends JPanel {

    private List<ReceiverHistoryVo> vos;// 후원 내역 데이터 리스트

    public Temlist3(List<ReceiverHistoryVo> vos) {
        this.vos = vos;
        setLayout(new BorderLayout());

        String[] columnNames = {"NO.", "후원자명", "일시", "금액", "메세지", "내역"};
        Object[][] data = new Object[vos.size()][6];
     // 후원 내역 데이터를 테이블 배열에 담기
        for (int i = 0; i < vos.size(); i++) {// 테이블 데이터 구성
            ReceiverHistoryVo vo = vos.get(i);
            data[i][0] = i + 1;// 순번
            data[i][1] = vo.getGiverName();// 후원자명
            data[i][2] = vo.getCreateDate().toString();// 일시
            data[i][3] = String.format("%,d", vo.getAmount());// 금액 (콤마 포맷)
            data[i][4] = vo.getMessage();// 메세지
            data[i][5] = "조회";// 버튼 텍스트
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);// 테이블 생성
        JTable table = new JTable(model);// JTable 생성

     // 테이블 스타일 설정
        table.setRowHeight(30);
        table.setFont(new Font("나눔고딕", Font.PLAIN, 15));
        table.getTableHeader().setFont(new Font("나눔고딕", Font.BOLD, 16));
        table.setEnabled(true);

        TableColumn column;
        column = table.getColumnModel().getColumn(0); column.setPreferredWidth(30);   // NO.
        column = table.getColumnModel().getColumn(1); column.setPreferredWidth(90);   // 후원자명
        column = table.getColumnModel().getColumn(2); column.setPreferredWidth(90);   // 일시
        column = table.getColumnModel().getColumn(3); column.setPreferredWidth(70);   // 금액
        column = table.getColumnModel().getColumn(4); column.setPreferredWidth(200);  // 메세지
        column = table.getColumnModel().getColumn(5); column.setPreferredWidth(70);   // 내역

    // 버튼 셀 렌더링 및 이벤트 연결
        table.getColumn("내역").setCellRenderer(new ButtonRenderer());
        table.getColumn("내역").setCellEditor(new ButtonEditor(new JCheckBox(), table, vos));

    // 테이블에 스크롤 추가
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));
        
        // 검색 기능 구현
        JTextField textSearch = new JTextField();
        TableRowSorter<TableModel> historySorter = new TableRowSorter<>(model);
        table.setRowSorter(historySorter);
        
        textSearch.getDocument().addDocumentListener(new DocumentListener() {// 검색 기능
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
				String searchText = textSearch.getText().toLowerCase(); // 검색 텍스트 소문자로 변환
				if (searchText.trim().length() == 0) {
					historySorter.setRowFilter(null); // 입력이 없으면 필터를 해제
				} else {
					historySorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // 필터링
				}
			}
		});
        
        
        panel.add(textSearch, BorderLayout.CENTER);
        textSearch.setColumns(10);
        
        JLabel lblNewLabel = new JLabel(" 테이블 검색: ");
        panel.add(lblNewLabel, BorderLayout.WEST);
    }

    // 버튼 렌더러
    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                                                                 boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // 버튼 클릭 시 팝업 띄우는 클래스
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;// 조회 버튼
        private boolean clicked;
        private JTable table;
        private List<ReceiverHistoryVo> vos;// 후원 내역 리스트

        public ButtonEditor(JCheckBox checkBox, JTable table, List<ReceiverHistoryVo> vos) {
            super(checkBox);
            this.table = table;
            this.vos = vos;
            button = new JButton("조회");// 버튼 생성 및 텍스트 설정
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());// 클릭 시 편집 종료
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(JTable table, Object value,
                                                              boolean isSelected, int row, int column) {
            clicked = true;
            return button;
        }
/*
 * etCellEditorValue() 메서드는 JTable 안에 있는 셀(버튼)을 클릭했을 때 실행되는 코드
 * 팝업 창을 띄워서 해당 후원자의 상세 정보를 보여주는 역할
 */
        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                int row = table.getSelectedRow();
                ReceiverHistoryVo vo = vos.get(row);

                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                Font labelFont = new Font("나눔고딕", Font.PLAIN, 13);
                Font valueFont = new Font("나눔고딕", Font.BOLD, 13);

                int y = 0;
                y = addRow(panel, gbc, "후원자명:", vo.getGiverName(), labelFont, valueFont, y);
                y = addRow(panel, gbc, "일시:", vo.getCreateDate().toString(), labelFont, valueFont, y);
                y = addRow(panel, gbc, "금액:", String.format("%,d 원", vo.getAmount()), labelFont, valueFont, y);

                //계좌번호 Null 체크
                String account = vo.getAccount();
                if (account == null || account.isBlank()) account = "(계좌정보 없음)";
                y = addRow(panel, gbc, "계좌번호:", account, labelFont, valueFont, y);

                y = addRow(panel, gbc, "메세지:", vo.getMessage(), labelFont, valueFont, y);

                JOptionPane.showMessageDialog(null, panel, "후원 상세정보", JOptionPane.INFORMATION_MESSAGE);
            }
            clicked = false;
            return "조회";
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        // 라벨-값 한 행 추가 메서드
        private int addRow(JPanel panel, GridBagConstraints gbc, String labelText, String valueText,
                           Font labelFont, Font valueFont, int y) {
            gbc.gridx = 0;
            gbc.gridy = y;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = new java.awt.Insets(2, 10, 2, 10);// 간격 설정
            JLabel label = new JLabel(labelText);
            label.setFont(labelFont);
            panel.add(label, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel value = new JLabel(valueText);
            value.setFont(valueFont);
            panel.add(value, gbc);

            return y + 1;
        }
    }
}
