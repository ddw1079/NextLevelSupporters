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
		
		// 회원가입 버튼
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		signUpButton = new JButton("SignUp");
		add(signUpButton, gbc);
		signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignUpFrame signUpFrame = new SignUpFrame();
                signUpFrame.setVisible(true);
                dispose(); // 현재 로그인 창 닫기
            }
        });
		// 메시지 레이블
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		messageLabel = new JLabel("");
		add(messageLabel, gbc);
		
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
		}

		);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new LoginFrame().setVisible(true);
			}
		});
	}
}
