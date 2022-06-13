
public class ParkingLot {
	// �ʵ�
	boolean isFull, isDisabled; // �����Ǿ����� ����, ����� ������������ ����
	int enterTime, exitTime; // �����ð�, �����ð�
	Vehicle aCar; // ���� ������ ����
	
	public ParkingLot() {
		aCar = new Vehicle();
		isFull = false;
		isDisabled = false;
		enterTime = 0;
		exitTime = 0;
	}
	
	public ParkingLot(boolean isDisabled) {
		aCar = new Vehicle();
		isFull = false;
		this.isDisabled = isDisabled;
		enterTime = 0;
		exitTime = 0;
	}
	
	public ParkingLot(Vehicle aCar, boolean isFull, boolean isDisabled) {
		this.aCar = aCar;
		this.isFull = isFull;
		this.isDisabled = isDisabled;
		enterTime = 0;
		exitTime = 0;
	}
	
	public int calculate() {
		int sum = 0;
		int hour = 0;
		int min =0;
		
		// ����ϴ� �ڵ�
		sum = exitTime - enterTime;
		hour = (sum / 100) * 60; // ��: �ð� -> ������ ��ȯ
		min = sum % 100; // ������ : ��
		sum = (hour + min * 100); // 10�п� 1000��
		
		// ������ ���� ��� ����.
		if (aCar.carModel.equals("�¿���")) {
			sum = sum * 1;
		}
		else if (aCar.carModel.equals("������")) {
			sum = (int)(sum * 1.5f);
		}
		else if (aCar.carModel.equals("����")) {
			sum = (int)(sum * 0.5f);
		}
		else if (aCar.carModel.equals("����� ����")) {
			sum = (int)(sum * 0.5f);
		}
		
		return sum;
	}
}
