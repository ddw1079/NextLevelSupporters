package Raccept;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import db.ConnectDB;

public class RAccept2 extends JFrame {

    public RAccept2(int receiverId, String receiverName) {
        // 1) 기본 프레임 세팅
        setTitle("받은 후원내역");
        setBounds(100, 100, 706, 477);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel root = new JPanel(null);
        setContentPane(root);

        // 2) 상단 메뉴바 + 제목
        template.NLSMenuTemplate menu = new template.NLSMenuTemplate(receiverName, 1);
        menu.setBounds(0, 0, 690, 42);
        root.add(menu);

        JLabel lblTitle = new JLabel("받은 후원내역");
        lblTitle.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 20));
        lblTitle.setBounds(27, 64, 300, 30);
        root.add(lblTitle);

        // 3) 스크롤 패널
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        JScrollPane sp = new JScrollPane(listPanel);
        sp.setBounds(27, 102, 637, 336);
        sp.getVerticalScrollBar().setUnitIncrement(60);
        root.add(sp);

        // 4) DB에서 카드 생성 후 화면에 추가
        try { 
            ReceiverHistoryDao dao = new ReceiverHistoryDao();
            
            List<ReceiverHistoryVo> vos = dao.read(receiverId);

            List<Temlist2> cards = createCards(vos);
            cards.forEach(card -> {
                card.setBackground(SystemColor.menu);
                card.setMaximumSize(new Dimension(600, 220));
                card.setAlignmentX(Component.CENTER_ALIGNMENT);
                listPanel.add(card);
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "DB 오류: " + e.getMessage(),
                "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** VO 리스트를 Temlist2 카드로 변환 */
    private List<Temlist2> createCards(List<ReceiverHistoryVo> vos) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy.MM.dd");
        List<Temlist2> out = new ArrayList<>();
        for (ReceiverHistoryVo v : vos) {
            out.add(new Temlist2(
                v.getGiverName(),
                String.format("%,d", v.getAmount()),
                fmt.format(v.getCreateDate()),
                v.getMessage()
            ));
        }
        return out;
    }

    /** 테스트용 main */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RAccept2(2, "김성진").setVisible(true);
        });
    }
}
