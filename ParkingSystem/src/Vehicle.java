
public class Vehicle {
		public String carIDString, nameString, phoneNumString, carModel; // 차 번호, 소유주 이름, 전화번호, 기종
		public boolean isMember; // 정기결제 여부
		
		// 생성자
		public Vehicle () {
			this.carIDString = "차 번호";
			this.nameString = "소유주 이름";
			this.phoneNumString = "전화번호";
			this.carModel = "승용차";
			this.isMember = false;
		}
		public Vehicle(String carIDString) {
			this.carIDString = carIDString;
			this.nameString = "이름";
			this.phoneNumString = "전화번호";
			this.carModel = "승용차";
			this.isMember = false;
		}
		public Vehicle(String carIDString, String nameString, String phoneNumString,  String carModlelString, boolean isMember) {
			this.carIDString = carIDString;
			this.nameString = nameString;
			this.phoneNumString = phoneNumString;
			this.carModel = carModlelString;
			this.isMember = isMember;
		}
		public Vehicle(Vehicle vehicle) {
			this.carIDString = vehicle.carIDString;
			this.nameString = vehicle.nameString;
			this.phoneNumString = vehicle.phoneNumString;
			this.carModel = vehicle.carModel;
			this.isMember = vehicle.isMember;
		}
}
