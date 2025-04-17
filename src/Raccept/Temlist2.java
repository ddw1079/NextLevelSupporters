
/*
	 * 파일 설명: 후원금 내역 템플릿
	 * (수혜자 페이지에서 사용될 템플릿)
	 * 파일 상세: 수혜자가 받아야하는 후원금 내역(후원자명, 일자, 금액, 메세지)정보와 [수락,거절]버튼
	 * Input: 로그인 한 수혜자 정보에 따른 후원자의 정보( 후원자명, 일자, 금액, 메세지)
	 * Feature: 
	 *  - 후원자가 후원한 내역을 확인 후 *받기*버튼을 누르면 
	 *   >DB로 받은 금액 정보가 들어감.
	 *   >팝업창으로 후원자명, 금액수령 확인창이 한번 더 띄워짐.
	 *  - 거절버튼: 보류
	 *  Output: 없음.
	 */

package Raccept;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Temlist2 extends JPanel {// Temlist2 클래스: 하나의 후원 정보를 표시

	public Temlist2() {
	    this("후원자", "0", "날짜", "메세지");
	}
	
	public Temlist2(String 후원자이름, String 금액, String 일자, String 메세지) {
		setBackground(Color.WHITE);
		setLayout(null); // 레이아웃 매니저 사용하지 않고 일단 수동 배치사용(상세디자인 회의 후 변경가능)
		setPreferredSize(new Dimension(280, 211)); // 크기 지정

		// 후원자 이름을 라벨
		JLabel lblTitle = new JLabel(" 후원자 " + 후원자이름 + " 님");
		lblTitle.setOpaque(true);
		lblTitle.setBackground(SystemColor.scrollbar);
		lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 15));
		lblTitle.setBounds(0, 0, 280, 20);
		add(lblTitle);

		JLabel lblAmount = new JLabel("금액 : " + 금액 + "원");
		lblAmount.setBackground(Color.LIGHT_GRAY);
		lblAmount.setBounds(10, 40, 151, 20);
		add(lblAmount);

		JLabel lblDate = new JLabel("일자 : " + 일자);
		lblDate.setBackground(Color.LIGHT_GRAY);
		lblDate.setBounds(10, 70, 151, 20);
		add(lblDate);

		// 후원 메시지 라벨 (줄바꿈 지원을 위해 HTML 형식 사용)
		JLabel lblMsg = new JLabel("<html>메세지 : " + 메세지.replaceAll("\n", "<br>") + "</html>");
		lblMsg.setBounds(10, 100, 151, 100);
		add(lblMsg);

		// '거절' 버튼 - 기능은 아직**
		JButton btnNewButton_1 = new JButton("거절");
		btnNewButton_1.setFont(new Font("나눔고딕", Font.BOLD, 15));
		btnNewButton_1.setBounds(190, 120, 81, 70);
		add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("\uBC1B\uAE30");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 예시 데이터 (필요시 변수로 대체)
		        String 후원자이름 = "홍길동";
		        int 금액 = 10000;

		        // 팝업 메시지 구성
		        String message = "후원자 " + 후원자이름 + " 님에게 \n금액: " + 금액 + "원\n받았습니다. ";

		        // 팝업 띄우기
		        JOptionPane.showMessageDialog(null, message, "후원 내역", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnNewButton_1_1.setBackground(new Color(250, 250, 210));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setFont(new Font("나눔고딕", Font.BOLD, 15));
		btnNewButton_1_1.setBounds(190, 40, 81, 70);
		add(btnNewButton_1_1);
	}
}
