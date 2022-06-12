import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/* My Panel : ����� �г��� �⺻ */
class MyPanel extends JPanel{ 
	// ���� ���� ���� : �챸��
	public static Color background = new Color (242, 230, 223, 255);
	
	// ������
	public MyPanel() {
	    // ��ġ������ border vs null
		this.setBackground(background);
		this.setVisible(true); 
	}
}

/* Menu Panel */
class MenuPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JLabel timeJLabel; // ���� �ð� �ð�
	JButton enterButton; // ����
	JButton exitButton; // ����
	JButton displayButton; // ��ȸ
	JButton memberButton; // ������� ����� ����
	
	// �ð�
	LocalTime time = LocalTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	// ������
	public MenuPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ���̾ƿ� : �׸��� ���̾ƿ�
		this.setLayout(new GridLayout(5, 1, 20, 20));
		this.setBorder(BorderFactory.createEmptyBorder(50, 300, 100, 300)); // ����
		
		// ������Ʈ ����
		timeJLabel = new JLabel(time.format(formatter));
		timeJLabel.setFont(new Font("���� ���", Font.PLAIN, 50));
		timeJLabel.setHorizontalAlignment(JLabel.CENTER);
			// timer(); �߰�
		enterButton = new JButton("�����ϱ�");
		exitButton = new JButton("�����ϱ�");
		memberButton = new JButton("������� ����");
		displayButton = new JButton("��ȸ�ϱ�");
		
		// ��ư �̺�Ʈ ó�� �ڵ�
		enterButton.addActionListener(e -> {
			systemFrame.change(systemFrame.enterPanel);
		});
		
		// ������Ʈ �߰�
		this.add(timeJLabel);
		this.add(enterButton);
		this.add(exitButton);
		this.add(displayButton);
		this.add(memberButton);
	}
}

/* Enter Panel */
class EnterPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JPanel centerJPanel; 
	JPanel rightJPanel;
	JButton oKButton; // Ȯ�� ��ư
	JTextField carIDField; // �ڵ��� ��ȣ
	JTextField phoneNumberField; // ��ȭ��ȣ
	JTextField nameField; // �̸�
	JRadioButton carModelButton; // �� ����
	ButtonGroup group; // �� ���� ������ư �׷�
	

	// ������
	public EnterPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ������Ʈ ����
			// �г�
		centerJPanel = new JPanel();
		rightJPanel = new JPanel();
		centerJPanel.setLayout(new GridLayout(3, 1, 20, 20));
		centerJPanel.setBorder(BorderFactory.createEmptyBorder(100, 300, 100, 300)); // ����
		rightJPanel.setBorder(BorderFactory.createEmptyBorder(100, 300, 100, 300)); // ����
			
		oKButton = new JButton();
		carIDField = new JTextField();
		phoneNumberField = new JTextField();
		nameField = new JTextField();
			// ������ư
		carModelButton = new JRadioButton("�� ����");
		
		
		
		// �̺�Ʈ ó�� �ڵ�
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.enterPanel); // ���� �гη�
		});
		
		// ������Ʈ �߰�
		centerJPanel.add(carIDField);
		centerJPanel.add(nameField);
		centerJPanel.add(phoneNumberField);
		rightJPanel.add(carModelButton);
	}
}