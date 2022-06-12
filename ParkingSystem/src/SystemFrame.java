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

// GUI�� ����� ������
public class SystemFrame extends JFrame {
	
	// ������Ʈ
	MenuPanel menuPanel;
	EnterPanel enterPanel;
	
	// ������
	public SystemFrame() {
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("�ڹٽ� ���������� ���� ���α׷�");
		
		// ������Ʈ ����
		menuPanel = new MenuPanel(this);
		enterPanel = new EnterPanel(this);
	
		
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
    }
	
	
	// Main Method
	public static void main (String[] args) {
		// �г� ����
		SystemFrame f = new SystemFrame();
	}
}




