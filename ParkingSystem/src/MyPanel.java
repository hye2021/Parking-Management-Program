import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.security.Identity;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.management.modelmbean.ModelMBean;
import javax.security.auth.x500.X500Principal;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

///* My Panel : ����� �г��� �⺻ *///
class MyPanel extends JPanel{ 
	SystemFrame systemFrame;
	public static Color background = new Color (242, 230, 223, 255);
	Font a = new Font("���� ���", Font.PLAIN, 20);
	Font b = new Font("���� ���", Font.BOLD, 50);
	Font c = new Font("���� ���", Font.BOLD, 30);
	Font d = new Font("���� ���", Font.BOLD, 23);
	
	public LocalTime time;
	public DateTimeFormatter formatter;
	
	// ������
	public MyPanel() {
		// �ð�
		time = LocalTime.now();
		formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		
	    // ��ġ������ border vs null
		this.setBackground(background);
		this.setVisible(true); 
	}
}

///* Menu Panel *///
class MenuPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JLabel timeJLabel; // ���� �ð� �ð�
	JButton enterButton, exitButton, displayButton, memberButton; // ����, ����, ��ȸ, ������� ����� ����


	// ������
	public MenuPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
		timeJLabel = new JLabel(time.format(formatter));
		timeJLabel.setFont(b);
		timeJLabel.setHorizontalAlignment(JLabel.CENTER);
			// timer(); �߰�
		enterButton = new JButton("�����ϱ�");
		exitButton = new JButton("�����ϱ�");
		memberButton = new JButton("������� ����");
		displayButton = new JButton("��ȸ�ϱ�");
		enterButton.setFont(a);
		exitButton.setFont(a);
		memberButton.setFont(a);
		displayButton.setFont(a);
		
		// ��ư �̺�Ʈ ó�� �ڵ�
		enterButton.addActionListener(e -> {
			systemFrame.enterPanel = new EnterPanel(systemFrame);
			systemFrame.change(systemFrame.enterPanel);
		});
		exitButton.addActionListener(e -> {
			systemFrame.exitPanel = new ExitPanel(systemFrame);
			systemFrame.change(systemFrame.exitPanel);
		});
		displayButton.addActionListener(e -> {
			systemFrame.change(systemFrame.displayPanel);
		});
		memberButton.addActionListener(e -> {
			systemFrame.change(systemFrame.memberPanel);
		});
		
		// ������Ʈ �߰�
		this.add(timeJLabel);
		this.add(enterButton);
		this.add(exitButton);
		this.add(displayButton);
		this.add(memberButton);
		
		// ��ġ ����
		timeJLabel.setBounds(0, 90, 1000, 50);
		enterButton.setBounds(350, 200, 300, 50);
		exitButton.setBounds(350, 280, 300, 50);
		displayButton.setBounds(350, 360, 300, 50);
		memberButton.setBounds(350, 440, 300, 50);
		
	}
}

