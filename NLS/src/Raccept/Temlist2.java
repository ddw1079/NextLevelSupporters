package Raccept;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

public class Temlist2 extends JPanel {

	private final int receiverId;
	private final int giverId;
	private final Date createDate;

	public Temlist2(String giverName, String amount, String dateStr, String message, JPanel listPanel, int receiverId,
			int giverId, Date createDate) {

		this.receiverId = receiverId;
		this.giverId = giverId;
		this.createDate = createDate;

		// 카드 스타일 설정
		setLayout(new BorderLayout());
		setBackground(SystemColor.controlLtHighlight);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(15, 20, 15, 20)));

		// 텍스트 정보 정렬을 위한 패널
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false); // 배경색은 상위 패널 사용

		contentPanel.add(Box.createVerticalStrut(6)); // 간격

		// 전체 너비 확장을 위한 패널
		JPanel titleWrapPanel = new JPanel();
		titleWrapPanel.setLayout(new BorderLayout()); // 가로 전체 채우기
		titleWrapPanel.setOpaque(false);
		titleWrapPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // 전체 폭 강제

		// 후원자 라벨
		JLabel lblTitle = new JLabel(" 후원자 " + giverName + " 님", SwingConstants.CENTER);
		lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 18));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(255, 230, 180)); // 배경색
		lblTitle.setPreferredSize(new Dimension(0, 40)); // 높이 지정 (선택)
		lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		// 라벨을 가운데 배치
		titleWrapPanel.add(lblTitle, BorderLayout.CENTER);
		contentPanel.add(titleWrapPanel);

		contentPanel.add(Box.createVerticalStrut(10)); // 간격

		// 금액 라벨
		JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		amountPanel.setOpaque(false);
		JLabel lblAmount = new JLabel("   금액 : " + amount + "원");
		lblAmount.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		amountPanel.add(lblAmount);
		contentPanel.add(amountPanel);
		contentPanel.add(Box.createVerticalStrut(8));

		// 일자 라벨
		JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		datePanel.setOpaque(false);
		JLabel lblDate = new JLabel("   일자 : " + dateStr);
		lblDate.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		datePanel.add(lblDate);
		contentPanel.add(datePanel);
		contentPanel.add(Box.createVerticalStrut(8));

		// 메세지 라벨
		JPanel msgPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		msgPanel.setOpaque(false);
		JLabel lblMsg = new JLabel("   메세지 : " + message);
		lblMsg.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		msgPanel.add(lblMsg);
		contentPanel.add(msgPanel);
		contentPanel.add(Box.createVerticalStrut(8));

		// contentPanel을 카드 패널에 붙이기
		add(contentPanel, BorderLayout.CENTER);

		// 버튼 패널 (하단 중앙)
		JPanel btnPanel = new JPanel();
		btnPanel.setOpaque(false);
		JButton acceptBtn = new JButton("받기");
		acceptBtn.setBackground(SystemColor.controlHighlight);
		acceptBtn.setPreferredSize(new Dimension(120, 35)); // 버튼 사이즈 키움
		acceptBtn.setFont(new Font("나눔고딕", Font.BOLD, 18));
		btnPanel.add(acceptBtn);
		add(btnPanel, BorderLayout.SOUTH);

		// 클릭 이벤트 처리
		acceptBtn.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String confirmMsg = giverName + "님께 후원을 받으시겠습니까?"; // 수정된 부분
		        int result = JOptionPane.showConfirmDialog(
		            Temlist2.this, 
		            confirmMsg, 
		            "받기 확인", 
		            JOptionPane.YES_NO_OPTION
		        );

				if (result == JOptionPane.YES_OPTION) {
					try {
						ReceiverHistoryDao dao = new ReceiverHistoryDao();
						dao.updateToReceivedOne(receiverId, giverId, createDate);

						JOptionPane.showMessageDialog(Temlist2.this, "후원금 수령 완료!", "알림",
								JOptionPane.INFORMATION_MESSAGE);

						listPanel.remove(Temlist2.this);
						listPanel.revalidate();
						listPanel.repaint();

					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(Temlist2.this, "DB 오류: " + ex.getMessage(), "에러",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}
