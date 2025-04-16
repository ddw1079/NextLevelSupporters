/*
	 * 파일 설명: 수혜자 후원금 내역/검색 페이지 (JFrame)
	 * 파일 상세: 수혜자가 후원금 받은 정보가 테이블형식으로 보임
	 * 	- 상단 칼럼()
	 *  ->> "ID", "후원자명", "일시", "금액", "메세지"
	 * 화면 메인에는 후원받은 내역목록 스크롤사용하여 아래로 쭉 보이게 할 예정
	 * Input: 1.로그인 한 수혜자 정보
	 * 		  2.각 후원자 정보후원내역:(후원자명, 일자, 금액, 메세지)가 들어갈예정
	 * Feature: 
	 *   - 검색 가능
	 */

package RECEIVER3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import template.NLSMenuTemplate;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.SystemColor;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/*
import java.util.ArrayList;
import java.awt.Dimension;
import javax.swing.Box;
*/

public class RAccept3list extends JFrame {//RAccept2 클래스는 후원 내역창

	private JPanel contentPane;

	public RAccept3list() {// 생성자
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 477);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitle = new JLabel("수혜내역목록");
		lblTitle.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 20));
		lblTitle.setBounds(27, 64, 311, 30);
		contentPane.add(lblTitle);
		
		// *후원 항목 하나를 표시* (Temlist3 클래스 템플릿 사용)
		Temlist3 list = new Temlist3();
		list.setBackground(SystemColor.menu);
		list.setBounds(27, 104, 619, 334); // 위치/크기 조정
		contentPane.add(list);
		
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
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(512, 64, 150, 30);
		contentPane.add(toolBar);
		
		JLabel lblNewLabel = new JLabel("\uC870\uD68C/\uAC80\uC0C9");
		toolBar.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {   

	                // 팝업에서 후원자명 입력 받기
	                String sponsorName = JOptionPane.showInputDialog(
	                        null,  // 부모 컴포넌트
	                        "후원자명을 입력해주세요:",  // 메시지
	                        "후원자명 입력",  // 제목
	                        JOptionPane.PLAIN_MESSAGE);  // 메시지 박스 유형

	                // 사용자가 입력한 후원자명 처리 (입력값 확인)
	                if (sponsorName != null && !sponsorName.trim().isEmpty()) {
	                    // 입력값이 있으면 처리
	                    JOptionPane.showMessageDialog(null, "입력된 후원자명: " + sponsorName, "입력 완료", JOptionPane.INFORMATION_MESSAGE);
	                    // 예: 후원자명으로 검색 등의 처리 가능
	                } else {
	                    // 입력값이 없으면 알림
	                    JOptionPane.showMessageDialog(null, "후원자명을 입력하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);

	            }
		        		        		    
			}
		});
		btnNewButton.setIcon(new ImageIcon("D:\\@@@rlatjdwls\\day01\\0409_22\uC77C\uCC28\\CHAT2.png"));
		toolBar.add(btnNewButton);
	
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
				RAccept3list frame = new RAccept3list();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