///* Enter Panel *///
class EnterPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton; // Ȯ�� ��ư
	JTextField carIDField, phoneNumberField, nameField; // �ڵ��� ��ȣ, ��ȭ��ȣ, �̸�
	JRadioButton sedan, van, light, disabled; // �� ���� : �¿���, ������, ����, �����
	ButtonGroup group; // �� ���� ������ư �׷�
	JLabel carIDJLabel, phoneNumJLabel, nameJLabel, carModelLabel; 
	boolean member;

	// ������
	public EnterPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
		oKButton = new JButton("�Է��ϱ�");
			// JLabel
		carIDJLabel = new JLabel("���� ��ȣ");
		phoneNumJLabel = new JLabel("��ȭ��ȣ");
		nameJLabel = new JLabel("�̸�");
		carModelLabel = new JLabel("����");
		carIDJLabel.setFont(d);
		phoneNumJLabel.setFont(d);
		nameJLabel.setFont(d);
		carModelLabel.setFont(d);
			// JField
		carIDField = new JTextField();
		phoneNumberField = new JTextField();
		nameField = new JTextField();
		carIDField.setFont(a);
		phoneNumberField.setFont(a);
		nameField.setFont(a);
			// JRadioButton
		sedan = new JRadioButton("�¿���");
		van = new JRadioButton("������");
		light = new JRadioButton("����");
		disabled = new JRadioButton("����� ����");
		sedan.setFont(a);
		van.setFont(a);
		light.setFont(a);
		disabled.setFont(a);
		sedan.setBackground(null);
		van.setBackground(null);
		light.setBackground(null);
		disabled.setBackground(null);
			// ButtonGroup (Radio Button)
		group = new ButtonGroup();
		group.add(sedan);
		group.add(van);
		group.add(light);
		group.add(disabled);
		
		
		// �̺�Ʈ ó�� �ڵ�
		carIDField.addActionListener(e -> {
			// ������� ������ ������ ������ �ڵ����� ä����
			for (int i=0; i< systemFrame.memberList.size(); i++) {
				if (systemFrame.memberList.get(i).carIDString.equals(carIDField.getText())) {
					nameField.setText(systemFrame.memberList.get(i).nameString);
					phoneNumberField.setText(systemFrame.memberList.get(i).phoneNumString);
					if (systemFrame.memberList.get(i).carModel == "�¿���") {
						sedan.setSelected(true);
					}
					else if (systemFrame.memberList.get(i).carModel == "������") {
						van.setSelected(true);
					}
					else if (systemFrame.memberList.get(i).carModel == "����") {
						light.setSelected(true);
					}
					else if (systemFrame.memberList.get(i).carModel == "����� ����") {
						disabled.setSelected(true);
					}
					member = true;
				}
			}
		});
		oKButton.addActionListener(e -> {
			// ���� ��ȣ�� �Է����� ���� ���
			if (carIDField.getText().equals("")) {
				systemFrame.failPanel.faiLabel.setText("���� ��ȣ�� �Է����ּ���.");
				systemFrame.change(systemFrame.failPanel);
			}
			else {
				// current Vehicle�� ���� ���� �Է�
				String model = "�¿���";
				if (sedan.isSelected() == true) {
					model = "�¿���";
				}
				else if (van.isSelected() == true) {
					model = "������";
				}
				else if (light.isSelected() == true) {
					model = "����";
				}
				else if (disabled.isSelected() == true) {
					model = "����� ����";
				}
				systemFrame.currentVehicle = new Vehicle(carIDField.getText(), nameField.getText(), phoneNumberField.getText(), model, member);

				for (int i = 0; i < 12; i++) {
					if (systemFrame.parkingLot[i].isFull == false) {
						if (i < 10) {
							systemFrame.enterPanel2 = new EnterPanel2(systemFrame);
							systemFrame.change(systemFrame.enterPanel2); // ���� �гη�
							break;
						}
						else { // ����� ��������
							if (systemFrame.currentVehicle.carModel != "����� ����") {
								systemFrame.change(systemFrame.addWaitingListPanel); // ��� �гη�
								break;
							}
							else {
								systemFrame.enterPanel2 = new EnterPanel2(systemFrame);
								systemFrame.change(systemFrame.enterPanel2); // ���� �гη�
								break;
							}
						}
					}
					else {
						if (i == 11) {
							systemFrame.change(systemFrame.addWaitingListPanel); // ��� �гη�
						}
					}
					
				}
			
			}
		});
		
		// ������Ʈ �߰�
		add(carIDJLabel);
		add(carIDField);
		add(nameJLabel);
		add(nameField);
		add(phoneNumJLabel);
		add(phoneNumberField);
		add(carModelLabel);
		add(sedan);
		add(van);
		add(light);
		add(disabled);
		add(oKButton);
		
		// ��ġ ����
		carIDJLabel.setBounds(50, 100, 200, 50);
		carIDField.setBounds(200, 100, 300, 50);
		nameJLabel.setBounds(50, 200, 200, 50);
		nameField.setBounds(200, 200, 300, 50);
		phoneNumJLabel.setBounds(50, 300, 200, 50);
		phoneNumberField.setBounds(200, 300, 300, 50);
		carModelLabel.setBounds(600, 100, 200, 50);
		sedan.setBounds(600, 150, 200, 50);
		van.setBounds(600, 200, 200, 50);
		light.setBounds(600, 250, 200, 50);
		disabled.setBounds(600, 300, 200, 50);
		oKButton.setBounds(800, 450, 100, 50);
	}
}

///* Enter Panel 2 *///
class EnterPanel2 extends MyPanel implements FocusListener {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton; // Ȯ�� ��ư
	JButton[] parkNumButtons; // ���� �ڸ� ��ư 
	JTextField carIDField, phoneNumberField, nameField, carModelField; // �ڵ��� ��ȣ, ��ȭ��ȣ, �̸� -> ���� �Ұ���
	JLabel carIDJLabel, phoneNumJLabel, nameJLabel, carModelLabel; 
	static int selectedNum = 0; // ���� ���õ� ���� �ڸ�
	
