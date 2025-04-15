package template;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NLSMenuTemplate extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JPanel panel;
	private static JPanel panel_1;
	private static JLabel lblShowUser;
	private static JButton btnCheckHistory;
	private static JButton btnLogin;
	private static JButton btnLogout;
	private static JButton btnRegister;

	/**
	 * 파일 설명: 메인 메뉴 템플릿
	 * 파일 상세: 최상단 메인 메뉴를 구현. 
	 * 	- 로그인 한 사람 확인. 로그인 타입 확인 라빌 구현
	 * 	- 회원가입, 로그인 및 로그아웃 버튼 구현
	 * Input: 현재 로그인한 사람의 정보(이름, 유저타입)
	 * Feature: 
	 *  - 유저타입에 따라 다른 정보를 출력
	 *  - 유저타입 구분: -1 로그아웃됨 0 후원자 1 수혜자 2 Admin
	 *  	- -1 로그아웃됨: "로그인해주세요" 라벨, 로그인, 회원가입 버튼 출력됨
	 *  	- 0 후원자: "반갑습니다 ㅁㅁㅁ 후원자님!" 라벨, 후원내역, 회원정보 수정, 로그아웃 버튼 출력
	 *  	- 1 수혜자: "반갑습니다 ㅁㅁㅁ 수혜자님!" 라벨, 수혜내역, 회원정보 수정, 로그아웃 버튼 출력
	 *  	- 2 Admin: "반갑습니다 ㅁㅁㅁ 관리자님!" 라벨, 회원정보 수정?, 로그아웃 버튼 출력
	 *  Output: 로그인, 회원가입, 후원내역, 수혜내역 페이지
	 */
	public NLSMenuTemplate() {
		this("111", 1);
	}
	public NLSMenuTemplate(String username, int usertype) {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		switch (usertype) {
			case -1:
				lblShowUser = new JLabel("안녕하세요! 로그인해주세요");
				btnLogin = new JButton("로그인");
				panel_1.add(btnLogin);
				btnRegister = new JButton("회원가입");
				panel_1.add(btnRegister);
				break;
			case 0:
				lblShowUser = new JLabel("반갑습니다. " + username + " 후원자님!");
				btnCheckHistory = new JButton("후원내역 확인");
				panel_1.add(btnCheckHistory);
				btnLogout = new JButton("로그아웃");
				panel_1.add(btnLogout);
				break;
			case 1:
				lblShowUser = new JLabel("반갑습니다. " + username + " 수혜자님!");
				btnCheckHistory = new JButton("수혜내역 확인");
				panel_1.add(btnCheckHistory);
				btnLogout = new JButton("로그아웃");
				panel_1.add(btnLogout);
				break;
			case 2:
				lblShowUser = new JLabel("반갑습니다. " + username + " 관리자님!");
				btnLogout = new JButton("로그아웃");
				panel_1.add(btnLogout);
				break;
			default:
				System.out.println("Menubar default executed. usertype? " + usertype);
				break;
		}
		panel.add(lblShowUser);
	}
}
