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

///* My Panel : 사용할 패널의 기본 *///
class MyPanel extends JPanel{ 
	SystemFrame systemFrame;
	public static Color background = new Color (242, 230, 223, 255);
	Font a = new Font("맑은 고딕", Font.PLAIN, 20);
	Font b = new Font("맑은 고딕", Font.BOLD, 50);
	Font c = new Font("맑은 고딕", Font.BOLD, 30);
	Font d = new Font("맑은 고딕", Font.BOLD, 23);
	
	public LocalTime time;
	public DateTimeFormatter formatter;
	
	// 생성자
	public MyPanel() {
		// 시계
		time = LocalTime.now();
		formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		
	    // 배치관리자 border vs null
		this.setBackground(background);
		this.setVisible(true); 
	}
}

///* Menu Panel *///
class MenuPanel extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JLabel timeJLabel; // 현재 시각 시계
	JButton enterButton, exitButton, displayButton, memberButton; // 입차, 출차, 조회, 정기결제 멤버십 관리


	// 생성자
	public MenuPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
		timeJLabel = new JLabel(time.format(formatter));
		timeJLabel.setFont(b);
		timeJLabel.setHorizontalAlignment(JLabel.CENTER);
			// timer(); 추가
		enterButton = new JButton("주차하기");
		exitButton = new JButton("출차하기");
		memberButton = new JButton("정기결제 관리");
		displayButton = new JButton("조회하기");
		enterButton.setFont(a);
		exitButton.setFont(a);
		memberButton.setFont(a);
		displayButton.setFont(a);
		
		// 버튼 이벤트 처리 코드
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
		
		// 컴포넌트 추가
		this.add(timeJLabel);
		this.add(enterButton);
		this.add(exitButton);
		this.add(displayButton);
		this.add(memberButton);
		
		// 위치 설정
		timeJLabel.setBounds(0, 90, 1000, 50);
		enterButton.setBounds(350, 200, 300, 50);
		exitButton.setBounds(350, 280, 300, 50);
		displayButton.setBounds(350, 360, 300, 50);
		memberButton.setBounds(350, 440, 300, 50);
		
	}
}

