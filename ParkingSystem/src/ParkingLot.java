
public class ParkingLot {
	// 필드
	boolean isFull, isDisabled; // 주차되었는지 여부, 장애인 주차구역인지 여부
	int enterTime, exitTime; // 입차시각, 출차시각
	Vehicle aCar; // 현재 주차된 차량
	
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
		
		// 계산하는 코드
		sum = exitTime - enterTime;
		hour = (sum / 100) * 60; // 몫: 시간 -> 분으로 변환
		min = sum % 100; // 나머지 : 분
		sum = (hour + min * 100); // 10분에 1000원
		
		// 기종에 따라서 요금 가감.
		if (aCar.carModel.equals("승용차")) {
			sum = sum * 1;
		}
		else if (aCar.carModel.equals("승합차")) {
			sum = (int)(sum * 1.5f);
		}
		else if (aCar.carModel.equals("경차")) {
			sum = (int)(sum * 0.5f);
		}
		else if (aCar.carModel.equals("장애인 차량")) {
			sum = (int)(sum * 0.5f);
		}
		
		return sum;
	}
}
