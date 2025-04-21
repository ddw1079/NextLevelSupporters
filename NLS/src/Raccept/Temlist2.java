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

    public Temlist2(String giverName, String amount, String dateStr, String message,
                    JPanel listPanel, int receiverId, int giverId, Date createDate) {

        this.receiverId = receiverId;
        this.giverId = giverId;
        this.createDate = createDate;

        // 카드 스타일 설정
        setLayout(new BorderLayout());
        setBackground(SystemColor.controlLtHighlight);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        // 텍스트 정보 정렬을 위한 패널
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false); // 배경색은 상위 패널 사용

        // 모든 라벨 왼쪽 정렬 고정!
        JLabel lblTitle = new JLabel("후원자 " + giverName + " 님");
        lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 20));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(lblTitle);  

        contentPanel.add(Box.createVerticalStrut(10)); // 간격

        JLabel lblAmount = new JLabel("- 금액 : " + amount + "원");
        lblAmount.setFont(new Font("나눔고딕", Font.PLAIN, 16));
        lblAmount.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(lblAmount);  
        contentPanel.add(Box.createVerticalStrut(8));

        JLabel lblDate = new JLabel("- 일자 : " + dateStr);
        lblDate.setFont(new Font("나눔고딕", Font.PLAIN, 16));
        lblDate.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(lblDate);  
        contentPanel.add(Box.createVerticalStrut(8));

        JLabel lblMsg = new JLabel("- 메세지 : " + message);
        lblMsg.setFont(new Font("나눔고딕", Font.PLAIN, 16));
        lblMsg.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(lblMsg);  
        contentPanel.add(Box.createVerticalStrut(8));

        // contentPanel을 카드 패널에 붙이기
        add(contentPanel, BorderLayout.CENTER);  


        // 버튼 패널 (하단 중앙)
        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        JButton acceptBtn = new JButton("받기");
        acceptBtn.setBackground(SystemColor.controlHighlight);
        acceptBtn.setPreferredSize(new Dimension(120, 40)); // 버튼 사이즈 키움
        acceptBtn.setFont(new Font("나눔고딕", Font.BOLD, 18));
        btnPanel.add(acceptBtn);
        add(btnPanel, BorderLayout.SOUTH);

        // 클릭 이벤트 처리
        acceptBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        Temlist2.this,
                        "정말 이 후원을 받으시겠습니까?",
                        "받기 확인",
                        JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.YES_OPTION) {
                    try {
                        ReceiverHistoryDao dao = new ReceiverHistoryDao();
                        dao.updateToReceivedOne(receiverId, giverId, createDate);

                        JOptionPane.showMessageDialog(
                                Temlist2.this,
                                "후원 수령 완료!",
                                "알림",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        listPanel.remove(Temlist2.this);
                        listPanel.revalidate();
                        listPanel.repaint();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Temlist2.this,
                                "DB 오류: " + ex.getMessage(),
                                "에러", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