	// ������
	public EnterPanel2(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
			// JButton
		oKButton = new JButton("�����ϱ�");
		parkNumButtons = new JButton[12]; // 11, 12 : ����� ���� ����
		for (int i = 0; i < 12; i++) {
			if (i < 10) {
				parkNumButtons[i] = new JButton((i+1) + " ��");
			}
			else {
				parkNumButtons[i] = new JButton("����� " + (i - 9) + " ��");
			}
			add(parkNumButtons[i]); // ������Ʈ �߰�
			parkNumButtons[i].setBackground(Color.white);
		}
			// �̹� ������ �ڸ��� ��ư ��Ȱ��ȭ
		for (int i = 0; i < 12; i ++)
		{
			if (systemFrame.parkingLot[i].isFull == true) {
				parkNumButtons[i].setEnabled(false);
			}
		}
			// JLabel
		carIDJLabel = new JLabel("���� ��ȣ");
		phoneNumJLabel = new JLabel("��ȭ��ȣ");
		nameJLabel = new JLabel("�̸�");
		carModelLabel = new JLabel("����");
		carIDJLabel.setFont(new Font("���� ���", Font.PLAIN, 15));
		phoneNumJLabel.setFont(new Font("���� ���", Font.PLAIN, 15));
		nameJLabel.setFont(new Font("���� ���", Font.PLAIN, 15));
		carModelLabel.setFont(new Font("���� ���", Font.PLAIN, 15));
			// JField
		carIDField = new JTextField(systemFrame.currentVehicle.carIDString);
		phoneNumberField = new JTextField(systemFrame.currentVehicle.phoneNumString);
		nameField = new JTextField(systemFrame.currentVehicle.nameString);
		carModelField = new JTextField(systemFrame.currentVehicle.carModel);
		
		carIDField.setEditable(false);
		phoneNumberField.setEditable(false);
		nameField.setEditable(false);
		carModelField.setEditable(false);
		
		// �̺�Ʈ ó�� �ڵ�
		oKButton.addActionListener(e -> {
				if ((selectedNum > 9) && (selectedNum < 12)) {
					if (systemFrame.currentVehicle.carModel == "����� ����") {
						// parkingLot �迭�� ���� ������ ����
						systemFrame.parkingLot[EnterPanel2.selectedNum] = new ParkingLot(systemFrame.currentVehicle, true, true);
						systemFrame.parkingLot[EnterPanel2.selectedNum].enterTime = (time.getHour() * 100) + (time.getMinute());
						systemFrame.change(systemFrame.enterResultPanel); // ���� �гη�
					}
					else {
						systemFrame.failPanelToEnter.faiLabel.setText("����� �������� �Դϴ�.");
						systemFrame.change(systemFrame.failPanelToEnter);
					}
				}
				else {
					// parkingLot �迭�� ���� ������ ����
					systemFrame.parkingLot[EnterPanel2.selectedNum] = new ParkingLot(systemFrame.currentVehicle, true, false);
					systemFrame.parkingLot[EnterPanel2.selectedNum].enterTime = (time.getHour() * 100) + (time.getMinute());
					systemFrame.change(systemFrame.enterResultPanel); // ���� �гη�
				}
			}
		
		);
			// ���� �ڸ� ��ư
		for (int i =0; i < 12; i++) {
			final int a = i;
			parkNumButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EnterPanel2.selectedNum = a;
				}
			});
			parkNumButtons[i].addFocusListener(this);
		}
		
		// ������Ʈ �߰�
		add(carIDJLabel);
		add(carIDField);
		add(nameJLabel);
		add(nameField);
		add(phoneNumJLabel);
		add(phoneNumberField);
		add(carModelLabel);
		add(carModelField);
		add(oKButton);
		
		// ��ġ ����
		carIDJLabel.setBounds(50, 50, 100, 20);
		carIDField.setBounds(140, 50, 100, 20);
		nameJLabel.setBounds(300, 50, 50, 20);
		nameField.setBounds(360, 50, 100, 20);
		phoneNumJLabel.setBounds(490, 50, 100, 20);
		phoneNumberField.setBounds(580, 50, 150, 20);
		carModelLabel.setBounds(770, 50, 50, 20);
		carModelField.setBounds(830, 50, 100, 20);
		oKButton.setBounds(800, 450, 100, 50);
		int x1 = 0, x2 = 0;
		for (int i = 0; i < 12; i++) {
			if (i < 7) {
				parkNumButtons[i].setBounds(50 + x1, 120, 100, 150);
				x1 += 130;
			}
			else {
				parkNumButtons[i].setBounds(50 + x2, 320, 100, 150);
				x2 += 130;
			}
		}
	}
	
	public void focusGained(FocusEvent e) {
		JButton buttonn = (JButton)e.getSource();
		buttonn.setBackground(new Color(124,205,255,100));
	}
	public void focusLost(FocusEvent e) {
		JButton buttonn = (JButton)e.getSource();
		buttonn.setBackground(Color.white);
	}
}

