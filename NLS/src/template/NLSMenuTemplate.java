package template;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import interfaces.Readable;
import giver.GiverMainClass;
import giver.GiverHistoryMainClass;
import Rlist.RAccept3list;
import Raccept.RAccept2;



public class NLSMenuTemplate extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JPanel panel;
	private static JPanel panel_1;
	private static JLabel lblShowUser;
	private static JButton btnSponsering;
	private static JButton btnCheckHistory;
	private static JButton btnLogin;
	private static JButton btnLogout;
	private static JButton btnRegister;
	
	private static GiverMainClass gmc;
	private static GiverHistoryMainClass ghmc;


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
	 * @throws SQLException 
	 */
	public NLSMenuTemplate() throws SQLException {
		this(-1);
	}
	public NLSMenuTemplate(int user_id) throws SQLException {
		// GRID LAYOUT 선언
		setLayout(new GridLayout(1, 0, 0, 0));
		
		// 로그인한 회원 정보 들어갈 라벨 생성
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		// 버튼 들어갈 라벨 선언
		panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		// 유저 id로 유저 타입과 유저 이름 이어주기
		NLSMenuTemplateVO user_data = new NLSMenuTemplateDAO().getUserLoginData(user_id);
		
		int usertype = user_data.getUser_type();
		String username = user_data.getName();
		
		
		// 생성자로 가져온 유저 타입에 따라 라벨과 버튼을 다르게 생성한다.
		switch (usertype) {
			case -1:
				// 타입 -1: 로그인 정보 없음
				lblShowUser = new JLabel("안녕하세요! 로그인해주세요");
				btnLogin = new JButton("로그인");
				panel_1.add(btnLogin);
				btnRegister = new JButton("회원가입");
				panel_1.add(btnRegister);
				break;
			case 0:
				// 타입 0: 후원자 로그인
				lblShowUser = new JLabel("반갑습니다. " + username + " 후원자님!");
				btnSponsering = new JButton("후원하기");
				btnSponsering.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// 아마 후원자 아이디와 타입을 넘겨주어야 할 것 같음
						// 여기서 맨 처음 창이 사라지지 않는 이슈 있음. 원인이 뭘까??
						if(gmc == null) {
							try {
								gmc = new GiverMainClass(user_id);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if(ghmc != null) {
							ghmc.setVisible(false);
						}
						gmc.setVisible(true);
					}
				});
				
				panel_1.add(btnSponsering);
				btnCheckHistory = new JButton("후원내역 확인");
				btnCheckHistory.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// 아마 후원자 아이디와 타입을 넘겨주어야 할 것 같음
						if(ghmc == null) {
							try {
								ghmc = new GiverHistoryMainClass(user_id);
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if(gmc != null) {
							gmc.setVisible(false);
						}
						ghmc.setVisible(true);
					}
				});
				panel_1.add(btnCheckHistory);
				btnLogout = new JButton("로그아웃");
				panel_1.add(btnLogout);
				break;
				
				
			case 1:// 타입 1: 수혜자 로그인
				
			    lblShowUser = new JLabel("반갑습니다. " + username + " 수혜자님!");

			    btnSponsering = new JButton("받은 후원");
			    btnSponsering.addMouseListener(new MouseAdapter() {
			        @Override
			        public void mouseClicked(MouseEvent e) {
			            // 현재 창 닫기
			            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btnSponsering);
			            currentFrame.dispose(); // 창 완전 종료

			            // 혜자 후원 수락 페이지(RAccept2) 띄우기
			            RAccept2 acceptPage = new RAccept2(user_id);
			            acceptPage.setVisible(true);
			        }
			    });
			    panel_1.add(btnSponsering);

			    btnCheckHistory = new JButton("수혜 내역 확인");
			    btnCheckHistory.addMouseListener(new MouseAdapter() {
			        @Override
			        public void mouseClicked(MouseEvent e) {
			            // 현재 창 닫기
			            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btnCheckHistory);
			            currentFrame.dispose();

			            // 수혜자 수혜 내역 창 띄우기
			            RAccept3list historyPage = new RAccept3list(user_id);
			            historyPage.setVisible(true);
			        }
			    });
			    panel_1.add(btnCheckHistory);

			    btnLogout = new JButton("로그아웃");
			    panel_1.add(btnLogout);
			    break;

				
			case 2:
				// 타입 2: Admin 관리자 로그인
				lblShowUser = new JLabel("반갑습니다. " + username + " 관리자님!");
				btnLogout = new JButton("로그아웃");
				panel_1.add(btnLogout);
				break;
			default:
				// 여기는 들어오면 안됨. 원활한 진행을 위해 오류는 내지 않고 콘솔에 왜 이런 값이 들어왔는지 유저타입 찍어줌.
				System.out.println("Menubar default executed. usertype? " + usertype);
				break;
		}
		panel.add(lblShowUser);
	}
}

class NLSMenuTemplateVO {
	private int id;
	private String name;
	private int user_type;
	NLSMenuTemplateVO() {}
	NLSMenuTemplateVO(int id, String name, int user_type) {
		super();
		this.setId(id);
		this.setName(name);
		this.setUser_type(user_type);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
}

class NLSMenuTemplateDAO {
	private Connection con;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public NLSMenuTemplateVO getUserLoginData(int user_id) throws SQLException {
		
		String sql = """
				SELECT 	ID, NAME, USER_TYPE
				FROM	USER_TABLE
				WHERE	ID = ?
				""";
		ps = con.prepareStatement(sql);
		ps.setInt(1, user_id);
		rs = ps.executeQuery();
		NLSMenuTemplateVO vo = new NLSMenuTemplateVO(rs.getInt("ID"), rs.getString("NAME"), rs.getInt("USER_TYPE"));
		return vo;
		
	}
	
}