///* Enter Panel *///
class EnterPanel extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton; // 확인 버튼
	JTextField carIDField, phoneNumberField, nameField; // 자동차 번호, 전화번호, 이름
	JRadioButton sedan, van, light, disabled; // 차 기종 : 승용차, 승합차, 경차, 장애인
	ButtonGroup group; // 차 기종 라디오버튼 그룹
	JLabel carIDJLabel, phoneNumJLabel, nameJLabel, carModelLabel; 
	boolean member;

	// 생성자
	public EnterPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
		oKButton = new JButton("입력하기");
			// JLabel
		carIDJLabel = new JLabel("차량 번호");
		phoneNumJLabel = new JLabel("전화번호");
		nameJLabel = new JLabel("이름");
		carModelLabel = new JLabel("기종");
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
		sedan = new JRadioButton("승용차");
		van = new JRadioButton("승합차");
		light = new JRadioButton("경차");
		disabled = new JRadioButton("장애인 차량");
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
		
		
		// 이벤트 처리 코드
		carIDField.addActionListener(e -> {
			// 정기결제 멤버라면 나머지 정보가 자동으로 채워짐
			for (int i=0; i< systemFrame.memberList.size(); i++) {
				if (systemFrame.memberList.get(i).carIDString.equals(carIDField.getText())) {
					nameField.setText(systemFrame.memberList.get(i).nameString);
					phoneNumberField.setText(systemFrame.memberList.get(i).phoneNumString);
					if (systemFrame.memberList.get(i).carModel == "승용차") {
						sedan.setSelected(true);
					}
					else if (systemFrame.memberList.get(i).carModel == "승합차") {
						van.setSelected(true);
					}
					else if (systemFrame.memberList.get(i).carModel == "경차") {
						light.setSelected(true);
					}
					else if (systemFrame.memberList.get(i).carModel == "장애인 차량") {
						disabled.setSelected(true);
					}
					member = true;
				}
			}
		});
		oKButton.addActionListener(e -> {
			// 차량 번호를 입력하지 않은 경우
			if (carIDField.getText().equals("")) {
				systemFrame.failPanel.faiLabel.setText("차량 번호를 입력해주세요.");
				systemFrame.change(systemFrame.failPanel);
			}
			else {
				// current Vehicle에 차량 정보 입력
				String model = "승용차";
				if (sedan.isSelected() == true) {
					model = "승용차";
				}
				else if (van.isSelected() == true) {
					model = "승합차";
				}
				else if (light.isSelected() == true) {
					model = "경차";
				}
				else if (disabled.isSelected() == true) {
					model = "장애인 차량";
				}
				systemFrame.currentVehicle = new Vehicle(carIDField.getText(), nameField.getText(), phoneNumberField.getText(), model, member);

				for (int i = 0; i < 12; i++) {
					if (systemFrame.parkingLot[i].isFull == false) {
						if (i < 10) {
							systemFrame.enterPanel2 = new EnterPanel2(systemFrame);
							systemFrame.change(systemFrame.enterPanel2); // 다음 패널로
							break;
						}
						else { // 장애인 주차구역
							if (systemFrame.currentVehicle.carModel != "장애인 차량") {
								systemFrame.change(systemFrame.addWaitingListPanel); // 대기 패널로
								break;
							}
							else {
								systemFrame.enterPanel2 = new EnterPanel2(systemFrame);
								systemFrame.change(systemFrame.enterPanel2); // 다음 패널로
								break;
							}
						}
					}
					else {
						if (i == 11) {
							systemFrame.change(systemFrame.addWaitingListPanel); // 대기 패널로
						}
					}
					
				}
			
			}
		});
		
		// 컴포넌트 추가
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
		
		// 위치 설정
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

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton; // 확인 버튼
	JButton[] parkNumButtons; // 주차 자리 버튼 
	JTextField carIDField, phoneNumberField, nameField, carModelField; // 자동차 번호, 전화번호, 이름 -> 수정 불가능
	JLabel carIDJLabel, phoneNumJLabel, nameJLabel, carModelLabel; 
	static int selectedNum = 0; // 현재 선택된 주차 자리
	
	// 생성자
	public EnterPanel2(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
			// JButton
		oKButton = new JButton("주차하기");
		parkNumButtons = new JButton[12]; // 11, 12 : 장애인 주차 구역
		for (int i = 0; i < 12; i++) {
			if (i < 10) {
				parkNumButtons[i] = new JButton((i+1) + " 번");
			}
			else {
				parkNumButtons[i] = new JButton("장애인 " + (i - 9) + " 번");
			}
			add(parkNumButtons[i]); // 컴포넌트 추가
			parkNumButtons[i].setBackground(Color.white);
		}
			// 이미 주차된 자리는 버튼 비활성화
		for (int i = 0; i < 12; i ++)
		{
			if (systemFrame.parkingLot[i].isFull == true) {
				parkNumButtons[i].setEnabled(false);
			}
		}
			// JLabel
		carIDJLabel = new JLabel("차량 번호");
		phoneNumJLabel = new JLabel("전화번호");
		nameJLabel = new JLabel("이름");
		carModelLabel = new JLabel("기종");
		carIDJLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		phoneNumJLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		nameJLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		carModelLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			// JField
		carIDField = new JTextField(systemFrame.currentVehicle.carIDString);
		phoneNumberField = new JTextField(systemFrame.currentVehicle.phoneNumString);
		nameField = new JTextField(systemFrame.currentVehicle.nameString);
		carModelField = new JTextField(systemFrame.currentVehicle.carModel);
		
		carIDField.setEditable(false);
		phoneNumberField.setEditable(false);
		nameField.setEditable(false);
		carModelField.setEditable(false);
		
		// 이벤트 처리 코드
		oKButton.addActionListener(e -> {
				if ((selectedNum > 9) && (selectedNum < 12)) {
					if (systemFrame.currentVehicle.carModel == "장애인 차량") {
						// parkingLot 배열에 현재 차량을 저장
						systemFrame.parkingLot[EnterPanel2.selectedNum] = new ParkingLot(systemFrame.currentVehicle, true, true);
						systemFrame.parkingLot[EnterPanel2.selectedNum].enterTime = (time.getHour() * 100) + (time.getMinute());
						systemFrame.change(systemFrame.enterResultPanel); // 다음 패널로
					}
					else {
						systemFrame.failPanelToEnter.faiLabel.setText("장애인 주차구역 입니다.");
						systemFrame.change(systemFrame.failPanelToEnter);
					}
				}
				else {
					// parkingLot 배열에 현재 차량을 저장
					systemFrame.parkingLot[EnterPanel2.selectedNum] = new ParkingLot(systemFrame.currentVehicle, true, false);
					systemFrame.parkingLot[EnterPanel2.selectedNum].enterTime = (time.getHour() * 100) + (time.getMinute());
					systemFrame.change(systemFrame.enterResultPanel); // 다음 패널로
				}
			}
		
		);
			// 주차 자리 버튼
		for (int i =0; i < 12; i++) {
			final int a = i;
			parkNumButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EnterPanel2.selectedNum = a;
				}
			});
			parkNumButtons[i].addFocusListener(this);
		}
		
		// 컴포넌트 추가
		add(carIDJLabel);
		add(carIDField);
		add(nameJLabel);
		add(nameField);
		add(phoneNumJLabel);
		add(phoneNumberField);
		add(carModelLabel);
		add(carModelField);
		add(oKButton);
		
		// 위치 설정
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

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton; // 확인 버튼
	JLabel okLabel; // 주차 완료 문구

	// 생성자
	public EnterResultPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
			// JButton
		oKButton = new JButton("돌아가기");
			// JLabel
		okLabel = new JLabel("주차가 완료되었습니다.");
		okLabel.setFont(b);
		okLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// 이벤트 처리 코드
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.menuPanel); // 메인 메뉴로
		});
		
		// 컴포넌트 추가
		add(oKButton);
		add(okLabel);
		
		// 위치 설정
		oKButton.setBounds(800, 450, 100, 50);
		okLabel.setBounds(0, 0, 1000, 500);
	}
}