///* Enter Result Panel  *///
class EnterResultPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton; // Ȯ�� ��ư
	JLabel okLabel; // ���� �Ϸ� ����

	// ������
	public EnterResultPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
			// JButton
		oKButton = new JButton("���ư���");
			// JLabel
		okLabel = new JLabel("������ �Ϸ�Ǿ����ϴ�.");
		okLabel.setFont(b);
		okLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// �̺�Ʈ ó�� �ڵ�
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.menuPanel); // ���� �޴���
		});
		
		// ������Ʈ �߰�
		add(oKButton);
		add(okLabel);
		
		// ��ġ ����
		oKButton.setBounds(800, 450, 100, 50);
		okLabel.setBounds(0, 0, 1000, 500);
	}
}

///* Exit Panel *///
class ExitPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton; // Ȯ�� ��ư
	JTextField carIDField, phoneNumberField, nameField, parkNumField; // �ڵ��� ��ȣ, ��ȭ��ȣ, �̸�, ���� �ڸ�
	JLabel carIDJLabel, phoneNumJLabel, nameJLabel, parkNumJLabel, calculateJLabel, amountJLabel; 
	int number; // ���� ��ȣ
	

	// ������
	public ExitPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
		oKButton = new JButton("�����ϱ�");
			// JLabel
		carIDJLabel = new JLabel("���� ��ȣ");
		phoneNumJLabel = new JLabel("��ȭ��ȣ");
		nameJLabel = new JLabel("�̸�");
		parkNumJLabel = new JLabel("���� ��ȣ");
		calculateJLabel = new JLabel("�����ϱ�");
		amountJLabel = new JLabel(); // ������ ����ؼ� �ֱ�
		carIDJLabel.setFont(d);
		phoneNumJLabel.setFont(new Font("���� ���", Font.BOLD, 18));
		nameJLabel.setFont(new Font("���� ���", Font.BOLD, 18));
		parkNumJLabel.setFont(new Font("���� ���", Font.BOLD, 18));
		calculateJLabel.setFont(d);
		amountJLabel.setFont(c);
			// JField
		carIDField = new JTextField();
		phoneNumberField = new JTextField();
		nameField = new JTextField();
		parkNumField = new JTextField();
		carIDField.setFont(a);
		phoneNumberField.setFont(a);
		nameField.setFont(a);
		phoneNumberField.setEditable(false);
		nameField.setEditable(false);
		parkNumField.setEditable(false);
		
		// �̺�Ʈ ó�� �ڵ�
			// parkingLot���� ���� ���̵� ã�� ���� ������ �ʵ带 ä��� ���� �ð� ����
		carIDField.addActionListener(e -> { 
			for (int i =0; i < 12; i++) {
				if (systemFrame.parkingLot[i].aCar.carIDString.equals(carIDField.getText())) {
					// ���� �ð� ���� �� ���
					systemFrame.parkingLot[i].exitTime = (time.getHour() * 100) + (time.getMinute());
					// ������� ����
					if (systemFrame.parkingLot[i].aCar.isMember == true) {
						amountJLabel.setText("������� ȸ��");
					} else {
						amountJLabel.setText(systemFrame.parkingLot[i].calculate() + " ��");
					}
					// ȭ�鿡 �ش� ���� ���
					phoneNumberField.setText(systemFrame.parkingLot[i].aCar.phoneNumString);
					nameField.setText(systemFrame.parkingLot[i].aCar.nameString);
					parkNumField.setText((i + 1) + " ��");
					number = i;
					break; 
				}
				else if (i == 11) {
					// �ش� ������ ���ٰ� �ȳ� �� main����
					systemFrame.failPanel.faiLabel.setText("�ش� ������ �����ϴ�.");
					systemFrame.change(systemFrame.failPanel);
				}
			}
		});
		
		oKButton.addActionListener(e -> {
			// parkingLot �迭���� ���� ���� ���� ����
			systemFrame.parkingLot[number] = new ParkingLot();
			// ��� ������ �ִٸ� ù��° ������ �ش� �ڸ��� �����ϱ�
			if (systemFrame.waitingList.size() > 0) {
				String model = systemFrame.waitingList.getFirst().carModel;
				if (model == "����� ����") {
					systemFrame.parkingLot[number] = new ParkingLot(systemFrame.waitingList.getFirst(), true, true);
				}
				else {
					systemFrame.parkingLot[number] = new ParkingLot(systemFrame.waitingList.getFirst(), true, false);
				}
				systemFrame.parkingLot[number].enterTime = (time.getHour() * 100) + (time.getMinute());
				systemFrame.waitingList.removeFirst(); // ����
				systemFrame.change(systemFrame.addWaitingResultPanel); // ���� �гη�
			}
			systemFrame.change(systemFrame.exitResultPanel); // ���� �гη�
		});
		
		// ������Ʈ �߰�
		add(carIDJLabel);
		add(carIDField);
		add(nameJLabel);
		add(parkNumJLabel);
		add(calculateJLabel);
		add(amountJLabel);
		add(nameField);
		add(phoneNumJLabel);
		add(phoneNumberField);
		add(parkNumField);
		add(oKButton);
		
		// ��ġ ����
		carIDJLabel.setBounds(50, 100, 200, 50);
		carIDField.setBounds(200, 100, 300, 50);
		nameJLabel.setBounds(50, 200, 200, 30);
		nameField.setBounds(200, 200, 300, 30);
		phoneNumJLabel.setBounds(50, 300, 200, 30);
		phoneNumberField.setBounds(200, 300, 300, 30);
		parkNumJLabel.setBounds(50, 400, 200, 30);
		parkNumField.setBounds(200, 400, 300, 30);
		oKButton.setBounds(800, 450, 100, 50);
		calculateJLabel.setBounds(600, 100, 200, 50);
		amountJLabel.setBounds(600, 200, 350, 50);
	}
}

