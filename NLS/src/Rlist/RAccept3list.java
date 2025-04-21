package Rlist;

import Raccept.ReceiverHistoryDao;
import Raccept.ReceiverHistoryVo;
import template.NLSMenuTemplate;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class RAccept3list extends JFrame {

    private JPanel contentPane;

    public RAccept3list(int receiverId, String receiverName) throws ClassNotFoundException, SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 706, 477);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("수혜내역 확인");
        lblTitle.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 20));
        lblTitle.setBounds(27, 64, 311, 30);
        contentPane.add(lblTitle);

        // 상단 메뉴바
        NLSMenuTemplate menuTemplate = new NLSMenuTemplate(receiverId);
        menuTemplate.setBounds(0, 0, 690, 42);
        contentPane.add(menuTemplate);

        try {
            ReceiverHistoryDao dao = new ReceiverHistoryDao();
            List<ReceiverHistoryVo> vos = dao.readByStatus(receiverId, "Y"); // 받은 내역만 조회

            Temlist3 tablePanel = new Temlist3(vos);
            tablePanel.setBounds(27, 102, 637, 336);
            contentPane.add(tablePanel);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "DB 오류: " + e.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new RAccept3list(2, "김성진").setVisible(true);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
