
public class Vehicle {
		public String carIDString, nameString, phoneNumString, carModel; // �� ��ȣ, ������ �̸�, ��ȭ��ȣ, ����
		public boolean isMember; // ������� ����
		
		// ������
		public Vehicle () {
			this.carIDString = "�� ��ȣ";
			this.nameString = "������ �̸�";
			this.phoneNumString = "��ȭ��ȣ";
			this.carModel = "�¿���";
			this.isMember = false;
		}
		public Vehicle(String carIDString) {
			this.carIDString = carIDString;
			this.nameString = "�̸�";
			this.phoneNumString = "��ȭ��ȣ";
			this.carModel = "�¿���";
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
