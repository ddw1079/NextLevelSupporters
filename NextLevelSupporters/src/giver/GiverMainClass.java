package giver;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import template.NLSMenuTemplate;
import template.ReceiverTemplate;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;

public class GiverMainClass extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * 파일 설명: 후원자 메인 메뉴
	 * 파일 상세: 후원자로 로그인하였을때 나타나는 정보들.
	 * 	- 당신은 후원자로 로그인했습니다.
	 *  - 후원자인 당신은 수혜자 목록을 보고, 기부할 사람을 지정합니다.
	 * Input: 현재 로그인 한 인원의 정보(이름, 유저타입)
	 * Feature:
	 *  - 로그인한 유저를 NLSMenuTemplate 로 내려보냅니다.
	 *  - 수혜자 목록을 보여줍니다.
	 *  - 수혜자 목록을 클릭하면 수혜자 상세정보 및 후원하기 페이지로 이동합니다.
	 * Output: 수혜자 상세정보 페이지
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiverMainClass frame = new GiverMainClass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GiverMainClass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NLSMenuTemplate menuTemplate = new NLSMenuTemplate("김희상", 0);
		menuTemplate.setBounds(12, 10, 653, 41);
		contentPane.add(menuTemplate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 96, 653, 345);
		
		
		ArrayList <ReceiverTemplate> RTAL = new ArrayList<>();
		RTAL.add(new ReceiverTemplate());
		RTAL.add(new ReceiverTemplate());
		
		DefaultListModel<ReceiverTemplate> listModel = new DefaultListModel<>();
        for (ReceiverTemplate item : RTAL) {
            listModel.addElement(item);
        }
        
		JList<ReceiverTemplate> list = new JList<>(listModel);
		scrollPane.setViewportView(list);
		contentPane.add(scrollPane);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 61, 651, 25);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("수혜자 목록");
		panel.add(lblNewLabel);
	}
}
