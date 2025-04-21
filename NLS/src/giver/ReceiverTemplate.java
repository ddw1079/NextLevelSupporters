package giver;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ReceiverTemplate extends JPanel {

	private static final long serialVersionUID = 1L;
	protected static final String ReceiverSponseringMainClass = null;
	private static JButton btnReceiverName;
	private static JPanel panel;
	private static JLabel lblReceiverReason;
	/**
	 * 파일 설명: 수혜자 단일 템플릿
	 * 파일 상세: 후원자가 수혜자 목록을 조회할 때 사용할 단일 템플릿
	 *  - 후원자가 수혜자 목록을 조회할 때, 이 클래스를 리스트로 선언하여 뿌릴 것.
	 *  - 각 수혜자의 단일 정보만 주어 정보를 표현하게 함.
	 * Input: 수혜자 이름, 수혜자 후원 이유
	 * Feature:
	 *  - 수혜자 이름과 수혜자 후원 이유를 출력
	 *  - 수혜자 이름의 버튼을 클릭하면 해당 수혜자 후원 페이지로 이동
	 * Output: (클릭한 수혜자의 )수혜자 후원 페이지
	 */
	public ReceiverTemplate() {
		this(0, 1, 2, "굶주리는 준석이", "배고픔에 잠 못 이루는 밤… 작은 도움 하나가 제게 희망이 됩니다.", "010-1111-2222");
	}
	
	public ReceiverTemplate(int index, int giverID, int receiverID, String receiverName, String receiverReason, String receiverPhone) {
		btnReceiverName = new JButton(receiverName);
		btnReceiverName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReceiverSponseringMainClass rsmc = new ReceiverSponseringMainClass(giverID, receiverID, receiverName, receiverReason, receiverPhone);
				rsmc.setVisible(true);
			}
		});
		btnReceiverName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		setLayout(null);
		btnReceiverName.setBounds(41,0,185,66);
		add(btnReceiverName);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setBounds(225, 0, 663, 66);
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblReceiverReason = new JLabel(receiverReason);
		panel.add(lblReceiverReason);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 43, 65);
		add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblIndexLabel = new JLabel(Integer.toString(index));
		lblIndexLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblIndexLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblIndexLabel);

	}
}
