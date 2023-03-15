package JDBC03;

import java.util.ArrayList;
import java.util.Scanner;

public class Book_Driver {

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
			
			if(choice.equals("5")) break;
			
			
			
			switch(choice) {
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

	}

	private static void delete() {
		// 삭제할 번호를 입력 받고, 그 번호를 전달인수로 해서 dao에 delete() 메서드를 호출합니다.
		Scanner sc = new Scanner(System.in);
		Book_Dao bdao = new Book_Dao();
		
		System.out.print("도서 번호를 입력하세요 : ");
		String num = sc.nextLine();
		int result = bdao.delete(num);
		
		
		if(result == 1) System.out.println("레코드 삭제 성공");
		else System.out.println("레코드 삭제 실패");
	}

	private static void update() {
		Book_Dao bdao = new Book_Dao();
		Scanner sc = new Scanner(System.in);
		
		// 수정할 도서번호를 입력받습니다.
		
		String booknum;
		while(true) {
			System.out.print("수정할 도서번호를 입력하세요 : ");
			booknum = sc.nextLine();
			if(booknum.equals("")) System.out.println("도서번호 입력은 필수 입니다."); 
			else break;
		}
		// 입력받은 도서 번호로 도서를 조회해서 oldDto객체 에 저장해둡니다. 원본 데이터의 로딩
		Book_Dto oldDto = bdao.getDto(booknum);
		// 조회한 도서번호에 해당 도서가 없습니다. 라고 출력하고 실행종료
		if( oldDto == null) {
			System.out.println("입력하신 도서번호로 조회된 도서가 없습니다.");
			return;
		}
		Book_Dto newDto = new Book_Dto();
		// dao의 update 메서드에 전달할(수정할 내용이 담길)객체에 입력 받은 도서번호를 저장합니다.
		newDto.setBooknum(Integer.parseInt(booknum));
		
		// 수정할 항목들을 하나하나 전부다 입력 받습니다. newDto에 저장합니다.
		// 다만 입력받은 내용이 없다면(빈칸이라면, 엔터만 눌렀다면)
		// 새로 고쳐질 데이터가 저장될 객체에 원본데이터로 해당 필드를 대체합니다
		// 도서제목
		System.out.printf("수정할 도서의 제목을 입력하세요 : ");
		String subject = sc.nextLine();
		if(subject.equals("")) newDto.setSubject(oldDto.getSubject());
		else newDto.setSubject(subject);
		
		// 출판년도를 입력 받습니다.
		System.out.printf("수정할 도서의 제작년도를 입력하세요 : ");
		String makeyear = sc.nextLine();
		if(makeyear.equals("")) newDto.setMakeyear(oldDto.getMakeyear());
		else newDto.setMakeyear(Integer.parseInt(makeyear));
		
		// 입고 가격
		System.out.printf("수정할 도서의 입고가격을 입력하세요 : ");
		String inprice = sc.nextLine();
		if(inprice.equals("")) newDto.setInprice(oldDto.getInprice());
		else newDto.setInprice(Integer.parseInt(inprice));
		
		// 대여가격
		System.out.printf("수정할 도서의 대여가격를 입력하세요 : ");
		String rentprice = sc.nextLine();
		if(rentprice.equals("")) newDto.setRentprice(oldDto.getRentprice());
		else newDto.setRentprice(Integer.parseInt(rentprice));
		
		// 등급
		System.out.printf("수정할 도서의 등급을 입력하세요 : ");
		String grade = sc.nextLine();
		if(grade.equals("")) newDto.setGrade(oldDto.getGrade());
		else newDto.setGrade(grade);
		
		// 그리고 bdao.update (newDTo); 를 실행해서 수정합니다.
		bdao.update(newDto);
		
	}

	private static void select() {
		Book_Dao bdao = new Book_Dao();
		// 데이터 열람명령이 입력되면, 
		// Book_Dao 클래스의 객체를 만들고, 그안의 멤버메서드 중   ///---- 객체는 switch 밖으로 꺼냄
		// 데이터를 모두 조회해서 리턴해줄수 있는 메서드를 호출합니다
		// 그리고 그 리턴된 결과를 화면에 출력합니다.
		
		ArrayList <Book_Dto> list = bdao.selectAll();
		
		//리턴된 list로 화면에 출력
		System.out.println("도서번호 \t 출판년도 \t 입고가격 \t 출고가격 \t 등급 \t 제목");
		System.out.println("-------------------------------------------------------------------");
		for(Book_Dto dto : list) { // list에 있는 Book_Dto 객체가 dto 변수에 하나씩 담기며 반복실행
			//dto.getBooknum() : 도서번호
			System.out.printf("%d \t %d \t %d \t %d \t %s \t %s\n",dto.getBooknum(),dto.getMakeyear(),dto.getInprice(),dto.getRentprice()
					,dto.getGrade(),dto.getSubject());
			
		}
		
	}

	private static void insert() {
		Scanner sc = new Scanner(System.in);
		Book_Dao bdao = new Book_Dao();
		Book_Dto bdto = new Book_Dto();
		System.out.print("도서제목을 입력하세요 : ");
		String subject = sc.nextLine();
		bdto.setSubject(subject);
		System.out.print("출판년도를 입력하세요 : ");
		bdto.setMakeyear(Integer.parseInt(sc.nextLine()));
		System.out.print("입고가격을 입력하세요 :");
		bdto.setInprice(Integer.parseInt(sc.nextLine()));
		System.out.print("대여가격을 입력하세요 : ");
		bdto.setRentprice(Integer.parseInt(sc.nextLine()));
		System.out.print("등급을 입력하세요 (all, 13, 18) : ");
		bdto.setGrade(sc.nextLine());
		int result = bdao.insert(bdto);
		if(result == 1) System.out.println("레코드 추가 성공");
		else System.out.println("레코드 추가 실패");
		
	}

}
