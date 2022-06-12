import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.security.PublicKey;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// GUI가 실행될 프레임
public class SystemFrame extends JFrame {
	
	// 컴포넌트
	MenuPanel menuPanel;
	EnterPanel enterPanel;
	
	// 생성자
	public SystemFrame() {
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("자바시 공영주차장 관리 프로그램");
		
		// 컴포넌트 생성
		menuPanel = new MenuPanel(this);
		enterPanel = new EnterPanel(this);
	
		
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
    }
	
	
	// Main Method
	public static void main (String[] args) {
		// 패널 생성
		SystemFrame f = new SystemFrame();
	}
}




