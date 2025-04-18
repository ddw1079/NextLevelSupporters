package Raccept;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import db.ConnectDB;

public class Temlist2 extends JPanel {

    private static final long serialVersionUID = 1L;

    // 🔑 DB 업데이트를 위해 추가해야 할 정보들
    private final int giverId;
    private final int receiverId;

    public Temlist2(String giverName, String amount, String date, String message,
                    JPanel listPanel, int giverId, int receiverId) {
        this.giverId = giverId;
        this.receiverId = receiverId;

        setBackground(Color.WHITE);
        setLayout(null);
        setPreferredSize(new Dimension(280, 211));

        JLabel lblTitle = new JLabel(" 후원자 " + giverName + " 님");
        lblTitle.setOpaque(true);
        lblTitle.setBackground(SystemColor.scrollbar);
        lblTitle.setFont(new Font("나눔고딕", Font.BOLD, 15));
        lblTitle.setBounds(0, 0, 280, 20);
        add(lblTitle);

        JLabel lblAmount = new JLabel("금액 : " + amount + "원");
        lblAmount.setBounds(10, 40, 151, 20);
        add(lblAmount);

        JLabel lblDate = new JLabel("일자 : " + date);
        lblDate.setBounds(10, 70, 151, 20);
        add(lblDate);

        JLabel lblMsg = new JLabel("<html>메세지 : " + message.replaceAll("\n", "<br>") + "</html>");
        lblMsg.setBounds(10, 100, 151, 100);
        add(lblMsg);

        JButton rejectBtn = new JButton("거절");
        rejectBtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
        rejectBtn.setBounds(190, 120, 81, 70);
        add(rejectBtn);

        JButton acceptBtn = new JButton("받기");
        acceptBtn.setBackground(new Color(250, 250, 210));
        acceptBtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
        acceptBtn.setBounds(190, 40, 81, 70);

        // ✔️ 수락 다이얼로그 + DB + 카드 제거
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
                        // 1. DB에서 상태 업데이트
                        String sql = "UPDATE HISTORY SET is_received = 'Y' WHERE giver_id = ? AND receiver_id = ? AND is_received = 'N'";
                        try (Connection conn = new ConnectDB().getConnection();
                             PreparedStatement ps = conn.prepareStatement(sql)) {
                            ps.setInt(1, giverId);
                            ps.setInt(2, receiverId);
                            ps.executeUpdate();
                        }

                        // 2. 안내 메시지
                        String msg = "후원자 " + giverName + " 님에게\n금액: " + amount + "원\n받았습니다.";
                        JOptionPane.showMessageDialog(Temlist2.this, msg, "후원 내역", JOptionPane.INFORMATION_MESSAGE);

                        // 3. 카드 제거
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

        add(acceptBtn);
    }
}
