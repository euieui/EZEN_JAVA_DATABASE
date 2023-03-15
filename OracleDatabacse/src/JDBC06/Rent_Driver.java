package JDBC06;

import java.util.ArrayList;
import java.util.Scanner;

public class Rent_Driver {

	public static void main(String[] args) {
		while(true){
			Scanner sc = new Scanner(System.in);
			System.out.println("\n*** 메뉴 선택 ***");
			System.out.printf("1.데이터추가.");
			System.out.printf(" 2.데이터열람.");
			System.out.printf(" 3.데이터수정.");
			System.out.printf(" 4.데이터삭제.");
			System.out.printf(" 5.프로그램종료.");
			System.out.print(">>메뉴선택 : ");
			String choice = sc.nextLine();
			// ### Rent_Dao를 공유해서 쓰는 방법 #1   ---- 직접 한번 만들어 볼까?
			// Driver Class 에서 객체를 생성하고 각 메서드에 전달인수로 전달
			
			if(choice.equals("5")) break;
			// Rent_Dao rdao = new Rent_Dao();
			switch(choice) {
				// case "1" : insert(rdao); break; 
				case "1" :
					insert();
					break;
				case "2" :
					select();
					break;
				case "3" :
					update();
					break;
				case "4" :
					delete();
					break;
				default :	
					System.out.println("메뉴 선택이 잘못되었습니다.");
			}
		}
		System.out.println("프로그램 종료");
	}

	private static void update() {
		// #### Rent_Dao를 공유해서 쓰는 방법 #2
		// Rent_Dao 를 Singleton 방식으로 구현하여 쓰는 방법
				// Rent_Dao rdao = new Rent_Dao(); // 에러
		Rent_Dao rdao = Rent_Dao.getInstance();
		Rent_Dto newDto = new Rent_Dto();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("수정할 대여건의 numseq를 입력하세요 : ");
		String numseq;
		while(true) {
			numseq = sc.nextLine();
			if(numseq.equals("")) System.out.print("numseq 입력은 필수입니다.");
			else break;
		}
		Rent_Dto oldDto = rdao.getDto(Integer.parseInt(numseq));
		if(oldDto == null) {
			System.out.print("입력한 numseq의 대여건이 없습니다.");
			return;
		}
		newDto.setNumseq(Integer.parseInt(numseq));
		
		System.out.print("수정할 대여날짜를 입력하세요(YYYYMMDD) : ");
		String rentdate = sc.nextLine();
		if(rentdate.equals("")) newDto.setRentdate(oldDto.getRentdate());
		else newDto.setRentdate(rentdate);
		
		
		System.out.print("수정할 도서번호를 입력하세요 : ");
		String booknum;
		while(true) {
			booknum = sc.nextLine(); // 도서번호를 입력
			if(booknum.equals("")) { //입력한 도서번호가 그냥 엔터라면, 이전 값으로 수정할 데이터를 대체하고 반복 중지
				newDto.setBooknum(oldDto.getBooknum());
				break;
			}else {
				String bn = rdao.confirmBn(booknum); // 입력한 도서번호로 조회
				if(bn==null) { // 조회 결과가 없으면 
					System.out.println("해당 도서가 없습니다. 다시입력해주세요");
				} else { // 조회 결과가 있으면 현재 입력된 도서 번호로 수정할 dto 에 저장
					newDto.setBooknum(Integer.parseInt(booknum));
					break;
				}
			}
		}
		
	
		System.out.print("수정할 회원번호를 입력하세요 : ");
		String membernum;
		while(true) {
			membernum = sc.nextLine();
			if(membernum.equals("")) {
				newDto.setMembernum(oldDto.getMembernum());
				break;
			}else {
				String mn = rdao.confrimMn(membernum);
				if(mn==null) {
					System.out.println("해당 회원이 없습니다. 다시입력해주세요");
				}else {
					newDto.setMembernum(Integer.parseInt(membernum));
					break;
				}
			}
		}
		
		
		
		System.out.print("수정할 할인금액을 입력하세요 : ");
		String discount = sc.nextLine();
		if(discount.equals("")) newDto.setDiscount(oldDto.getDiscount());
		else newDto.setDiscount(Integer.parseInt(discount));
		
		rdao.update(newDto);
		
		
		
		
		
		
		
		
	}

	private static void delete() {
		// #### Rent_Dao를 공유해서 쓰는 방법 #2
		// Rent_Dao 를 Singleton 방식으로 구현하여 쓰는 방법
		// Rent_Dao rdao = new Rent_Dao(); // 에러
		Rent_Dao rdao = Rent_Dao.getInstance();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 numseq를 입력해주세요 : ");
		String numseq;
		while(true) {
			numseq = sc.nextLine();
			if(numseq.equals("")) System.out.print("numseq 입력은 필수입니다.");
			else break;
		}
		Rent_Dto oldDto = rdao.getDto(Integer.parseInt(numseq));
		if(oldDto == null) {
			System.out.print("입력한 numseq의 대여건이 없습니다.");
			return;
		}
		rdao.delete(Integer.parseInt(numseq));
		
		
		
	}

	private static void select() {
		// #### Rent_Dao를 공유해서 쓰는 방법 #2
		// Rent_Dao 를 Singleton 방식으로 구현하여 쓰는 방법
		// Rent_Dao rdao = new Rent_Dao(); // 에러
		Rent_Dao rdao = Rent_Dao.getInstance();
		ArrayList<Rent_Dto> list = rdao.selectAll();
		System.out.println("날짜 \t\t\t 순번 \t 도서번호 \t 회원번호 \t 할인금액");
		System.out.println("-------------------------------------------------------------------");
		for(Rent_Dto rdto: list) {
			System.out.printf("%s \t %d \t %s \t\t %s \t\t %d\n", rdto.getRentdate(),rdto.getNumseq(),rdto.getBooknum(),rdto.getMembernum(),rdto.getDiscount());
		}
		
		
	}

	private static void insert() {
		// #### Rent_Dao를 공유해서 쓰는 방법 #2
		// Rent_Dao 를 Singleton 방식으로 구현하여 쓰는 방법
		// Rent_Dao rdao = new Rent_Dao(); // 에러
		Rent_Dao rdao = Rent_Dao.getInstance(); // 대부분 dao 는 singleton 을 만든다 접속자가 많으니  과부하가 생기기 때문에
		Rent_Dto rdto = new Rent_Dto();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("대여 날짜를 입력하세요(YYYYMMDD) : ");
		rdto.setRentdate(sc.nextLine());
		System.out.print("대여하는 도서번호를 입력하세요 : ");
		rdto.setBooknum(Integer.parseInt(sc.nextLine()));
		System.out.print("대여한 사람의 회원번호를 입력하세요 : ");
		rdto.setMembernum(Integer.parseInt(sc.nextLine()));
		System.out.print("할인 금액을 입력하세요 : ");
		rdto.setDiscount(Integer.parseInt(sc.nextLine()));
		
		rdao.insert(rdto);
		
		// rentdate 는 YYYYMMDD(String) 로 입력 받아서 dto 를 통해 dao로 전달합니다.
		// numseq는 시퀀스를 사용합니다
		// 나머지는 적절하게 입력 받아서 레코드를 추가합니다.
		
	}

	// private static void insert(Rent_Dao rdao) {} // Rent_Dao를 공유해서 쓰는 방법 #1

}
