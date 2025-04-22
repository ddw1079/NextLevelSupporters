package Raccept;

import template.NLSMenuTemplate;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class RAccept2 extends JFrame {

    public RAccept2(int receiverId, String receiverName) throws ClassNotFoundException, SQLException {
        setTitle("후원금확인페이지");
        setSize(706, 477);
        setLocationRelativeTo(null); // 화면 중앙 정렬
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        setContentPane(root);

        // 상단 메뉴 + 제목 패널
        JPanel topPanel = new JPanel(null);
        topPanel.setPreferredSize(new Dimension(690, 100));
        topPanel.setBackground(Color.WHITE);

        NLSMenuTemplate menu = new NLSMenuTemplate(receiverId);
        menu.setBounds(0, 0, 690, 42);
        topPanel.add(menu);

        //후원내역상단레벨
        JLabel lblTitle = new JLabel(
        	    "<html>" + receiverName + "님,<br>누군가의 따뜻한 마음이 도착했습니다</html>"
        	);
        	lblTitle.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 18));

        lblTitle.setBounds(10, 50, 600, 42); // 너비 넉넉히 설정
        topPanel.add(lblTitle);

        root.add(topPanel, BorderLayout.NORTH);

        // 카드 리스트 패널 (JPanel로 변경 )
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(255, 255, 240));

        JScrollPane sp = new JScrollPane(listPanel);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        root.add(sp, BorderLayout.CENTER);
        // 스크롤 속도 빠르게 설정 (기본보다 높게)
        sp.getVerticalScrollBar().setUnitIncrement(16); 

        // DB에서 카드 불러오기
        try {
            ReceiverHistoryDao dao = new ReceiverHistoryDao();
            List<ReceiverHistoryVo> vos = dao.readByStatus(receiverId, "N");

            System.out.println("받기 전 후원 수: " + vos.size()); // 디버깅 로그

            SimpleDateFormat fmt = new SimpleDateFormat("yyyy.MM.dd");

            for (ReceiverHistoryVo v : vos) {
                Temlist2 card = new Temlist2(
                        v.getGiverName(),
                        String.format("%,d", v.getAmount()),
                        fmt.format(v.getCreateDate()),
                        v.getMessage(),
                        listPanel,                 // JPanel로 통일
                        receiverId,
                        v.getGiverId(),
                        v.getCreateDate()
                );

                card.setPreferredSize(new Dimension(600, 180));
                card.setMaximumSize(new Dimension(600, 180));
                card.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                listPanel.add(Box.createVerticalStrut(10)); // 카드 간 간격
                listPanel.add(card);
            }

            listPanel.revalidate();
            listPanel.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "DB 오류: " + e.getMessage(),
                    "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

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
