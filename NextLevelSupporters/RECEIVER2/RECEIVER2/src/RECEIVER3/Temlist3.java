/*
	 * ���� ����: ���� �Ŀ� ���� ���ø�(Temlist3)///���̺���**
	 * (������������������ ���� ���ø�)
	 * ���� ��: �����ڰ� ���� �Ŀ��� ����(�Ŀ��ڸ�, ����, �ݾ�, �޼���)������ ��ȸ���
	 * Input: �α��� �� ������ ������ ���� �Ŀ����� ����( �Ŀ��ڸ�, ����, �ݾ�, �޼���)
	 * Feature: 
	 *  - �Ŀ����� ������ ���̵��?��¥��(�ֽ�)?���� ���̰� ���� ����
	 *   >DB�� ����� Į��,������ ������
	 *   >��ȸâ���� ��ȸ�� �� ������ Ȯ���Ҽ�����
	 *  Output: ����.
	 */

package RECEIVER3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.BorderLayout;

public class Temlist3 extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Create the panel.
     */
    public Temlist3() {
        setLayout(new BorderLayout());  // BorderLayout�� ����Ͽ� ���̾ƿ� ����

        // ���̺� �����Ϳ� �÷� �̸� ����
        String[] columnNames = {"ID", "�Ŀ��ڸ�", "�Ͻ�", "�ݾ�", "�޼���"};
        Object[][] data = {
            {1, "ȫ�浿", "2025-04-16", 10000, "�׻� �����մϴ�! �ǰ��ϼ���"},
            {2, "��ö��", "2025-04-15", 5000, "��������"},
            {3, "�̿���", "2025-04-14", 1500, "������ �Ǳ� �ٶ��ϴ�."}
        };

        // DefaultTableModel�� �̿��Ͽ� ���̺� �� ����
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // JTable ����
        JTable table = new JTable(model);

        // �� Į���� �ʺ� �������� ����
        TableColumn column = null;

        // ù ��° Į��(ID) �ʺ� ����
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(25);  // ID Į�� �ʺ� 50
        
        // ����° Į��(�޼���) �ʺ� ����
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(100); // �޼��� Į�� �ʺ� 500

        // �ټ� ��° Į��(�޼���) �ʺ� ����
        column = table.getColumnModel().getColumn(4);
        column.setPreferredWidth(450); // �޼��� Į�� �ʺ� 500
        

        // ���̺��� JScrollPane�� �߰��Ͽ� ��ũ�� �����ϰ� �����
        JScrollPane scrollPane = new JScrollPane(table);
        
        // ���̺� ��Ȱ��ȭ (���� �Ұ���)
        table.setEnabled(false);  // ���̺��� ��Ȱ��ȭ�Ͽ� ���� �Ұ�!

        // JScrollPane�� �гο� �߰�
        add(scrollPane, BorderLayout.CENTER);

        // ���̺��� �� �ʺ� ������ ������ �� ���� ��ħ
        table.getTableHeader().repaint(); // ��� ���� ��ħ
        table.repaint(); // ���̺� ���� ���� ��ħ
    }

    public static void main(String[] args) {
        // JFrame ����
        JFrame frame = new JFrame("Table Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(741, 475);

        // Temlist3 �г��� �����ӿ� �߰�
        frame.getContentPane().add(new Temlist3());

        // ������ ���̱�
        frame.setVisible(true);
    }
}