///* Exit Panel *///
class ExitPanel extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton; // 확인 버튼
	JTextField carIDField, phoneNumberField, nameField, parkNumField; // 자동차 번호, 전화번호, 이름, 주차 자리
	JLabel carIDJLabel, phoneNumJLabel, nameJLabel, parkNumJLabel, calculateJLabel, amountJLabel; 
	int number; // 주차 번호
	

	// 생성자
	public ExitPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
		oKButton = new JButton("출차하기");
			// JLabel
		carIDJLabel = new JLabel("차량 번호");
		phoneNumJLabel = new JLabel("전화번호");
		nameJLabel = new JLabel("이름");
		parkNumJLabel = new JLabel("주차 번호");
		calculateJLabel = new JLabel("정산하기");
		amountJLabel = new JLabel(); // 얼마인지 계산해서 넣기
		carIDJLabel.setFont(d);
		phoneNumJLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		nameJLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		parkNumJLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
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
		
		// 이벤트 처리 코드
			// parkingLot에서 현재 아이디를 찾은 다음 나머지 필드를 채우고 현재 시간 저장
		carIDField.addActionListener(e -> { 
			for (int i =0; i < 12; i++) {
				if (systemFrame.parkingLot[i].aCar.carIDString.equals(carIDField.getText())) {
					// 현재 시간 저장 및 계산
					systemFrame.parkingLot[i].exitTime = (time.getHour() * 100) + (time.getMinute());
					// 정기결제 여부
					if (systemFrame.parkingLot[i].aCar.isMember == true) {
						amountJLabel.setText("정기결제 회원");
					} else {
						amountJLabel.setText(systemFrame.parkingLot[i].calculate() + " 원");
					}
					// 화면에 해당 정보 출력
					phoneNumberField.setText(systemFrame.parkingLot[i].aCar.phoneNumString);
					nameField.setText(systemFrame.parkingLot[i].aCar.nameString);
					parkNumField.setText((i + 1) + " 번");
					number = i;
					break; 
				}
				else if (i == 11) {
					// 해당 차량이 업다고 안내 후 main으로
					systemFrame.failPanel.faiLabel.setText("해당 차량이 없습니다.");
					systemFrame.change(systemFrame.failPanel);
				}
			}
		});
		
		oKButton.addActionListener(e -> {
			// parkingLot 배열에서 현재 차량 정보 삭제
			systemFrame.parkingLot[number] = new ParkingLot();
			// 대기 차량이 있다면 첫번째 차량을 해당 자리에 주차하기
			if (systemFrame.waitingList.size() > 0) {
				String model = systemFrame.waitingList.getFirst().carModel;
				if (model == "장애인 차량") {
					systemFrame.parkingLot[number] = new ParkingLot(systemFrame.waitingList.getFirst(), true, true);
				}
				else {
					systemFrame.parkingLot[number] = new ParkingLot(systemFrame.waitingList.getFirst(), true, false);
				}
				systemFrame.parkingLot[number].enterTime = (time.getHour() * 100) + (time.getMinute());
				systemFrame.waitingList.removeFirst(); // 삭제
				systemFrame.change(systemFrame.addWaitingResultPanel); // 다음 패널로
			}
			systemFrame.change(systemFrame.exitResultPanel); // 다음 패널로
		});
		
		// 컴포넌트 추가
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
		
		// 위치 설정
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

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton; // 확인 버튼
	JLabel okLabel; // 주차 완료 문구

	// 생성자
	public ExitResultPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
			// JButton
		oKButton = new JButton("돌아가기");
			// JLabel
		okLabel = new JLabel("출차가 완료되었습니다.");
		okLabel.setFont(b);
		okLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// 이벤트 처리 코드
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.menuPanel); // 메인 메뉴로
		});
		
		// 컴포넌트 추가
		add(oKButton);
		add(okLabel);
		
		// 위치 설정
		oKButton.setBounds(800, 450, 100, 50);
		okLabel.setBounds(0, 0, 1000, 500);
	}
}

