package Raccept;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RAccept2 extends JFrame {

    public RAccept2(int receiverId, String receiverName) throws ClassNotFoundException, SQLException {
        setTitle("받은 후원내역");
        setBounds(100, 100, 706, 477);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel root = new JPanel(null);
        setContentPane(root);

        // 메뉴바 + 제목
        template.NLSMenuTemplate menu = new template.NLSMenuTemplate(receiverId);
        menu.setBounds(0, 0, 690, 42);
        root.add(menu);

        JLabel lblTitle = new JLabel("받은 후원내역");
        lblTitle.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 20));
        lblTitle.setBounds(27, 64, 300, 30);
        root.add(lblTitle);

        // 스크롤 영역
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        JScrollPane sp = new JScrollPane(listPanel);
        sp.setBounds(27, 102, 637, 336);
        sp.getVerticalScrollBar().setUnitIncrement(60);
        root.add(sp);

        // DB에서 카드 불러오기
        try {
            ReceiverHistoryDao dao = new ReceiverHistoryDao();
            List<ReceiverHistoryVo> vos = dao.readByStatus(receiverId, "Y");


            SimpleDateFormat fmt = new SimpleDateFormat("yyyy.MM.dd");
            for (ReceiverHistoryVo v : vos) {
                Temlist2 card = new Temlist2(
                			    v.getGiverName(),
                			    String.format("%,d", v.getAmount()),
                			    fmt.format(v.getCreateDate()),
                			    v.getMessage(),
                			    listPanel,
                			    v.getGiverId(),   // ➕ 추가
                			    receiverId        // ➕ 추가
                			);

                card.setBackground(SystemColor.menu);
                card.setMaximumSize(new Dimension(600, 220));
                card.setAlignmentX(Component.CENTER_ALIGNMENT);
                listPanel.add(card);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "DB 오류: " + e.getMessage(),
                "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** 테스트용 main */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new RAccept2(2, "김성진").setVisible(true);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