///* Exit Result Panel  *///
class ExitResultPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton; // Ȯ�� ��ư
	JLabel okLabel; // ���� �Ϸ� ����

	// ������
	public ExitResultPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
			// JButton
		oKButton = new JButton("���ư���");
			// JLabel
		okLabel = new JLabel("������ �Ϸ�Ǿ����ϴ�.");
		okLabel.setFont(b);
		okLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// �̺�Ʈ ó�� �ڵ�
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.menuPanel); // ���� �޴���
		});
		
		// ������Ʈ �߰�
		add(oKButton);
		add(okLabel);
		
		// ��ġ ����
		oKButton.setBounds(800, 450, 100, 50);
		okLabel.setBounds(0, 0, 1000, 500);
	}
}

///* Add Waiting Result Panel  *///
class AddWaitingResultPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton; // Ȯ�� ��ư
	JLabel okLabel; // ���� �Ϸ� ����

	// ������
	public AddWaitingResultPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
			// JButton
		oKButton = new JButton("���ư���");
			// JLabel
		okLabel = new JLabel(systemFrame.parkingLot[systemFrame.exitPanel.number] + " �� ��� ������ �����Ͽ����ϴ�.");
		okLabel.setFont(c);
		okLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// �̺�Ʈ ó�� �ڵ�
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.menuPanel); // ���� �޴���
		});
		
		// ������Ʈ �߰�
		add(oKButton);
		add(okLabel);
		
		// ��ġ ����
		oKButton.setBounds(800, 450, 100, 50);
		okLabel.setBounds(0, 0, 1000, 500);
	}
}

///*  Display Panel  *///
class DisplayPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton, noButton; // Ȯ�� ��ư

	// ������
	public DisplayPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
			// JButton
		oKButton = new JButton("���� ����Ʈ");
		noButton = new JButton("��� ����Ʈ");
		oKButton.setFont(c);
		noButton.setFont(c);
		
		// �̺�Ʈ ó�� �ڵ�
		oKButton.addActionListener(e -> {
			systemFrame.ParkingLotDisplayPanel = new ParkingLotDisplayPanel(systemFrame);
			systemFrame.change(systemFrame.ParkingLotDisplayPanel);
		});
		noButton.addActionListener(e -> {
			systemFrame.waitingListDisplayPanel = new WaitingListDisplayPanel(systemFrame);
			systemFrame.change(systemFrame.waitingListDisplayPanel);
		});
		
		// ������Ʈ �߰�
		add(oKButton);
		add(noButton);
		
		// ��ġ ����
		oKButton.setBounds(250, 220, 200, 70);
		noButton.setBounds(550, 220, 200, 70);

	}
}