///* Add Waiting Result Panel  *///
class AddWaitingResultPanel extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton; // 확인 버튼
	JLabel okLabel; // 주차 완료 문구

	// 생성자
	public AddWaitingResultPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
			// JButton
		oKButton = new JButton("돌아가기");
			// JLabel
		okLabel = new JLabel(systemFrame.parkingLot[systemFrame.exitPanel.number] + " 번 대기 차량을 주차하였습니다.");
		okLabel.setFont(c);
		okLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// 이벤트 처리 코드
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.menuPanel); // 메인 메뉴로
		});
		
		// 컴포넌트 추가
		add(oKButton);
		add(okLabel);
		
		// 위치 설정
		oKButton.setBounds(800, 450, 100, 50);
		okLabel.setBounds(0, 0, 1000, 500);
	}
}

///*  Display Panel  *///
class DisplayPanel extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton, noButton; // 확인 버튼

	// 생성자
	public DisplayPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
			// JButton
		oKButton = new JButton("주차 리스트");
		noButton = new JButton("대기 리스트");
		oKButton.setFont(c);
		noButton.setFont(c);
		
		// 이벤트 처리 코드
		oKButton.addActionListener(e -> {
			systemFrame.ParkingLotDisplayPanel = new ParkingLotDisplayPanel(systemFrame);
			systemFrame.change(systemFrame.ParkingLotDisplayPanel);
		});
		noButton.addActionListener(e -> {
			systemFrame.waitingListDisplayPanel = new WaitingListDisplayPanel(systemFrame);
			systemFrame.change(systemFrame.waitingListDisplayPanel);
		});
		
		// 컴포넌트 추가
		add(oKButton);
		add(noButton);
		
		// 위치 설정
		oKButton.setBounds(250, 220, 200, 70);
		noButton.setBounds(550, 220, 200, 70);

	}
}

///* Waiting List Display Panel *///
class WaitingListDisplayPanel extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton; // 확인 버튼
	JLabel[] labels ; // 출력 내용 
	
	// 생성자
	public WaitingListDisplayPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
		oKButton = new JButton("돌아가기");
			// JLabel
		labels = new JLabel[12]; // 12개까지 표시함
		int y = 0;
		// 딸기
		System.out.println(systemFrame.waitingList.size());
		for (int i = 0; i < systemFrame.waitingList.size(); i++) {
			// 생성
			labels[i] = new JLabel((i+1) + " 번)    " 
					+ systemFrame.waitingList.get(i).carIDString + "    "
					+ systemFrame.waitingList.get(i).nameString + "    "
					+ systemFrame.waitingList.get(i).phoneNumString + "    "
					+ systemFrame.waitingList.get(i).carModel + "    ");
			// 설정
			labels[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			// 추가
			add(labels[i]);
			// 위치 설정
			if (i < 7) {
				labels[i].setBounds(50, 50 + y, 400, 50);
			}
			else {
				labels[i].setBounds(500, y - 440, 400, 50);
			}
			y += 70;
		}

		
		// 이벤트 처리 코드
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.menuPanel); 
		});

		// 컴포넌트 추가
		add(oKButton);
		
		// 위치 설정
		oKButton.setBounds(800, 450, 100, 50);

	}
}

