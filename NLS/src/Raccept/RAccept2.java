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
package Raccept;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import template.NLSMenuTemplate;
import javax.swing.JScrollPane;

import giver.GiverHistoryVO;

import java.awt.SystemColor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Dimension;


public class RAccept2 extends JFrame {
    private JPanel contentPane;

    // 생성자: 수혜자 후원금 내역 수락 페이지를 초기화
    public RAccept2() throws SQLException {
    	
    	int user_id = 1;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 706, 477);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // 페이지 제목 표시
        JLabel lblTitle = new JLabel("받은 후원내역");
        lblTitle.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 20));
        lblTitle.setBounds(27, 64, 311, 30);
        contentPane.add(lblTitle);

        // 상단 메뉴바: NLSMenuTemplate 템플릿 사용
        NLSMenuTemplate menuTemplate = new NLSMenuTemplate("김성진", 1);
        menuTemplate.setBounds(0, 0, 690, 42);
        contentPane.add(menuTemplate);

        // 후원 내역 리스트를 담을 패널 생성
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(SystemColor.controlLtHighlight);

        // 스크롤 패널 생성 및 구성
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBounds(27, 102, 637, 336);
        scrollPane.getVerticalScrollBar().setUnitIncrement(60); // 휠 스크롤 속도 증가
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // contentPane에 추가
        contentPane.add(scrollPane);

        // 후원 내역 리스트 (임시 데이터)
        ReceiverHistoryDao rdao = new ReceiverHistoryDao();
        ArrayList<ReceiverHistoryVo> List = rdao.read(user_id);
     
        ArrayList<Temlist2> donationList = createDonationList(List);
  
        
        
        // 리스트 패널에 후원 내역 항목 추가
        donationList.forEach(item -> {
            item.setBackground(SystemColor.menu); // 배경 색상 설정
            item.setMaximumSize(new Dimension(600, 220)); // 항목 크기 설정
            item.setAlignmentX(CENTER_ALIGNMENT);
            listPanel.add(item);
            
        });
    } 
    	    // 후원 내역 리스트 생성
    	    private ArrayList<Temlist2> createDonationList(ArrayList<ReceiverHistoryVo> volist) {
    	        ArrayList<Temlist2> donationList = new ArrayList<>();
    	        for (ReceiverHistoryVo temp : volist) {
    	            donationList.add(new Temlist2(
    	                temp.getGiverName(),
    	                String.valueOf(temp.getAmount()),
    	                temp.getCreate_date().toString(),
    	                temp.getMessage()
    	            ));
    	        }
    	        return donationList;
    	    }
	

	// 테이블 데이터와 컬럼 이름 설정
    String[] columnNames = {"ID", "수혜자명", "일자", "금액", "메세지"};

    ArrayList<Object[]> dataList = new ArrayList<>();


    public static void main(String[] args) {
    	int user_id = 1;
    	
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
