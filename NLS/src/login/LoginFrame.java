package login;

import Raccept.RAccept2;
import admin.AdminAccess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import giver.GiverMainClass;
import Raccept.RAccept2;
import login.LoginDAO;
import signup.SignUpFrame;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;

    private JLabel messageLabel;

    public LoginFrame() {
        // 프레임 기본 설정
        setTitle("Login Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 메인 패널 설정
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 입력 필드들을 위한 패널
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        // 사용자 이름 레이블과 입력 필드
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);

        // 비밀번호 레이블과 입력 필드
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        // 버튼들을 위한 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        loginButton = new JButton("Login");
        signUpButton = new JButton("SignUp");
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        // 메시지 레이블
        messageLabel = new JLabel("");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        // 컴포넌트들을 메인 패널에 추가
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(messageLabel, BorderLayout.NORTH);

        // 메인 패널을 프레임에 추가
        add(mainPanel);

        // 회원가입 버튼 액션 리스너
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignUpFrame signUpFrame = new SignUpFrame();
                signUpFrame.setVisible(true);
                dispose(); // 현재 로그인 창 닫기
            }
        });

        // 로그인 버튼 액션 리스너
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginDAO dao = new LoginDAO();
                String loginId = usernameField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    int ID = dao.getUserId(loginId);
                    if (dao.validateLogin(ID, password)) {
                        int userType = dao.getUserType(ID);
                        int userId = dao.getUserId(loginId);
                        String is_active = dao.getActive(userId);
                        if (is_active.equals("N")) {
                            messageLabel.setText("Your account is deactived");
                            dispose();
                        }
                        if (userType == 2) {
                            try {
                                AdminAccess adminFrame = new AdminAccess();
                                adminFrame.setVisible(true);
                                dispose();
                            } catch (ClassNotFoundException ex) {
                                messageLabel.setText("Admin frame could not be loaded");
                                messageLabel.setForeground(Color.RED);
                            }
                        } else if (userType == 0) {
                            try {
                                GiverMainClass giverMainFrame = new GiverMainClass(userId);
                                giverMainFrame.setVisible(true);
                                dispose();
                            } catch (ClassNotFoundException ex) {
                                messageLabel.setText("Giver frame could not be loaded");
                                messageLabel.setForeground(Color.RED);
                            }
                        } else {
                            String userName = dao.getUserName(userId);
                            RAccept2 acceptMainFrame = new RAccept2(userId, userName);
                            acceptMainFrame.setVisible(true);
                            dispose();
                        }
                    } else {
                        messageLabel.setText("Invalid username or password!");
                        messageLabel.setForeground(Color.RED);
                    }
                } catch (SQLException ex) {
                    messageLabel.setText("Database error occurred!");
                    messageLabel.setForeground(Color.RED);
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
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