///* Parking Lot Display Panel *///
class ParkingLotDisplayPanel extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton; // 확인 버튼
	JLabel[] labels ; // 출력 내용 
	
	// 생성자
	public ParkingLotDisplayPanel(SystemFrame systemFrame) {
		
this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
		oKButton = new JButton("돌아가기");
			// JLabel
		labels = new JLabel[12];
		int y = 0;
		for (int i = 0; i < 12; i++) {
			// 생성
			labels[i] = new JLabel((i+1) + " 번)    "
					+ systemFrame.parkingLot[i].aCar.carIDString + "    "
					+ systemFrame.parkingLot[i].aCar.nameString + "    "
					+ systemFrame.parkingLot[i].aCar.phoneNumString + "    "
					+ systemFrame.parkingLot[i].aCar.carModel + "    ");
			// 설정
			labels[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			// labels[i].setHorizontalAlignment(JLabel.CENTER);
			if (systemFrame.parkingLot[i].aCar.carIDString.equals("차 번호") == false) {
				add(labels[i]);
				// 위치 설정
				if (i < 7) {
					labels[i].setBounds(50, 50 + y, 400, 50);
				}
				else {
					labels[i].setBounds(500, y - 440, 400, 50);
				}
				y += 70;
			}
		}

		
		// 이벤트 처리 코드
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.menuPanel); 
		});

		// 컴포넌트 추가
		add(oKButton);
		
		// 위치 설정
		oKButton.setBounds(800, 450, 100, 50);
	}
}

///*  Member Panel  *///
class MemberPanel extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton, noButton; // 확인 버튼

	// 생성자
	public MemberPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
			// JButton
		oKButton = new JButton("추가");
		noButton = new JButton("조회");
		oKButton.setFont(c);
		noButton.setFont(c);
		
		// 이벤트 처리 코드
		oKButton.addActionListener(e -> {
			systemFrame.addMemberPanel = new AddMemberPanel(systemFrame);
			systemFrame.change(systemFrame.addMemberPanel);
		});
		noButton.addActionListener(e -> {
			systemFrame.memberDisplayPanel = new MemberDisplayPanel(systemFrame);
			systemFrame.change(systemFrame.memberDisplayPanel);
		});
		
		// 컴포넌트 추가
		add(oKButton);
		add(noButton);
		
		// 위치 설정
		oKButton.setBounds(250, 220, 200, 70);
		noButton.setBounds(550, 220, 200, 70);

	}
}

///* Member Display Panel *///
class MemberDisplayPanel extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton; // 확인 버튼
	JLabel[] labels ; // 출력 내용 
	
	// 생성자
	public MemberDisplayPanel(SystemFrame systemFrame) {
			
			this.systemFrame = systemFrame;
			
			// 배치관리자 : null (절대위치로 배치)
			this.setLayout(null);
			
			// 컴포넌트 생성
			oKButton = new JButton("돌아가기");
				// JLabel
			labels = new JLabel[12]; // 12개까지 표시함
			int y = 0;
			for (int i = 0; i < systemFrame.memberList.size(); i++) {
				// 생성
				labels[i] = new JLabel((i+1) + " 번)    " 
						+ systemFrame.memberList.get(i).carIDString + "    "
						+ systemFrame.memberList.get(i).nameString + "    "
						+ systemFrame.memberList.get(i).phoneNumString + "    "
						+ systemFrame.memberList.get(i).carModel + "    ");
				// 설정
				labels[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
				// 추가
				add(labels[i]);
				// 위치 설정
				if (i < 7) {
					labels[i].setBounds(50, 50 + y, 400, 50);
				}
				else {
					labels[i].setBounds(500, y - 440, 400, 50);
				}
				y += 70;
			}

			
			// 이벤트 처리 코드
			oKButton.addActionListener(e -> {
				systemFrame.change(systemFrame.menuPanel); 
			});

			// 컴포넌트 추가
			add(oKButton);
			
			// 위치 설정
			oKButton.setBounds(800, 450, 100, 50);
		}
}

