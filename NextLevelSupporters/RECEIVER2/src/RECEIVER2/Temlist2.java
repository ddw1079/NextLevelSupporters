
/*
	 * ���� ����: �Ŀ��� ���� ���ø�
	 * (������ ���������� ���� ���ø�)
	 * ���� ��: �����ڰ� �޾ƾ��ϴ� �Ŀ��� ����(�Ŀ��ڸ�, ����, �ݾ�, �޼���)������ [����,����]��ư
	 * Input: �α��� �� ������ ������ ���� �Ŀ����� ����( �Ŀ��ڸ�, ����, �ݾ�, �޼���)
	 * Feature: 
	 *  - �Ŀ��ڰ� �Ŀ��� ������ Ȯ�� �� *�ޱ�*��ư�� ������ 
	 *   >DB�� ���� �ݾ� ������ ��.
	 *   >�˾�â���� �Ŀ��ڸ�, �ݾ׼��� Ȯ��â�� �ѹ� �� �����.
	 *  - ������ư: ����
	 *  Output: ����.
	 */

package RECEIVER2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Temlist2 extends JPanel {// Temlist2 Ŭ����: �ϳ��� �Ŀ� ������ ǥ��

	public Temlist2() {
	    this("�Ŀ���", "0", "��¥", "�޼���");
	}
	
	public Temlist2(String �Ŀ����̸�, String �ݾ�, String ����, String �޼���) {
		setBackground(Color.WHITE);
		setLayout(null); // ���̾ƿ� �Ŵ��� ������� �ʰ� �ϴ� ���� ��ġ���(�󼼵����� ȸ�� �� ���氡��)
		setPreferredSize(new Dimension(280, 211)); // ũ�� ����

		// �Ŀ��� �̸��� ��
		JLabel lblTitle = new JLabel(" �Ŀ��� " + �Ŀ����̸� + " ��");
		lblTitle.setOpaque(true);
		lblTitle.setBackground(SystemColor.scrollbar);
		lblTitle.setFont(new Font("�������", Font.BOLD, 15));
		lblTitle.setBounds(0, 0, 280, 20);
		add(lblTitle);

		JLabel lblAmount = new JLabel("�ݾ� : " + �ݾ� + "��");
		lblAmount.setBackground(Color.LIGHT_GRAY);
		lblAmount.setBounds(10, 40, 151, 20);
		add(lblAmount);

		JLabel lblDate = new JLabel("���� : " + ����);
		lblDate.setBackground(Color.LIGHT_GRAY);
		lblDate.setBounds(10, 70, 151, 20);
		add(lblDate);

		// �Ŀ� �޽��� �� (�ٹٲ� ������ ���� HTML ���� ���)
		JLabel lblMsg = new JLabel("<html>�޼��� : " + �޼���.replaceAll("\n", "<br>") + "</html>");
		lblMsg.setBounds(10, 100, 151, 100);
		add(lblMsg);

		// '����' ��ư - ����� ����**
		JButton btnNewButton_1 = new JButton("����");
		btnNewButton_1.setFont(new Font("�������", Font.BOLD, 15));
		btnNewButton_1.setBounds(190, 120, 81, 70);
		add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("\uBC1B\uAE30");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ���� ������ (�ʿ�� ������ ��ü)
		        String �Ŀ����̸� = "ȫ�浿";
		        int �ݾ� = 10000;

		        // �˾� �޽��� ����
		        String message = "�Ŀ��� " + �Ŀ����̸� + " �Կ��� \n�ݾ�: " + �ݾ� + "��\n�޾ҽ��ϴ�. ";

		        // �˾� ����
		        JOptionPane.showMessageDialog(null, message, "�Ŀ� ����", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnNewButton_1_1.setBackground(new Color(250, 250, 210));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setFont(new Font("�������", Font.BOLD, 15));
		btnNewButton_1_1.setBounds(190, 40, 81, 70);
		add(btnNewButton_1_1);
	}
}