///* Waiting List Display Panel *///
class WaitingListDisplayPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton; // Ȯ�� ��ư
	JLabel[] labels ; // ��� ���� 
	
	// ������
	public WaitingListDisplayPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
		oKButton = new JButton("���ư���");
			// JLabel
		labels = new JLabel[12]; // 12������ ǥ����
		int y = 0;
		// ����
		System.out.println(systemFrame.waitingList.size());
		for (int i = 0; i < systemFrame.waitingList.size(); i++) {
			// ����
			labels[i] = new JLabel((i+1) + " ��)    " 
					+ systemFrame.waitingList.get(i).carIDString + "    "
					+ systemFrame.waitingList.get(i).nameString + "    "
					+ systemFrame.waitingList.get(i).phoneNumString + "    "
					+ systemFrame.waitingList.get(i).carModel + "    ");
			// ����
			labels[i].setFont(new Font("���� ���", Font.PLAIN, 15));
			// �߰�
			add(labels[i]);
			// ��ġ ����
			if (i < 7) {
				labels[i].setBounds(50, 50 + y, 400, 50);
			}
			else {
				labels[i].setBounds(500, y - 440, 400, 50);
			}
			y += 70;
		}

		
		// �̺�Ʈ ó�� �ڵ�
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.menuPanel); 
		});

		// ������Ʈ �߰�
		add(oKButton);
		
		// ��ġ ����
		oKButton.setBounds(800, 450, 100, 50);

	}
}

///* Parking Lot Display Panel *///
class ParkingLotDisplayPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton; // Ȯ�� ��ư
	JLabel[] labels ; // ��� ���� 
	
	// ������
	public ParkingLotDisplayPanel(SystemFrame systemFrame) {
		
this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
		oKButton = new JButton("���ư���");
			// JLabel
		labels = new JLabel[12];
		int y = 0;
		for (int i = 0; i < 12; i++) {
			// ����
			labels[i] = new JLabel((i+1) + " ��)    "
					+ systemFrame.parkingLot[i].aCar.carIDString + "    "
					+ systemFrame.parkingLot[i].aCar.nameString + "    "
					+ systemFrame.parkingLot[i].aCar.phoneNumString + "    "
					+ systemFrame.parkingLot[i].aCar.carModel + "    ");
			// ����
			labels[i].setFont(new Font("���� ���", Font.PLAIN, 15));
			// labels[i].setHorizontalAlignment(JLabel.CENTER);
			if (systemFrame.parkingLot[i].aCar.carIDString.equals("�� ��ȣ") == false) {
				add(labels[i]);
				// ��ġ ����
				if (i < 7) {
					labels[i].setBounds(50, 50 + y, 400, 50);
				}
				else {
					labels[i].setBounds(500, y - 440, 400, 50);
				}
				y += 70;
			}
		}

		
		// �̺�Ʈ ó�� �ڵ�
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.menuPanel); 
		});

		// ������Ʈ �߰�
		add(oKButton);
		
		// ��ġ ����
		oKButton.setBounds(800, 450, 100, 50);
	}
}

///*  Member Panel  *///
class MemberPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton, noButton; // Ȯ�� ��ư

	// ������
	public MemberPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
			// JButton
		oKButton = new JButton("�߰�");
		noButton = new JButton("��ȸ");
		oKButton.setFont(c);
		noButton.setFont(c);
		
		// �̺�Ʈ ó�� �ڵ�
		oKButton.addActionListener(e -> {
			systemFrame.addMemberPanel = new AddMemberPanel(systemFrame);
			systemFrame.change(systemFrame.addMemberPanel);
		});
		noButton.addActionListener(e -> {
			systemFrame.memberDisplayPanel = new MemberDisplayPanel(systemFrame);
			systemFrame.change(systemFrame.memberDisplayPanel);
		});
		
		// ������Ʈ �߰�
		add(oKButton);
		add(noButton);
		
		// ��ġ ����
		oKButton.setBounds(250, 220, 200, 70);
		noButton.setBounds(550, 220, 200, 70);

	}
}