///* Add Member Panel *///
class AddMemberPanel extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton; // 확인 버튼
	JTextField carIDField, phoneNumberField, nameField; // 자동차 번호, 전화번호, 이름
	JRadioButton sedan, van, light, disabled; // 차 기종 : 승용차, 승합차, 경차, 장애인
	ButtonGroup group; // 차 기종 라디오버튼 그룹
	JLabel carIDJLabel, phoneNumJLabel, nameJLabel, carModelLabel; 
	

	// 생성자
	public AddMemberPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
		oKButton = new JButton("입력하기");
			// JLabel
		carIDJLabel = new JLabel("차량 번호");
		phoneNumJLabel = new JLabel("전화번호");
		nameJLabel = new JLabel("이름");
		carModelLabel = new JLabel("기종");
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
		sedan = new JRadioButton("승용차");
		van = new JRadioButton("승합차");
		light = new JRadioButton("경차");
		disabled = new JRadioButton("장애인 차량");
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
		
		
		// 이벤트 처리 코드
		oKButton.addActionListener(e -> {
			// 정보를 모두 입력하지 않은 경우
			if ((carIDField.getText().equals("")) || (phoneNumberField.getText().equals(""))
					|| (nameField.getText().equals("")) || (group.getSelection() == null)) {
				systemFrame.failPanel.faiLabel.setText("정보를 정확하게 입력해주세요.");
				systemFrame.change(systemFrame.failPanel);
			} else {
				// current Vehicle에 차량 정보 입력
				String model = "승용차";
				if (sedan.isSelected() == true) {
					model = "승용차";
				} else if (van.isSelected() == true) {
					model = "승합차";
				} else if (light.isSelected() == true) {
					model = "경차";
				} else if (disabled.isSelected() == true) {
					model = "장애인 차량";
				}
				systemFrame.currentVehicle = new Vehicle(carIDField.getText(), nameField.getText(),
						phoneNumberField.getText(), model, true);
				systemFrame.memberList.add(systemFrame.currentVehicle);
				systemFrame.change(systemFrame.menuPanel);
			}
		});
		
		// 컴포넌트 추가
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
		
		// 위치 설정
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

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton; // 확인 버튼
	JLabel faiLabel; // 경고 문구

	// 생성자
	public FailPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
			// JButton
		oKButton = new JButton("돌아가기");
			// JLabel
		faiLabel = new JLabel("안내문구");
		faiLabel.setFont(b);
		faiLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// 이벤트 처리 코드
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.menuPanel); // 메인 메뉴로
		});
		
		// 컴포넌트 추가
		add(oKButton);
		add(faiLabel);
		
		// 위치 설정
		oKButton.setBounds(800, 450, 100, 50);
		faiLabel.setBounds(0, 0, 1000, 500);
	}
}

///*  Fail Panel to Enter Panel  *///
class FailPanelToEnter extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton; // 확인 버튼
	JLabel faiLabel; // 경고 문구

	// 생성자
	public FailPanelToEnter(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
			// JButton
		oKButton = new JButton("돌아가기");
			// JLabel
		faiLabel = new JLabel("안내문구");
		faiLabel.setFont(b);
		faiLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// 이벤트 처리 코드
		oKButton.addActionListener(e -> {
			systemFrame.change(systemFrame.enterPanel2); // 메인 메뉴로
		});
		
		// 컴포넌트 추가
		add(oKButton);
		add(faiLabel);
		
		// 위치 설정
		oKButton.setBounds(800, 450, 100, 50);
		faiLabel.setBounds(0, 0, 1000, 500);
	}
}

///*  Add Waiting List Panel  *///
class AddWaitingListPanel extends MyPanel {

	// 컴포넌트
	SystemFrame systemFrame; // 현재 패널을 포함한 프레임
	JButton oKButton, noButton; // 확인 버튼
	JLabel label, label2; // 안내 문구

	// 생성자
	public AddWaitingListPanel(SystemFrame systemFrame) {
		
		this.systemFrame = systemFrame;
		
		// 배치관리자 : null (절대위치로 배치)
		this.setLayout(null);
		
		// 컴포넌트 생성
			// JButton
		oKButton = new JButton("추가하기");
		noButton = new JButton("돌아가기");
			// JLabel
		label = new JLabel("주차장에 자리가 없습니다.");
		label2 = new JLabel("해당차량을 대기리스트에 추가하시겠습니까?");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		label.setHorizontalAlignment(JLabel.CENTER);
		label2.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		label2.setHorizontalAlignment(JLabel.CENTER);
		
		// 이벤트 처리 코드
		oKButton.addActionListener(e -> {
			// 대기리스트에 추가
			systemFrame.waitingList.add(new Vehicle(systemFrame.currentVehicle));
			// 딸기 : 대기리스트에 추가되었습니다
			systemFrame.change(systemFrame.menuPanel);
		});
		noButton.addActionListener(e -> {
			systemFrame.failPanel.faiLabel.setText("대기리스트에 추가되었습니다.");
			systemFrame.change(systemFrame.failPanel); // 메인 메뉴로
		});
		
		// 컴포넌트 추가
		add(oKButton);
		add(noButton);
		add(label);
		add(label2);
		
		// 위치 설정
		oKButton.setBounds(800, 450, 100, 50);
		noButton.setBounds(670, 450, 100, 50);
		label.setBounds(0, 150, 1000, 100);
		label2.setBounds(0, 250, 1000, 100);
	}
}