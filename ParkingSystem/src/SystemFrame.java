import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.security.PublicKey;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// GUI�� ����� ������
public class SystemFrame extends JFrame {
	
	// ������Ʈ
	Vehicle currentVehicle = new Vehicle();
	ParkingLot[] parkingLot; // ������
	LinkedList<Vehicle> waitingList = new LinkedList<Vehicle>(); // ��� ����Ʈ
	LinkedList<Vehicle> memberList = new LinkedList<Vehicle>(); // ������� ��� ����Ʈ
	MenuPanel menuPanel;
	EnterPanel enterPanel;
	EnterPanel2 enterPanel2;
	EnterResultPanel enterResultPanel;
	ExitPanel exitPanel;
	ExitResultPanel exitResultPanel;
	FailPanel failPanel;
	FailPanelToEnter failPanelToEnter;
	AddWaitingListPanel addWaitingListPanel;
	DisplayPanel displayPanel;
	ParkingLotDisplayPanel ParkingLotDisplayPanel;
	WaitingListDisplayPanel waitingListDisplayPanel;
	MemberPanel memberPanel;
	MemberDisplayPanel memberDisplayPanel;
	AddMemberPanel addMemberPanel;
	AddWaitingResultPanel addWaitingResultPanel;
	
	// ������
	public SystemFrame() {
		setTitle("�ڹٽ� ���������� ���� ���α׷�");
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ������ �޸𸮿��� ����
		setLocationRelativeTo(null); // ȭ�� ��� ��ġ
		setResizable(false); // ������ ũ�� ����
		
		// ������ 
		parkingLot = new ParkingLot[12];
		for(int i = 0; i < 12; i++) {
			if (i < 10) {
				parkingLot[i] = new ParkingLot();
			}
			else {
				parkingLot[i] = new ParkingLot(true);
			}
		}
		
		// ������Ʈ ����
		menuPanel = new MenuPanel(this);
		enterPanel = new EnterPanel(this);
		enterPanel2 = new EnterPanel2(this);
		enterResultPanel = new EnterResultPanel(this);
		exitPanel = new ExitPanel(this);
		exitResultPanel = new ExitResultPanel(this);
		failPanel = new FailPanel(this);
		failPanelToEnter = new FailPanelToEnter(this);
		addWaitingListPanel = new AddWaitingListPanel(this);
		displayPanel = new DisplayPanel(this);
		ParkingLotDisplayPanel = new ParkingLotDisplayPanel(this);
		waitingListDisplayPanel = new WaitingListDisplayPanel(this);
		memberPanel = new MemberPanel(this);
		memberDisplayPanel = new MemberDisplayPanel(this);
		addMemberPanel = new AddMemberPanel(this);
		addWaitingResultPanel = new AddWaitingResultPanel(this);
		
		// �����ӿ� ���� �޴� �г� �߰�
		this.add(menuPanel);
		setVisible(true);
		
	}
	
	// �г� ���� �Լ�
	 public void change(MyPanel nextPanel) { 
		// ���� �г��� ����� ���� �����ӿ� �г��� �߰�
        getContentPane().removeAll();
        getContentPane().add(nextPanel);
        revalidate();
        repaint();
    }
	
	
	// Main Method
	public static void main (String[] args) {
		// �г� ����
		SystemFrame f = new SystemFrame();
	}
}




