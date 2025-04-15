/*
	 * 파일 설명: 수혜자 후원금 내역확인 템플릿
	 * 파일 상세: 수혜자가 받은 후원금 내역 템플릿 목록이 여러개 보여짐
	 * 	- 상단 메뉴바에 로그인 한 사람(수혜자)
	 * // 수혜자 명, 수혜내역버튼 , 로그아웃 버튼 보여짐
	 * 화면 메인에는 후원받은 내역이 나열
	 * Input: 로그인 한 수혜자 정보/후원내역:(후원자명, 일자, 금액, 메세지)가 들어갈예정
	 * Feature: 
	 *  - 로그인 한 수혜자 정보보여지고 // 후원내역:(후원자명, 일자, 금액, 메세지) 보여짐
	 *  - 후원자가 후원한 내역을 수락하면 후원금 받기
	 */

package Receiver01;

import javax.swing.*;
import java.awt.*;
import template.tem_list;
import template.NLSMenuTemplate;

public class R_Accept extends JFrame {

	private JPanel contentPane;

	public R_Accept() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 477);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitle = new JLabel("후원내역");
		lblTitle.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 20));
		lblTitle.setBounds(72, 74, 137, 30);
		contentPane.add(lblTitle);

		
		// tem_list 패널 추가
		tem_list item = new tem_list("홍길동", "10,000", "2025.04.10", "항상 응원합니다!\n건강하세요 :)");
		item.setBounds(72, 124, 429, 232); // 위치/크기 조정
		contentPane.add(item);
		
		NLSMenuTemplate menuTemplate = new NLSMenuTemplate("김성진" , 1);
		menuTemplate.setBounds(0, 0, 690, 42);
		contentPane.add(menuTemplate);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				R_Accept frame = new R_Accept();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
