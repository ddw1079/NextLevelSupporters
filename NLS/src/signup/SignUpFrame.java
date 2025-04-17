/* 
 fdsafdsa
 fdsafdsaf
 accountFieldsaf
 dsafdsafdsa
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpFrame extends JFrame {

    private JTextField loginIDField;
    private JTextField loginPWField;
    private JTextField nameField;
    private JTextField phoneField;
    private JComboBox<String> userTypeBox;
    private JTextField accountField;
    private JTextArea reasonArea;

    private JButton signUpButton;
    private JLabel messageLabel; // 이건 뭐하는거임? 

    public SignUpFrame() {
        // 프레임 기본 설정
        setTitle("SignUp Form");
        setSize(1000, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 배치

        // 레이아웃 설정
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 여백 설정

        // 사용자 아이디디 레이블
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Id:"), gbc);

        // 사용자 아이디 입력 필드
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginIDField = new JTextField(20);
        add(loginIDField, gbc);

        // 비밀번호 레이블
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);

        // 비밀번호 입력 필드
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPWField = new JTextField(20);
        add(loginPWField, gbc);

        // 사용자 이름 레이블
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("이름:"), gbc);

        // 사용자 이름 입력 필드
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        nameField = new JTextField(20);
        add(nameField, gbc);

        // 전화번호 레이블
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("전화번호:"), gbc);

        // 전화번호 입력 필드
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        phoneField = new JTextField(20);
        add(phoneField, gbc);

        // 타입 레이블
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("타입:"), gbc);

        // 타입 선택 필드
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] items = {"후원자", "수혜자"};
        userTypeBox = new JComboBox<>(items);
        add(userTypeBox, gbc);

        // 계좌번호 레이블
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("계좌번호:"), gbc);

        // 계좌번호 입력 필드
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginIDField = new JTextField(20);
        add(loginIDField, gbc);

        // 로그인 버튼
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        signUpButton = new JButton("SignUp");
        add(signUpButton, gbc);

        // 사유 레이블
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel reasonLabel = new JLabel("사유:");
        reasonLabel.setVisible(false);
        add(reasonLabel, gbc);

        // 사유 입력 필드
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        reasonArea = new JTextArea();
        reasonArea.setVisible(false);
        add(reasonArea, gbc);

        // 사유 나타나는 액션 리스너
        userTypeBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) userTypeBox.getSelectedItem();
                if (selectedItem.equals("수혜자")) {
                    reasonLabel.setVisible(true);
                    reasonArea.setVisible(true);
                } else {
                    reasonLabel.setVisible(false);
                    reasonArea.setVisible(false);
                }
            }
        });

        // 로그인 버튼 액션 리스너
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = loginIDField.getText();
                String password = loginPWField.getText();

                if (username.equals("admin") && password.equals("admin")) {
                    messageLabel.setText("Login successful!");
                    messageLabel.setForeground(Color.GREEN);
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
                new SignUpFrame().setVisible(true);
            }
        });
    }
}