///* Member Display Panel *///
class MemberDisplayPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton; // Ȯ�� ��ư
	JLabel[] labels ; // ��� ���� 
	
	// ������
	public MemberDisplayPanel(SystemFrame systemFrame) {
			
			this.systemFrame = systemFrame;
			
			// ��ġ������ : null (������ġ�� ��ġ)
			this.setLayout(null);
			
			// ������Ʈ ����
			oKButton = new JButton("���ư���");
				// JLabel
			labels = new JLabel[12]; // 12������ ǥ����
			int y = 0;
			for (int i = 0; i < systemFrame.memberList.size(); i++) {
				// ����
				labels[i] = new JLabel((i+1) + " ��)    " 
						+ systemFrame.memberList.get(i).carIDString + "    "
						+ systemFrame.memberList.get(i).nameString + "    "
						+ systemFrame.memberList.get(i).phoneNumString + "    "
						+ systemFrame.memberList.get(i).carModel + "    ");
				// ����
				labels[i].setFont(new Font("���� ���", Font.PLAIN, 15));
				// �߰�
				add(labels[i]);
				// ��ġ ����
				if (i < 7) {
					labels[i].setBounds(50, 50 + y, 400, 50);
				}
				else {
					labels[i].setBounds(500, y - 440, 400, 50);
				}
				y += 70;
			}

			
			// �̺�Ʈ ó�� �ڵ�
			oKButton.addActionListener(e -> {
				systemFrame.change(systemFrame.menuPanel); 
			});

			// ������Ʈ �߰�
			add(oKButton);
			
			// ��ġ ����
			oKButton.setBounds(800, 450, 100, 50);
		}
}

///* Add Member Panel *///
class AddMemberPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton; // Ȯ�� ��ư
	JTextField carIDField, phoneNumberField, nameField; // �ڵ��� ��ȣ, ��ȭ��ȣ, �̸�
	JRadioButton sedan, van, light, disabled; // �� ���� : �¿���, ������, ����, �����
	ButtonGroup group; // �� ���� ������ư �׷�
	JLabel carIDJLabel, phoneNumJLabel, nameJLabel, carModelLabel; 
	

	// ������
	public AddMemberPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
		oKButton = new JButton("�Է��ϱ�");
			// JLabel
		carIDJLabel = new JLabel("���� ��ȣ");
		phoneNumJLabel = new JLabel("��ȭ��ȣ");
		nameJLabel = new JLabel("�̸�");
		carModelLabel = new JLabel("����");
		carIDJLabel.setFont(d);
		phoneNumJLabel.setFont(d);
		nameJLabel.setFont(d);
		carModelLabel.setFont(d);
			// JField
		carIDField = new JTextField();
		phoneNumberField = new JTextField();
		nameField = new JTextField();
		carIDField.setFont(a);
		phoneNumberField.setFont(a);
		nameField.setFont(a);
			// JRadioButton
		sedan = new JRadioButton("�¿���");
		van = new JRadioButton("������");
		light = new JRadioButton("����");
		disabled = new JRadioButton("����� ����");
		sedan.setBackground(null);
		van.setBackground(null);
		light.setBackground(null);
		disabled.setBackground(null);
		sedan.setFont(a);
		van.setFont(a);
		light.setFont(a);
		disabled.setFont(a);
			// ButtonGroup (Radio Button)
		group = new ButtonGroup();
		group.add(sedan);
		group.add(van);
		group.add(light);
		group.add(disabled);
		
		
		// �̺�Ʈ ó�� �ڵ�
		oKButton.addActionListener(e -> {
			// ������ ��� �Է����� ���� ���
			if ((carIDField.getText().equals("")) || (phoneNumberField.getText().equals(""))
					|| (nameField.getText().equals("")) || (group.getSelection() == null)) {
				systemFrame.failPanel.faiLabel.setText("������ ��Ȯ�ϰ� �Է����ּ���.");
				systemFrame.change(systemFrame.failPanel);
			} else {
				// current Vehicle�� ���� ���� �Է�
				String model = "�¿���";
				if (sedan.isSelected() == true) {
					model = "�¿���";
				} else if (van.isSelected() == true) {
					model = "������";
				} else if (light.isSelected() == true) {
					model = "����";
				} else if (disabled.isSelected() == true) {
					model = "����� ����";
				}
				systemFrame.currentVehicle = new Vehicle(carIDField.getText(), nameField.getText(),
						phoneNumberField.getText(), model, true);
				systemFrame.memberList.add(systemFrame.currentVehicle);
				systemFrame.change(systemFrame.menuPanel);
			}
		});
		
		// ������Ʈ �߰�
		add(carIDJLabel);
		add(carIDField);
		add(nameJLabel);
		add(nameField);
		add(phoneNumJLabel);
		add(phoneNumberField);
		add(carModelLabel);
		add(sedan);
		add(van);
		add(light);
		add(disabled);
		add(oKButton);
		
		// ��ġ ����
		carIDJLabel.setBounds(50, 100, 200, 50);
		carIDField.setBounds(200, 100, 300, 50);
		nameJLabel.setBounds(50, 200, 200, 50);
		nameField.setBounds(200, 200, 300, 50);
		phoneNumJLabel.setBounds(50, 300, 200, 50);
		phoneNumberField.setBounds(200, 300, 300, 50);
		carModelLabel.setBounds(600, 100, 200, 50);
		sedan.setBounds(600, 150, 200, 50);
		van.setBounds(600, 200, 200, 50);
		light.setBounds(600, 250, 200, 50);
		disabled.setBounds(600, 300, 200, 50);
		oKButton.setBounds(800, 450, 100, 50);
	}
}


