/*
	 * ���� ����: ������ �Ŀ��� �������� ������ (JFrame)
	 * ���� ��: �����ڰ� �Ŀ��� �ޱ� ���� �Ŀ����� ���ø� ����� ����
	 * 	- ��� �޴���(�̸̹������ ���ø����)�� �α��� �� ���(������)�̸�,��������
	 *  ->> (������ ��, ����������ư , �α׾ƿ� ��ư ������)
	 * ȭ�� ���ο��� �Ŀ����� ������� ��ũ�ѻ���Ͽ� �Ʒ��� �� ���̰� �� ����
	 * Input: 1.�α��� �� ������ ����
	 * 		  2.�Ŀ��� �����Ŀ�����:(�Ŀ��ڸ�, ����, �ݾ�, �޼���)�� ������
	 * Feature: 
	 *   - �Ŀ��ڰ� �Ŀ��� ������ Ȯ�� �� *�ޱ�*��ư�� ������ 
	 *   >DB�� ���� �ݾ� ������ ��.
	 *   >�˾�â���� �Ŀ��ڸ�, �ݾ׼��� Ȯ��â�� �ѹ� �� �����.
	 *   -***���� �ݾ׵��� *�հ�*�Ǿ� ���߿� ���������� ���������ε� Ȯ�ΰ����ϰ� �� ����
	 */

package RECEIVER2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import template.NLSMenuTemplate;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

import java.util.ArrayList;
import java.awt.Dimension;
import javax.swing.Box;

public class RAccept2 extends JFrame {//RAccept2 Ŭ������ �Ŀ� ����â

	private JPanel contentPane;

	public RAccept2() {// ������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 477);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitle = new JLabel("�Ŀ�����");
		lblTitle.setFont(new Font("������� ExtraBold", Font.BOLD, 20));
		lblTitle.setBounds(27, 64, 311, 30);
		contentPane.add(lblTitle);

		
		// *�Ŀ� �׸� �ϳ��� ǥ��* (Temlist2 Ŭ���� ���ø� ���)
		Temlist2 item = new Temlist2("ȫ�浿", "10,000", "2025.04.10", "�׻� �����մϴ�!\n�ǰ��ϼ��� :)");
		item.setBackground(SystemColor.menu);
		item.setBounds(67, 114, 280, 204); // ��ġ/ũ�� ����
		contentPane.add(item);
		
		
		Temlist2 temlist2 = new Temlist2("�輺��", "5,000", "2025.03.29", "��������^^)");
		temlist2.setBackground(SystemColor.menu);
		temlist2.setBounds(67, 331, 280, 211);
		contentPane.add(temlist2);
		
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

		
		/**<ArrayList<Temlist2>�� Temlist2 �׸� ���� �� �߰�>
		 * 
		// �ӽ÷� ArrayList�� Temlist2 ��ü���� ����
		// (���� �Ŀ� �׸� ����)
		ArrayList<Temlist2> donationList = new ArrayList<>();
		donationList.add(new Temlist2("ȫ�浿", "10,000", "2025.04.10", "�׻� �����մϴ�!\n�ǰ��ϼ��� :)"));
		donationList.add(new Temlist2("�̼���", "20,000", "2025.04.11", "���� �����մϴ�!\n��������~"));
		donationList.add(new Temlist2("�������", "15,000", "2025.04.12", "�� �����մϴ� :)"));

		// ����Ʈ �гο� �׸�� �߰�
		for (Temlist2 item : donationList) {
		    t.setBackground(SystemColor.menu);
			listPanel.add(item);
			listPanel.add(Box.createVerticalStrut(10)); // �׸� �� ����
			
			------->> �� ������ DB���� �ҷ��� �����͸� donationList�� ������ ���డ��			
		*/		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				RAccept2 frame = new RAccept2();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
