
/*
	 * 파일 설명: 수혜자 후원금 내역확인 템플릿
	 * 파일 상세: 수혜자가 받은 후원금 내역( 후원자명, 일자, 금액, 메세지)과 [수락,거절] 실행
	 * Input: 로그인 한 수혜자 정보/( 후원자명, 일자, 금액, 메세지)
	 * Feature: 
	 *  - 수락버튼: 후원자가 후원한 내역을 수락하면 후원금 받기
	 *  - 거절버튼: 보류
	 *  Output: 없음.
	 */

package template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class tem_list extends JPanel {

	public tem_list(String 후원자이름, String 금액, String 일자, String 메세지) {
		setBackground(Color.WHITE);
		setLayout(null); // 필요 시 다른 레이아웃 써도 됨
		setPreferredSize(new Dimension(429, 309)); // 크기 지정

		JLabel lblTitle = new JLabel("후원자 " + 후원자이름 + " 님");
		lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 15));
		lblTitle.setBounds(10, 10, 300, 20);
		add(lblTitle);

		JLabel lblAmount = new JLabel("금액 : " + 금액 + "원");
		lblAmount.setBackground(Color.LIGHT_GRAY);
		lblAmount.setBounds(10, 40, 189, 20);
		add(lblAmount);

		JLabel lblDate = new JLabel("일자 : " + 일자);
		lblDate.setBackground(Color.LIGHT_GRAY);
		lblDate.setBounds(10, 70, 189, 20);
		add(lblDate);

		JLabel lblMsg = new JLabel("<html>메세지 : " + 메세지.replaceAll("\n", "<br>") + "</html>");
		lblMsg.setBounds(10, 100, 189, 100);
		add(lblMsg);

		JButton btnNewButton_1 = new JButton("거절");
		btnNewButton_1.setFont(new Font("나눔고딕", Font.BOLD, 15));
		btnNewButton_1.setBounds(246, 120, 100, 70);
		add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("수락");
		btnNewButton_1_1.setBackground(new Color(135, 206, 235));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setFont(new Font("나눔고딕", Font.BOLD, 15));
		btnNewButton_1_1.setBounds(246, 40, 100, 70);
		add(btnNewButton_1_1);
	}
}
