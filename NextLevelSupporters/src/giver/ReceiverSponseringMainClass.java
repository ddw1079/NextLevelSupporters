package giver;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReceiverSponseringMainClass extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldAmount;
	private JTextField textFieldMsg;

	/**
	 * 파일 설명: 후원자 후원 금액 송부 페이지
	 * 파일 상세: 후원자는 수혜자에게 금액을 송부한다.
	 *  - 후원자는 수혜자를 선택하였고 수혜자에게 후원 금액과 메시지를 송부하려고 합니다.
	 *  - 후원자인 당신은 수혜자 상세 정보를 확인하고, 금액과 메시지를 입력하여 송부합니다.
	 * Input: 후원자 정보(후원자 ID), 수혜자 정보(수혜자 ID, 이름, 전화번호, 수혜 이유)
	 * Feature:
	 * 	- 후원자에게 수혜자 상세 정보를 보여줍니다.
	 *  - 후원자는 후원할 금액과 메시지를 입력할 수 있습니다.
	 *  - 후원자가 금액과 메시지를 입력하면 해당 정보가 후원 내역 테이블에 저장됩니다.
	 * Output: 후원 내역 정보 저장(후원자, 수혜자, 금액, 메시지)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceiverSponseringMainClass frame = new ReceiverSponseringMainClass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	// 테스트용 메인 생성자
	public ReceiverSponseringMainClass() {
		this(1, 2, "준석이", "010-1111-2222", "꽃등심이 먹고싶어요...");
	}
	
	public ReceiverSponseringMainClass(int GiverID, int ReceiverID, String ReceiverName, String ReceiverPhone, String ReceiverReason) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNameHeader = new JLabel("수혜자 이름:");
		lblNameHeader.setBounds(12, 10, 115, 35);
		contentPane.add(lblNameHeader);
		
		JLabel lblReceiverName = new JLabel(ReceiverName);
		lblReceiverName.setBounds(139, 10, 313, 35);
		contentPane.add(lblReceiverName);
		
		JLabel lblPhoneHeader = new JLabel("수혜자 전화번호");
		lblPhoneHeader.setBounds(12, 59, 115, 35);
		contentPane.add(lblPhoneHeader);
		
		JLabel lblReceiverPhone = new JLabel(ReceiverPhone);
		lblReceiverPhone.setBounds(139, 59, 313, 35);
		contentPane.add(lblReceiverPhone);
		
		JLabel lblReasonHeader = new JLabel("수혜 이유:");
		lblReasonHeader.setBounds(12, 104, 115, 35);
		contentPane.add(lblReasonHeader);
		
		JLabel lblReceiverReason = new JLabel(ReceiverReason);
		lblReceiverReason.setBounds(139, 104, 313, 35);
		contentPane.add(lblReceiverReason);
		
		JLabel lblText = new JLabel("따뜻한 메시지와 응원 금액을 보내요.");
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setBounds(12, 149, 440, 45);
		contentPane.add(lblText);
		
		JLabel lblAmountHeader = new JLabel("응원 금액");
		lblAmountHeader.setBounds(12, 204, 115, 35);
		contentPane.add(lblAmountHeader);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(139, 204, 313, 35);
		contentPane.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
		JLabel lblMsgHeader = new JLabel("응원 메시지");
		lblMsgHeader.setBounds(12, 249, 115, 35);
		contentPane.add(lblMsgHeader);
		
		textFieldMsg = new JTextField();
		textFieldMsg.setColumns(10);
		textFieldMsg.setBounds(139, 249, 313, 35);
		contentPane.add(textFieldMsg);
		
		JButton btnSend = new JButton("보내기!!!");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sendMsg = "후원금과 메시지를 " + ReceiverName + " 에게 송부합니다.";
				int amount = Integer.parseInt(textFieldAmount.getText());
				sendMsg += "\n후원 금액: " + amount;
				String msg = textFieldMsg.getText();
				sendMsg += "\n응원 메시지: " + msg;
				sendMsg += "\n\n이렇게 보낼까요?";
				int result = JOptionPane.showConfirmDialog(btnSend, sendMsg, "응원 메시지를 보내요", JOptionPane.YES_NO_OPTION);
				
			}
		});
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSend.setBounds(12, 294, 201, 99);
		contentPane.add(btnSend);
		
		JButton btnCancel = new JButton("취소...");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnCancel.setBounds(251, 294, 201, 99);
		contentPane.add(btnCancel);
	}
}