///*  Fail Panel  *///
class FailPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton; // Ȯ�� ��ư
	JLabel faiLabel; // ��� ����

	// ������
	public FailPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
			// JButton
		oKButton = new JButton("���ư���");
			// JLabel
		faiLabel = new JLabel("�ȳ�����");
		faiLabel.setFont(b);
		faiLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// �̺�Ʈ ó�� �ڵ�
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.menuPanel); // ���� �޴���
		});
		
		// ������Ʈ �߰�
		add(oKButton);
		add(faiLabel);
		
		// ��ġ ����
		oKButton.setBounds(800, 450, 100, 50);
		faiLabel.setBounds(0, 0, 1000, 500);
	}
}

///*  Fail Panel to Enter Panel  *///
class FailPanelToEnter extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton; // Ȯ�� ��ư
	JLabel faiLabel; // ��� ����

	// ������
	public FailPanelToEnter(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
			// JButton
		oKButton = new JButton("���ư���");
			// JLabel
		faiLabel = new JLabel("�ȳ�����");
		faiLabel.setFont(b);
		faiLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// �̺�Ʈ ó�� �ڵ�
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.enterPanel2); // ���� �޴���
		});
		
		// ������Ʈ �߰�
		add(oKButton);
		add(faiLabel);
		
		// ��ġ ����
		oKButton.setBounds(800, 450, 100, 50);
		faiLabel.setBounds(0, 0, 1000, 500);
	}
}

///*  Add Waiting List Panel  *///
class AddWaitingListPanel extends MyPanel {

	// ������Ʈ
	SystemFrame systemFrame; // ���� �г��� ������ ������
	JButton oKButton, noButton; // Ȯ�� ��ư
	JLabel label, label2; // �ȳ� ����

	// ������
	public AddWaitingListPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// ��ġ������ : null (������ġ�� ��ġ)
		this.setLayout(null);
		
		// ������Ʈ ����
			// JButton
		oKButton = new JButton("�߰��ϱ�");
		noButton = new JButton("���ư���");
			// JLabel
		label = new JLabel("�����忡 �ڸ��� �����ϴ�.");
		label2 = new JLabel("�ش������� ��⸮��Ʈ�� �߰��Ͻðڽ��ϱ�?");
		label.setFont(new Font("���� ���", Font.BOLD, 35));
		label.setHorizontalAlignment(JLabel.CENTER);
		label2.setFont(new Font("���� ���", Font.BOLD, 35));
		label2.setHorizontalAlignment(JLabel.CENTER);
		
		// �̺�Ʈ ó�� �ڵ�
		oKButton.addActionListener(e -> {
			// ��⸮��Ʈ�� �߰�
			systemFrame.waitingList.add(new Vehicle(systemFrame.currentVehicle));
			// ���� : ��⸮��Ʈ�� �߰��Ǿ����ϴ�
			systemFrame.change(systemFrame.menuPanel);
		});
		noButton.addActionListener(e -> {
			systemFrame.failPanel.faiLabel.setText("��⸮��Ʈ�� �߰��Ǿ����ϴ�.");
			systemFrame.change(systemFrame.failPanel); // ���� �޴���
		});
		
		// ������Ʈ �߰�
		add(oKButton);
		add(noButton);
		add(label);
		add(label2);
		
		// ��ġ ����
		oKButton.setBounds(800, 450, 100, 50);
		noButton.setBounds(670, 450, 100, 50);
		label.setBounds(0, 150, 1000, 100);
		label2.setBounds(0, 250, 1000, 100);
	}
}