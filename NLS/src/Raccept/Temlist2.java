package Raccept;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Temlist2 extends JPanel {

    private static final long serialVersionUID = 1L;

    public Temlist2(String giverName, String amount, String date, String message) {
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
        acceptBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String msg = "후원자 " + giverName + " 님에게\n금액: " + amount + "원\n받았습니다.";
                JOptionPane.showMessageDialog(null, msg, "후원 내역", JOptionPane.INFORMATION_MESSAGE);
                // 여기에 DB insert 메소드 추가 가능
            }
        });
        add(acceptBtn);
    }
}
