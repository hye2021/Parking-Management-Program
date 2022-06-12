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

/* My Panel : 사용할 패널의 기본 */
class MyPanel extends JPanel{ 
	// 배경색 전역 변수 : 살구색
	public static Color background = new Color (242, 230, 223, 255);
	
	// 생성자
	public MyPanel() {
	    // 배치관리자 border vs null
		this.setBackground(background);
		this.setVisible(true); 
	}
}

/* Menu Panel */
class MenuPanel extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JLabel timeJLabel; // 현재 시각 시계
	JButton enterButton; // 입차
	JButton exitButton; // 출차
	JButton displayButton; // 조회
	JButton memberButton; // 정기결제 멤버십 관리
	
	// 시계
	LocalTime time = LocalTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	// 생성자
	public MenuPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 레이아웃 : 그리드 레이아웃
		this.setLayout(new GridLayout(5, 1, 20, 20));
		this.setBorder(BorderFactory.createEmptyBorder(50, 300, 100, 300)); // 여백
		
		// 컴포넌트 생성
		timeJLabel = new JLabel(time.format(formatter));
		timeJLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		timeJLabel.setHorizontalAlignment(JLabel.CENTER);
			// timer(); 추가
		enterButton = new JButton("주차하기");
		exitButton = new JButton("출차하기");
		memberButton = new JButton("정기결제 관리");
		displayButton = new JButton("조회하기");
		
		// 버튼 이벤트 처리 코드
		enterButton.addActionListener(e -> {
			systemFrame.change(systemFrame.enterPanel);
		});
		
		// 컴포넌트 추가
		this.add(timeJLabel);
		this.add(enterButton);
		this.add(exitButton);
		this.add(displayButton);
		this.add(memberButton);
	}
}

/* Enter Panel */
class EnterPanel extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JPanel centerJPanel; 
	JPanel rightJPanel;
	JButton oKButton; // 확인 버튼
	JTextField carIDField; // 자동차 번호
	JTextField phoneNumberField; // 전화번호
	JTextField nameField; // 이름
	JRadioButton carModelButton; // 차 기종
	ButtonGroup group; // 차 기종 라디오버튼 그룹
	

	// 생성자
	public EnterPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 컴포넌트 생성
			// 패널
		centerJPanel = new JPanel();
		rightJPanel = new JPanel();
		centerJPanel.setLayout(new GridLayout(3, 1, 20, 20));
		centerJPanel.setBorder(BorderFactory.createEmptyBorder(100, 300, 100, 300)); // 여백
		rightJPanel.setBorder(BorderFactory.createEmptyBorder(100, 300, 100, 300)); // 여백
			
		oKButton = new JButton();
		carIDField = new JTextField();
		phoneNumberField = new JTextField();
		nameField = new JTextField();
			// 라디오버튼
		carModelButton = new JRadioButton("차 기종");
		
		
		
		// 이벤트 처리 코드
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.enterPanel); // 다음 패널로
		});
		
		// 컴포넌트 추가
		centerJPanel.add(carIDField);
		centerJPanel.add(nameField);
		centerJPanel.add(phoneNumberField);
		rightJPanel.add(carModelButton);
	}
}