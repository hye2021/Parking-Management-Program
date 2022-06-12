//A Simple Java program for a Car Park System (Code) Part 2

//Part 1 (PSEUDOCODE )

//CODE

import java.util.*;
import java.util.LinkedList;

//public class car11 {
//	public static void main(String args[]) {
//
//		// 연결리스트, 타입설정 : vehicle 객체만 사용 가능
//		LinkedList<vehicle> list1 = new LinkedList<vehicle>(); // 현재 주차된 자동차 리스트
//		LinkedList<vehicle> list2 = new LinkedList<vehicle>(); // 대기 리스트
//
//		// 입출력을 위한 스캐너 객체 사용
//		Scanner in = new Scanner(System.in);
//		
//		// 시작 안내 문구 출력
//		System.out.println("");
//		System.out.println("\t" + "	*****WELCOME TO COMFORT CAR PARKING *****");
//		System.out.println("\t" + "  NAME:ABM GROUPS ");
//		System.out.println("\t" + "  Reg#: 151CS");
//		System.out.println("\t" + "  RollNo: 202,125,198 ");
//		System.out.println("");
//		System.out.println("\t" + "Car Park contains : " + list1); // 현재 주차된 자동차 출력
//		
//		int inputNum1 = 0; // 메뉴 선택을 입력받아 저장하는 변수
//		int carNum1; 
//		int Num2;
//		
//		// 예외 처리
//		try {
//			// 메인 메뉴를 반복하여 띄운다.
//			while (true) {
//				// 선택할 수 있는 메뉴를 출력한다. 
//				System.out.println("1.Enter Garage\n2.Exit from garage\n3.Display Car List\n4.Exit menu");
//				inputNum1 = in.nextInt(); // 정수를 입력받아 변수에 넣는다. 
//				
//				
//				// 메뉴에서 입력받은 숫자에 따라
//				switch (inputNum1) {
//				// 1번 : Enter Garage
//				case 1:
//					if (list1.size() <= 9) { // 10대까지 주차 가능
//						int numOfCars = 0; // 차 번호
//						vehicle vehicleID = null; // 차번호   
//
//						// 현재 주차장에 주차된 차량의 아이디를 
//						for (int h = 0; h < list1.size(); h++) {
//							vehicleID = list1.get(h); // 리스트에 들어있는 vehicle 객체를 vehicleID에 넣는다. 
//							numOfCars = vehicleID.no; // 주차장 번호를 나타내는 해당 객체의 no 필드를 변수에 넣는다.
//							// 현재 차량이 주차 되어있는 주차장 번호를 출력한다.
//							System.out.println("Car numbers in the Car Park now " + numOfCars);
//
//						}
//						
//						// 몇 개의 주차장이 남았는지 알려주는 문구 출력
//						System.out.println("");
//						System.out.println("Car Park has another : " + (10 - numOfCars) + " vacansies"); 
//						
//						// 다음 차량이 들어올 주차장 번호를 알려주는 문구 출력
//						System.out.println("\t" + "...Please come next car..."); 
//						System.out.println(""); 
//						System.out.println("Car number " + (list1.size() + 1) + " is the next to enter garage");
//						System.out.print("Enter the car number given above ");
//						carNum1 = in.nextInt(); // 입력받는다. 
//
//						if ((list1.size() + 1) == carNum1) { // 자동차 번호를 정확히 입력받았다면
//							list1.add(new vehicle(carNum1));
//						} else {
//							System.out.println("Please enter correct car number "); 
//						}
//
//					} else { // 주차 수가 10대를 넘겼다면
//						System.out.println("");
//						System.out.println("Sorry!!!!");
//						System.out.println("No parking space available.Please wait until a vacancy comes");
//
//						int inputNum2 = 0; // 대기할지, 말지 묻고 대답을 저장하는 필드
//
//						for (int h = 0; h < list2.size(); h++) { // 대기중인 차량을 출력함
//							System.out.print(list2.get(h).no + " "); // 차량 번호 출력
//						}
//						System.out.println("  Cars are waiting to enter garage");
//						System.out.println("Would you like to enter waiting list???");
//						System.out.println("1.yes\n2.no");
//						inputNum2 = in.nextInt(); // 대답을 입력받아 필드에 넣음
//
//						switch (inputNum2) {
//						// Yes를 눌렀을 경우
//						case 1:
//							System.out.println("Car number " + (list2.size() + 11) + " is the next to enter garage");
//							System.out.print("Enter the car number given above ");
//							int waitingCarNum;
//							waitingCarNum = in.nextInt();
//							
//							// 정확하게 입력했다면
//							if ((list2.size() + 11) == waitingCarNum) {
//								for (int i = 0; i < list2.size() - 1; i++) {
//									vehicle temp = list2.get(i);
//									System.out.println(temp.no); // 대기중인 차량의 번호를 출력함
//								}
//								
//								// 대기중인 차량 리스트에 입력하는 번호의 차량 추가
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
//						// No를 눌렀을 경우
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
//				// 2번 : Exit from garage 출차시킴
//				case 2:
//					System.out.println("1.Depart from main garage\n2.Depart From Waiting List");
//					int inputNum3 = in.nextInt(); // 사용자 반응을 입력받는 변수
//
//					// 예외처리문
//					try {
//						switch (inputNum3) { // 입력받은 내용이
//						// Depart from main garage 라면
//						case 1:
//							// 주차된 자동차가 하나도 없다면
//							if (list1.size() == 0) {
//								System.out.println("Garage is empty. If you wish you can Enter your car now");
//							} else { // 주차된 자동차가 있다면
//								System.out.println("Car numbers in the Car park.Choose yours "); // 주차장에 있는 번호입니다. 당신의 것을 고르세요.
//								vehicle qsa = null; // 리스트의 객체를 담을 변수
//								int ssd = 0; // qsa의 차량 번호를 담을 변수
//								
//								for (int h = 0; h < list1.size(); h++) { // 현재 주차 리스트에 있는 차량들 목록 나열
//									qsa = list1.get(h);
//									ssd = qsa.no;
//									System.out.println("in Park " + ssd); 
//								}
//								
//								// 출차할 차 번호 입력받기
//								System.out.println("Enter the number of your car");
//								int RemoveCar = 0;
//								RemoveCar = in.nextInt();
//								int sd = 0; // 
//								int which = RemoveCar; // 출차하고자 하는 차량 번호
//
//								for (int h = 0; h < list1.size(); h++) {
//									vehicle qa = list1.get(h); 
//									if (qa.no != which) { // 해당하는 번호가 아니라면
//										qa = list1.get(h);
//										continue; // 아래를 실행하지 않고 다음 조건 반복문을 실행한다. 
//									}
//									// 리스트의 h번째 차량 번호가 입력받은 값 이라면?, 현재 h는 출차할 차량 번호
//									else { 
//										vehicle kl = null;
//										// 아래서부터 mvs에 2를 더함
//										for (int u = 0; u < list1.size() - 1; u++) { // 마지막 인덱스는 탐색하지 않게 되자나
//											kl = list1.get(u);
//											if (kl.no != which) { // 해당하는 번호가 아니라면
//												kl.mvs += 2; // 이동 횟수 2번 추가?
//											} else // 해당하는 번호라면 반복을 끝낸다.
//												break; 
//										}
//										// 위에서부터  mvs에 1을 더함
//										for (int v = list1.size(); (list1.get(h).no != which); v--) {
//											kl.mvs += 1;
//										}
//										
//										// 해당 차량의 mvs에 1을 더하여 출력함
//										System.out.println("moves " + list1.get(h).incmoves(1));
//										list1.remove(h); // 인덱스 h 반환 후 제거
//										break;
//									}
//
//
//
//								}
//								
//								// 대기중인 차량이 있다면 
//								if (list2.size() > 0) {
//									System.out.println("So car number " + list2.getFirst() + "  to car park:"); // 리스트의 첫 요소를 반환
//
//									list1.add(new vehicle(list2.getFirst().no)); // 대기 리스트 첫번째 객체의 차량번호를 그대로 갖는 새로운 객체를 생성하여 리스트에 추가한다.
//									list2.remove(0); // 대기 리스트의 첫번째 인덱스 삭제
//
//									System.out.println("New car list in car park : " + list1); // 새로운 리스트 상태를 보여준다. 
//									System.out.println("");
//								} else { // 대기중인 차량이 없다면 
//									System.out.println("No cars in waiting list to enter garage");
//								}
//
//							}
//							break; // 스위치문 break
//
//						// Depart From Waiting List 라면
//						case 2:
//							if (list2.size() == 0) { // 대기 리스트의 크기가 0일 경우
//								System.out.println("There is no cars waiting in the list");
//							} else {
//								System.out.println("Cars in waiting list" + list2);
//								System.out.print("Enter your car number");
//								int removeWaitCar = in.nextInt(); 
//							}
//							break;
//						}
//					}
//					// 예외
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

	int no; // 차 번호 (== 주차장 번호)
	int mvs; // moves

	public vehicle(int abc) {
		no = abc;
		mvs = 0;
	}

	public int incmoves(int x) {
		return (mvs + x);
	}

}