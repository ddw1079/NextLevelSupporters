package login;

import Raccept.RAccept2;
import admin.AdminAccess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;

    public LoginFrame() {
        // 프레임 기본 설정
        setTitle("Login Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 배치

        // 레이아웃 설정
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 여백 설정

        // 사용자 이름 레이블
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);

        // 사용자 이름 입력 필드
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        usernameField = new JTextField(20);
        add(usernameField, gbc);

        // 비밀번호 레이블
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);

        // 비밀번호 입력 필드
        gbc.gridx = 1;
        gbc.gridy = 1;
        passwordField = new JPasswordField(20);
        add(passwordField, gbc);

        // 로그인 버튼
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        loginButton = new JButton("Login");
        add(loginButton, gbc);

        // 메시지 레이블
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        messageLabel = new JLabel("");
        add(messageLabel, gbc);

        // 로그인 버튼 액션 리스너
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                String answer = 다오에서 받아온거;

                if (password.equals(answer)) {
                    if (1 == 2) {
                        AdminAccess adminFrame = new AdminAccess();
                        adminFrame.setVisible(true);
                        this.dispose();
                        // rootPaneCheckingEnabled 이걸 추천해주네네

                    } else if (1 == 0) {
                        GiverMainClass giverMainFrame = new GiverMainClass(인자 전달달
                        );
                        giverMainFrame.setVisible(true);
                        this.dispose();
                    } else {
                        RAccept2 acceptMainFrame = new RAccept2(인자 전달달
                        );
                        acceptMainFrame.setVisible(true);
                        this.dispose();
                    }

                } else {
                    messageLabel.setText("Invalid username or password!");
                    messageLabel.setForeground(Color.RED);
                }

            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}
