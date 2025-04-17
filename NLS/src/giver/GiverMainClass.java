package giver;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import template.NLSMenuTemplate;
import template.ReceiverTemplate;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

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
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 61, 651, 25);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("수혜자 목록");
		panel.add(lblNewLabel);
		
//		DefaultListModel<ReceiverTemplate> listModel = new DefaultListModel<>();
//		listModel.addElement(new ReceiverTemplate());
//		listModel.addElement(new ReceiverTemplate());
//
//		
//		
//		JList<ReceiverTemplate> list = new JList<>(listModel);
//		list.setFixedCellHeight(66);
//		list.setLayoutOrientation(JList.VERTICAL);
//		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		list.setCellRenderer(new ListCellRenderer<ReceiverTemplate>() {
//		    @Override
//		    public Component getListCellRendererComponent(JList<? extends ReceiverTemplate> list, ReceiverTemplate value, int index, boolean isSelected, boolean cellHasFocus) {
//		        return value; // JPanel 반환
//		    }
//		});
        
		JPanel panel_1 = new JPanel();
		
        // 스크롤 영역 생성 및 패널 연결
        JScrollPane scrollPane = new JScrollPane(panel_1);
		scrollPane.setBounds(12, 96, 653, 345);
        contentPane.add(scrollPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(panel_1);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20); // 숫자를 늘리면 더 빠르게 스크롤
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		panel_1.add( new ReceiverTemplate());
		panel_1.add( new ReceiverTemplate());
		panel_1.add( new ReceiverTemplate());
		panel_1.add( new ReceiverTemplate());
		panel_1.add( new ReceiverTemplate());
		panel_1.add( new ReceiverTemplate());
		panel_1.add( new ReceiverTemplate());
		panel_1.add( new ReceiverTemplate());
		panel_1.add( new ReceiverTemplate());
		
		panel_1.setPreferredSize(new Dimension(653, panel_1.getComponentCount() * 66));
		panel_1.revalidate();
		panel_1.repaint();

	}
}