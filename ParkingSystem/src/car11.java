//A Simple Java program for a Car Park System (Code) Part 2

//Part 1 (PSEUDOCODE )

//CODE

import java.util.*;
import java.util.LinkedList;

//public class car11 {
//	public static void main(String args[]) {
//
//		// ���Ḯ��Ʈ, Ÿ�Լ��� : vehicle ��ü�� ��� ����
//		LinkedList<vehicle> list1 = new LinkedList<vehicle>(); // ���� ������ �ڵ��� ����Ʈ
//		LinkedList<vehicle> list2 = new LinkedList<vehicle>(); // ��� ����Ʈ
//
//		// ������� ���� ��ĳ�� ��ü ���
//		Scanner in = new Scanner(System.in);
//		
//		// ���� �ȳ� ���� ���
//		System.out.println("");
//		System.out.println("\t" + "	*****WELCOME TO COMFORT CAR PARKING *****");
//		System.out.println("\t" + "  NAME:ABM GROUPS ");
//		System.out.println("\t" + "  Reg#: 151CS");
//		System.out.println("\t" + "  RollNo: 202,125,198 ");
//		System.out.println("");
//		System.out.println("\t" + "Car Park contains : " + list1); // ���� ������ �ڵ��� ���
//		
//		int inputNum1 = 0; // �޴� ������ �Է¹޾� �����ϴ� ����
//		int carNum1; 
//		int Num2;
//		
//		// ���� ó��
//		try {
//			// ���� �޴��� �ݺ��Ͽ� ����.
//			while (true) {
//				// ������ �� �ִ� �޴��� ����Ѵ�. 
//				System.out.println("1.Enter Garage\n2.Exit from garage\n3.Display Car List\n4.Exit menu");
//				inputNum1 = in.nextInt(); // ������ �Է¹޾� ������ �ִ´�. 
//				
//				
//				// �޴����� �Է¹��� ���ڿ� ����
//				switch (inputNum1) {
//				// 1�� : Enter Garage
//				case 1:
//					if (list1.size() <= 9) { // 10����� ���� ����
//						int numOfCars = 0; // �� ��ȣ
//						vehicle vehicleID = null; // ����ȣ   
//
//						// ���� �����忡 ������ ������ ���̵� 
//						for (int h = 0; h < list1.size(); h++) {
//							vehicleID = list1.get(h); // ����Ʈ�� ����ִ� vehicle ��ü�� vehicleID�� �ִ´�. 
//							numOfCars = vehicleID.no; // ������ ��ȣ�� ��Ÿ���� �ش� ��ü�� no �ʵ带 ������ �ִ´�.
//							// ���� ������ ���� �Ǿ��ִ� ������ ��ȣ�� ����Ѵ�.
//							System.out.println("Car numbers in the Car Park now " + numOfCars);
//
//						}
//						
//						// �� ���� �������� ���Ҵ��� �˷��ִ� ���� ���
//						System.out.println("");
//						System.out.println("Car Park has another : " + (10 - numOfCars) + " vacansies"); 
//						
//						// ���� ������ ���� ������ ��ȣ�� �˷��ִ� ���� ���
//						System.out.println("\t" + "...Please come next car..."); 
//						System.out.println(""); 
//						System.out.println("Car number " + (list1.size() + 1) + " is the next to enter garage");
//						System.out.print("Enter the car number given above ");
//						carNum1 = in.nextInt(); // �Է¹޴´�. 
//
//						if ((list1.size() + 1) == carNum1) { // �ڵ��� ��ȣ�� ��Ȯ�� �Է¹޾Ҵٸ�
//							list1.add(new vehicle(carNum1));
//						} else {
//							System.out.println("Please enter correct car number "); 
//						}
//
//					} else { // ���� ���� 10�븦 �Ѱ�ٸ�
//						System.out.println("");
//						System.out.println("Sorry!!!!");
//						System.out.println("No parking space available.Please wait until a vacancy comes");
//
//						int inputNum2 = 0; // �������, ���� ���� ����� �����ϴ� �ʵ�
//
//						for (int h = 0; h < list2.size(); h++) { // ������� ������ �����
//							System.out.print(list2.get(h).no + " "); // ���� ��ȣ ���
//						}
//						System.out.println("  Cars are waiting to enter garage");
//						System.out.println("Would you like to enter waiting list???");
//						System.out.println("1.yes\n2.no");
//						inputNum2 = in.nextInt(); // ����� �Է¹޾� �ʵ忡 ����
//
//						switch (inputNum2) {
//						// Yes�� ������ ���
//						case 1:
//							System.out.println("Car number " + (list2.size() + 11) + " is the next to enter garage");
//							System.out.print("Enter the car number given above ");
//							int waitingCarNum;
//							waitingCarNum = in.nextInt();
//							
//							// ��Ȯ�ϰ� �Է��ߴٸ�
//							if ((list2.size() + 11) == waitingCarNum) {
//								for (int i = 0; i < list2.size() - 1; i++) {
//									vehicle temp = list2.get(i);
//									System.out.println(temp.no); // ������� ������ ��ȣ�� �����
//								}
//								
//								// ������� ���� ����Ʈ�� �Է��ϴ� ��ȣ�� ���� �߰�
//								list2.add(new vehicle(waitingCarNum));
//								System.out.println("Waiting List ");
//								for (int h = 0; h < list2.size() - 1; h++) {
//									System.out.print(list2.get(h).no + " ");
//								}
//								System.out.println("");
//
//							} else {
//								System.out.println("Please enter correct car number ");
//							}
//							break;
//						
//						// No�� ������ ���
//						case 2:
//							System.out.println("Thank you");
//							break;
// 
//						}
//						break;
//
//					}
//					break;
//					
//					
//				// 2�� : Exit from garage ������Ŵ
//				case 2:
//					System.out.println("1.Depart from main garage\n2.Depart From Waiting List");
//					int inputNum3 = in.nextInt(); // ����� ������ �Է¹޴� ����
//
//					// ����ó����
//					try {
//						switch (inputNum3) { // �Է¹��� ������
//						// Depart from main garage ���
//						case 1:
//							// ������ �ڵ����� �ϳ��� ���ٸ�
//							if (list1.size() == 0) {
//								System.out.println("Garage is empty. If you wish you can Enter your car now");
//							} else { // ������ �ڵ����� �ִٸ�
//								System.out.println("Car numbers in the Car park.Choose yours "); // �����忡 �ִ� ��ȣ�Դϴ�. ����� ���� ������.
//								vehicle qsa = null; // ����Ʈ�� ��ü�� ���� ����
//								int ssd = 0; // qsa�� ���� ��ȣ�� ���� ����
//								
//								for (int h = 0; h < list1.size(); h++) { // ���� ���� ����Ʈ�� �ִ� ������ ��� ����
//									qsa = list1.get(h);
//									ssd = qsa.no;
//									System.out.println("in Park " + ssd); 
//								}
//								
//								// ������ �� ��ȣ �Է¹ޱ�
//								System.out.println("Enter the number of your car");
//								int RemoveCar = 0;
//								RemoveCar = in.nextInt();
//								int sd = 0; // 
//								int which = RemoveCar; // �����ϰ��� �ϴ� ���� ��ȣ
//
//								for (int h = 0; h < list1.size(); h++) {
//									vehicle qa = list1.get(h); 
//									if (qa.no != which) { // �ش��ϴ� ��ȣ�� �ƴ϶��
//										qa = list1.get(h);
//										continue; // �Ʒ��� �������� �ʰ� ���� ���� �ݺ����� �����Ѵ�. 
//									}
//									// ����Ʈ�� h��° ���� ��ȣ�� �Է¹��� �� �̶��?, ���� h�� ������ ���� ��ȣ
//									else { 
//										vehicle kl = null;
//										// �Ʒ������� mvs�� 2�� ����
//										for (int u = 0; u < list1.size() - 1; u++) { // ������ �ε����� Ž������ �ʰ� ���ڳ�
//											kl = list1.get(u);
//											if (kl.no != which) { // �ش��ϴ� ��ȣ�� �ƴ϶��
//												kl.mvs += 2; // �̵� Ƚ�� 2�� �߰�?
//											} else // �ش��ϴ� ��ȣ��� �ݺ��� ������.
//												break; 
//										}
//										// ����������  mvs�� 1�� ����
//										for (int v = list1.size(); (list1.get(h).no != which); v--) {
//											kl.mvs += 1;
//										}
//										
//										// �ش� ������ mvs�� 1�� ���Ͽ� �����
//										System.out.println("moves " + list1.get(h).incmoves(1));
//										list1.remove(h); // �ε��� h ��ȯ �� ����
//										break;
//									}
//
//
//
//								}
//								
//								// ������� ������ �ִٸ� 
//								if (list2.size() > 0) {
//									System.out.println("So car number " + list2.getFirst() + "  to car park:"); // ����Ʈ�� ù ��Ҹ� ��ȯ
//
//									list1.add(new vehicle(list2.getFirst().no)); // ��� ����Ʈ ù��° ��ü�� ������ȣ�� �״�� ���� ���ο� ��ü�� �����Ͽ� ����Ʈ�� �߰��Ѵ�.
//									list2.remove(0); // ��� ����Ʈ�� ù��° �ε��� ����
//
//									System.out.println("New car list in car park : " + list1); // ���ο� ����Ʈ ���¸� �����ش�. 
//									System.out.println("");
//								} else { // ������� ������ ���ٸ� 
//									System.out.println("No cars in waiting list to enter garage");
//								}
//
//							}
//							break; // ����ġ�� break
//
//						// Depart From Waiting List ���
//						case 2:
//							if (list2.size() == 0) { // ��� ����Ʈ�� ũ�Ⱑ 0�� ���
//								System.out.println("There is no cars waiting in the list");
//							} else {
//								System.out.println("Cars in waiting list" + list2);
//								System.out.print("Enter your car number");
//								int removeWaitCar = in.nextInt(); 
//							}
//							break;
//						}
//					}
//					// ����
//					catch (Exception e) {
//						System.out.println("You have entered wrong Index number.please check");
//					}
//					break;
//
//				// Display Car List
//				case 3: 
//					System.out.println("What do you want to see?");
//
//					System.out.println("1.Main garage\n2.Waiting list");
//					int displayNum = in.nextInt();
//					
//					try {
//						switch (displayNum) {
//						// Main garage
//						case 1:
//							vehicle carID1 = null;
//							int carList1 = 0;
//
//							for (int h = 0; h < list1.size(); h++) {
//								carID1 = list1.get(h);
//								carList1 = carID1.no;
//								System.out.println("Park now " + carList1);
//							}
//							break;
//						
//						// Waiting List
//						case 2:
//							vehicle carID2 = null;
//							int carList2 = 0;
//
//							for (int h1 = 0; h1 < list2.size(); h1++) {
//								carID2 = list2.get(h1);
//								carList2 = carID2.no;
//								System.out.println(carList2 + " is in the Waiting list now");
//
//							}
//							break;
//						}
//					} catch (Exception e) {
//						System.out.println("You have enter wrong option number.please check again");
//					}
//					break;
//
//				// Exit Menu
//				case 4:
//					System.out.println("Have a nice day");
//
//				default:
//					System.exit(0);
//
//				}
//
//			}
//		} catch (Exception e) {
//			System.out.println(e + "You may have entered a wrong charactor.please check");
//		}
//
//	}
//
// }

class vehicle {

	int no; // �� ��ȣ (== ������ ��ȣ)
	int mvs; // moves

	public vehicle(int abc) {
		no = abc;
		mvs = 0;
	}

	public int incmoves(int x) {
		return (mvs + x);
	}

}