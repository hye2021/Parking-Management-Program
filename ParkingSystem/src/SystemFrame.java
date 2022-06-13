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

// GUI가 실행될 프레임
public class SystemFrame extends JFrame {
	
	// 컴포넌트
	Vehicle currentVehicle = new Vehicle();
	ParkingLot[] parkingLot; // 주차장
	LinkedList<Vehicle> waitingList = new LinkedList<Vehicle>(); // 대기 리스트
	LinkedList<Vehicle> memberList = new LinkedList<Vehicle>(); // 정기결제 멤버 리스트
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
	
	// 생성자
	public SystemFrame() {
		setTitle("자바시 공영주차장 관리 프로그램");
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫으면 메모리에서 제거
		setLocationRelativeTo(null); // 화면 가운데 배치
		setResizable(false); // 프레임 크기 고정
		
		// 주차장 
		parkingLot = new ParkingLot[12];
		for(int i = 0; i < 12; i++) {
			if (i < 10) {
				parkingLot[i] = new ParkingLot();
			}
			else {
				parkingLot[i] = new ParkingLot(true);
			}
		}
		
		// 컴포넌트 생성
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
		
		// 프레임에 메인 메뉴 패널 추가
		this.add(menuPanel);
		setVisible(true);
		
	}
	
	// 패널 변경 함수
	 public void change(MyPanel nextPanel) { 
		// 현재 패널을 지우고 다음 프레임에 패널을 추가
        getContentPane().removeAll();
        getContentPane().add(nextPanel);
        revalidate();
        repaint();
    }
	
	
	// Main Method
	public static void main (String[] args) {
		// 패널 생성
		SystemFrame f = new SystemFrame();
	}
}




