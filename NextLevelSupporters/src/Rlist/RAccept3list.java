/*
	 * ���� ����: ������ �Ŀ��� ����/�˻� ������ (JFrame)
	 * ���� ��: �����ڰ� �Ŀ��� ���� ������ ���̺��������� ����
	 * 	- ��� Į��()
	 *  ->> "ID", "�Ŀ��ڸ�", "�Ͻ�", "�ݾ�", "�޼���"
	 * ȭ�� ���ο��� �Ŀ����� ������� ��ũ�ѻ���Ͽ� �Ʒ��� �� ���̰� �� ����
	 * Input: 1.�α��� �� ������ ����
	 * 		  2.�� �Ŀ��� �����Ŀ�����:(�Ŀ��ڸ�, ����, �ݾ�, �޼���)�� ������
	 * Feature: 
	 *   - �˻� ����
	 */

package Rlist;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import template.NLSMenuTemplate;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.SystemColor;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/*
import java.util.ArrayList;
import java.awt.Dimension;
import javax.swing.Box;
*/

public class RAccept3list extends JFrame {//RAccept2 Ŭ������ �Ŀ� ����â

	private JPanel contentPane;

	public RAccept3list() {// ������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 477);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitle = new JLabel("\uC218\uD61C\uB0B4\uC5ED \uD655\uC778");
		lblTitle.setFont(new Font("������� ExtraBold", Font.BOLD, 20));
		lblTitle.setBounds(27, 64, 311, 30);
		contentPane.add(lblTitle);
		
		// *�Ŀ� �׸� �ϳ��� ǥ��* (Temlist3 Ŭ���� ���ø� ���)
		Temlist3 list = new Temlist3();
		list.setBackground(SystemColor.menu);
		list.setBounds(27, 104, 619, 334); // ��ġ/ũ�� ����
		contentPane.add(list);
		
		// *��� �޴���* (NLSMenuTemplate ���ø� ���)
		NLSMenuTemplate menuTemplate = new NLSMenuTemplate("�輺��" , 1);
		menuTemplate.setBounds(0, 0, 690, 42);
		contentPane.add(menuTemplate);
		
		//JScrollPane �ȿ� �� listPanel�� ���� �� ���� �ڽ� ���̾ƿ� ����.
		// �Ŀ� �׸���� ���� ����Ʈ �г� ����
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));//�������� ��Ʈ�ѵ�
		listPanel.setBackground(SystemColor.controlLtHighlight);

		
		// ��ũ�� ���� ���� �� �г� ����
		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setBounds(27, 102, 637, 336);
		contentPane.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(543, 64, 119, 30);
		contentPane.add(toolBar);
		
		JLabel lblNewLabel = new JLabel(" ���� ��ȸ ");
		toolBar.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {   

	                // �˾����� �Ŀ��ڸ� �Է� �ޱ�
	                String sponsorName = JOptionPane.showInputDialog(
	                        null,  // �θ� ������Ʈ
	                        "�Ŀ��ڸ��� �Է����ּ���:",  // �޽���
	                        "�Ŀ��ڸ� �Է�",  // ����
	                        JOptionPane.PLAIN_MESSAGE);  // �޽��� �ڽ� ����

	                // ����ڰ� �Է��� �Ŀ��ڸ� ó�� (�Է°� Ȯ��)
	                if (sponsorName != null && !sponsorName.trim().isEmpty()) {
	                    // �Է°��� ������ ó��
	                    JOptionPane.showMessageDialog(null, "�Էµ� �Ŀ��ڸ�: " + sponsorName, "�Է� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
	                    // ��: �Ŀ��ڸ����� �˻� ���� ó�� ����
	                } else {
	                    // �Է°��� ������ �˸�
	                    JOptionPane.showMessageDialog(null, "�Ŀ��ڸ��� �Է����� �ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);

	            }
		        		        		    
			}
		});
		btnNewButton.setIcon(new ImageIcon(RAccept3list.class.getResource("/IMAGES/30.png")));
		toolBar.add(btnNewButton);
	
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				RAccept3list frame = new RAccept3list();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
