/*
	 * 파일 설명: 수혜자 후원금 내역수락 페이지 (JFrame)
	 * 파일 상세: 수혜자가 후원금 받기 전의 후원내역 템플릿 목록이 보임
	 * 	- 상단 메뉴바(이미만들어진 탬플릿사용)에 로그인 한 사람(수혜자)이름,정보보임
	 *  ->> (수혜자 명, 수혜내역버튼 , 로그아웃 버튼 보여짐)
	 * 화면 메인에는 후원받은 내역목록 스크롤사용하여 아래로 쭉 보이게 할 예정
	 * Input: 1.로그인 한 수혜자 정보
	 * 		  2.후원자 정보후원내역:(후원자명, 일자, 금액, 메세지)가 들어갈예정
	 * Feature: 
	 *   - 후원자가 후원한 내역을 확인 후 *받기*버튼을 누르면 
	 *   >DB로 받은 금액 정보가 들어감.
	 *   >팝업창으로 후원자명, 금액수령 확인창이 한번 더 띄워짐.
	 *   -***받은 금액들은 *합계*되어 나중에 수혜내역에 누적정보로도 확인가능하게 할 예정
	 */

package RECEIVER2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import template.NLSMenuTemplate;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

import java.util.ArrayList;
import java.awt.Dimension;
import javax.swing.Box;

public class RAccept2 extends JFrame {//RAccept2 클래스는 후원 내역창

	private JPanel contentPane;

	public RAccept2() {// 생성자
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 477);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitle = new JLabel("후원내역");
		lblTitle.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 20));
		lblTitle.setBounds(27, 64, 311, 30);
		contentPane.add(lblTitle);

		
		// *후원 항목 하나를 표시* (Temlist2 클래스 템플릿 사용)
		Temlist2 item = new Temlist2("홍길동", "10,000", "2025.04.10", "항상 응원합니다!\n건강하세요 :)");
		item.setBackground(SystemColor.menu);
		item.setBounds(67, 114, 280, 204); // 위치/크기 조정
		contentPane.add(item);
		
		
		Temlist2 temlist2 = new Temlist2("김성진", "5,000", "2025.03.29", "힘내세요^^)");
		temlist2.setBackground(SystemColor.menu);
		temlist2.setBounds(67, 331, 280, 211);
		contentPane.add(temlist2);
		
		// *상단 메뉴바* (NLSMenuTemplate 템플릿 사용)
		NLSMenuTemplate menuTemplate = new NLSMenuTemplate("김성진" , 1);
		menuTemplate.setBounds(0, 0, 690, 42);
		contentPane.add(menuTemplate);
		
		//JScrollPane 안에 들어갈 listPanel을 생성 → 수직 박스 레이아웃 지정.
		// 후원 항목들을 담을 리스트 패널 생성
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));//수직으로 스트롤됨
		listPanel.setBackground(SystemColor.controlLtHighlight);

		
		// 스크롤 영역 생성 및 패널 연결
		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setBounds(27, 102, 637, 336);
		contentPane.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		
		/**<ArrayList<Temlist2>로 Temlist2 항목 여러 개 추가>
		 * 
		// 임시로 ArrayList에 Temlist2 객체들을 저장
		// (예시 후원 항목 생성)
		ArrayList<Temlist2> donationList = new ArrayList<>();
		donationList.add(new Temlist2("홍길동", "10,000", "2025.04.10", "항상 응원합니다!\n건강하세요 :)"));
		donationList.add(new Temlist2("이순신", "20,000", "2025.04.11", "정말 감사합니다!\n힘내세요~"));
		donationList.add(new Temlist2("세종대왕", "15,000", "2025.04.12", "늘 응원합니다 :)"));

		// 리스트 패널에 항목들 추가
		for (Temlist2 item : donationList) {
		    t.setBackground(SystemColor.menu);
			listPanel.add(item);
			listPanel.add(Box.createVerticalStrut(10)); // 항목 간 여백
			
			------->> 이 구조로 DB에서 불러온 데이터를 donationList에 넣으면 실행가능			
		*/		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				RAccept2 frame = new RAccept2();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
