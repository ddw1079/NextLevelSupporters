package giver;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import template.NLSMenuTemplate;

public class GiverHistoryMainClass extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiverHistoryMainClass frame = new GiverHistoryMainClass();
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
	public GiverHistoryMainClass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 978, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("여기는 후원자 테이블이 들어갈 자리야");
		lblNewLabel.setBounds(326, 220, 407, 104);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("당신의 후원 내역");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(242, 61, 466, 41);
		contentPane.add(lblNewLabel_1);
		
		NLSMenuTemplate menuTemplate = new NLSMenuTemplate("김희상", 0);
		menuTemplate.setBounds(12, 10, 938, 41);
		contentPane.add(menuTemplate);
	}
}
