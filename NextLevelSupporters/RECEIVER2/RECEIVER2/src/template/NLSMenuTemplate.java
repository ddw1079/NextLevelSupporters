package template;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NLSMenuTemplate extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JPanel panel;
	private static JPanel panel_1;
	private static JLabel lblShowUser;
	private static JButton btnCheckHistory;
	private static JButton btnLogin;
	private static JButton btnLogout;
	private static JButton btnRegister;

	/**
	 * ���� ����: ���� �޴� ���ø�
	 * ���� ��: �ֻ�� ���� �޴��� ����. 
	 * 	- �α��� �� ��� Ȯ��. �α��� Ÿ�� Ȯ�� ��� ����
	 * 	- ȸ������, �α��� �� �α׾ƿ� ��ư ����
	 * Input: ���� �α����� ����� ����(�̸�, ����Ÿ��)
	 * Feature: 
	 *  - ����Ÿ�Կ� ���� �ٸ� ������ ���
	 *  - ����Ÿ�� ����: -1 �α׾ƿ��� 0 �Ŀ��� 1 ������ 2 Admin
	 *  	- -1 �α׾ƿ���: "�α������ּ���" ��, �α���, ȸ������ ��ư ��µ�
	 *  	- 0 �Ŀ���: "�ݰ����ϴ� ������ �Ŀ��ڴ�!" ��, �Ŀ�����, ȸ������ ����, �α׾ƿ� ��ư ���
	 *  	- 1 ������: "�ݰ����ϴ� ������ �����ڴ�!" ��, ��������, ȸ������ ����, �α׾ƿ� ��ư ���
	 *  	- 2 Admin: "�ݰ����ϴ� ������ �����ڴ�!" ��, ȸ������ ����?, �α׾ƿ� ��ư ���
	 *  Output: ����.
	 */
	public NLSMenuTemplate() {
		this("1111", 1);
	}
	public NLSMenuTemplate(String username, int usertype) {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		switch (usertype) {
			case -1:
				lblShowUser = new JLabel("�ȳ��ϼ���! �α������ּ���");
				btnLogin = new JButton("�α���");
				panel_1.add(btnLogin);
				btnRegister = new JButton("ȸ������");
				panel_1.add(btnRegister);
				break;
			case 0:
				lblShowUser = new JLabel("�ݰ����ϴ�. " + username + "�Ŀ��ڴ�!");
				btnCheckHistory = new JButton("�Ŀ����� Ȯ��");
				panel_1.add(btnCheckHistory);
				btnLogout = new JButton("�α׾ƿ�");
				panel_1.add(btnLogout);
				break;
			case 1:
				lblShowUser = new JLabel("�ݰ����ϴ�. " + username + " �����ڴ�!");
				btnCheckHistory = new JButton("�������� Ȯ��");
				panel_1.add(btnCheckHistory);
				btnLogout = new JButton("�α׾ƿ�");
				panel_1.add(btnLogout);
				break;
			case 2:
				lblShowUser = new JLabel("�ݰ����ϴ�. " + username + " �����ڴ�!");
				btnLogout = new JButton("�α׾ƿ�");
				panel_1.add(btnLogout);
				break;
			default:
				System.out.println("Menubar default executed. usertype? " + usertype);
				break;
		}
		panel.add(lblShowUser);
	}
}
