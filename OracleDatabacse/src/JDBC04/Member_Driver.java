package JDBC04;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public class Member_Driver {

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
	
	// Dto 부터 제작. 
	private static void delete() {
		Member_Dao mdao = new Member_Dao();
		Member_Dto mdto = new Member_Dto();
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 번호를 입력해주세요 : ");
		mdto.setMembernum(Integer.parseInt(sc.nextLine()));
		mdao.delete(mdto);
		
		
	}

	private static void update() {
		Member_Dao mdao = new Member_Dao();
		Scanner sc = new Scanner(System.in);
		// 회원 번호 입력
		String membernum;
		while(true) {
			System.out.print("수정할 멤버번호를 입력하세요 : ");
			membernum = sc.nextLine();
			if(membernum.equals("")) System.out.println("멤버 번호 입력은 필수 입니다.");
			else break;
		}
		// 회원 번호로 회원 조회 -> oldDto 에 저장
		Member_Dto oldDto = mdao.getDto(membernum);
		// 조회결과가 없으면 메서드 종료
		if(oldDto == null) {
			System.out.println("입력하신 멤버번호로 조회된 멤버가 없습니다.");
			return;
		}
		// 조회 결과가 있으면 newDto 에 회원 번호 저장
		Member_Dto newDto = new Member_Dto();
		newDto.setMembernum(Integer.parseInt(membernum));
		// 이름 입력
		System.out.print("수정할 멤버의 이름을 입력하세요 : ");
		String name = sc.nextLine();
		if(name.equals("")) newDto.setName(oldDto.getName());
		else newDto.setName(name);
		// 전화번호 입력
		System.out.print("수정할 멤버의 전화번호를 입력하세요 : ");
		String phone = sc.nextLine();
		if (phone.equals("")) newDto.setPhone(oldDto.getPhone());
		else newDto.setPhone(phone);
		// 생일 입력 - 나이 자동 계싼 수정
		
		System.out.print("수정할 멤버의 생년월일를 입력하세요(YYYY-MM-DD) : ");
		String birth = sc.nextLine();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
															
		if(birth.equals("")) newDto.setBirth(oldDto.getBirth());
		else {
			java.util.Date d = null;
			while(true) {
				try {
					d = sdf.parse(birth);
					
					break;
				} catch (ParseException e) {
					System.out.print("양식에 맞게 생일을 다시 입력하세요 (YYYY-MM-DD) : ");
					birth = sc.nextLine();
					e.printStackTrace();
				}
				
			}
			java.sql.Date birth2 = new java.sql.Date(d.getTime());
			newDto.setBirth(birth2);
			Calendar c =Calendar.getInstance();
			Calendar today = Calendar.getInstance();
			c.setTime(d);
			int age = today.get(Calendar.YEAR) - c.get(Calendar.YEAR) +1;
			newDto.setAge(age);
		}
		
		// 성별 입력
		System.out.print("수정할 멤버의 성별을 입력하세요 (F/M) : ");
		String gender=sc.nextLine();
		if(gender.equals("")) newDto.setGender(oldDto.getGender());
		else newDto.setGender(gender);
		// bpoint 입력
		System.out.print("수정할 멤버의 포인트를 입력하세요 : ");
		String bpoint = sc.nextLine();
		if(bpoint.equals("")) newDto.setBpoint(oldDto.getBpoint());
		else newDto.setBpoint(Integer.parseInt(bpoint));
		// 모든 항목은 입력된 값이 있을때만 수정하며, 필드값 입력중 입력된 값이 없으면 이전 값을 유지합니다.
		mdao.update(newDto);
		
	}

	private static void select() {
		Member_Dao mdao = new Member_Dao();
		
		ArrayList <Member_Dto> list = mdao.selectAll();
		System.out.println("번호 \t 이름 \t\t 전화번호 \t\t 생일 \t\t\t 포인트 \t 가입날짜 \t\t 성별 \t 나이");
		System.out.println("-------------------------------------------------------------------------------------");
		for ( Member_Dto mdto : list) {
			System.out.printf("%d \t %s \t %s \t %s \t %d \t\t %s \t %s \t %d\n",mdto.getMembernum(),mdto.getName(),mdto.getPhone(),mdto.getBirth(),mdto.getBpoint(),mdto.getJoindate(),mdto.getGender(),mdto.getAge());
		}
		
		
	}

	private static void insert() {
		Member_Dao mdao = new Member_Dao();
		Scanner sc = new Scanner(System.in);
		
		Member_Dto mdto = new Member_Dto();
		// 회원번호는 Sequence 이용
		// 이름 입력
		System.out.print("이름을 입력하세요 : ");
		mdto.setName(sc.nextLine());
		// 전화번호 입력
		System.out.print("전화번호를 입력하세요(000-0000-0000) : ");
		mdto.setPhone(sc.nextLine());
		// 생일 입력 - java.util.date()형식의 입력 후 java.sql.date()로 변환이 필요합니다
		// java.util.Date()의 입력을 위해선 SimpleDateFormat 의 parse 루틴이 필요합니다.
		System.out.print("생일을 입력하세요(YYYY-MM-DD) : ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d =null;
		
		while(true){
			try { // 문자로 입력받은 날짜데이터를 날짜데이터로 변환 -> 날짜변수에 저장
				d = sdf.parse(sc.nextLine());
				break;
			} catch (ParseException e) {
				System.out.print("양식에 맞게 생일을 다시 입력해주세요 (YYYY-MM-DD) : ");
				
				e.printStackTrace();
			} 
		}
		// java.util.DAte을 java.sql.Date 로 변환
		// d.getTime()을 java.sql.Date 의 생성자의 전달인수로 넣습니다.
		java.sql.Date birth = new java.sql.Date(d.getTime());
		mdto.setBirth(birth);
		
		System.out.print("성별을 입력하세요(F/M) : ");
		mdto.setGender(sc.nextLine());
	
		// 나이는 계싼해라
		Calendar c = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		c.setTime(d); // c.setTime(birth); Date 자료를 Calendar 자료로 변환
		int age = today.get(Calendar.YEAR) - c.get(Calendar.YEAR) + 1;
		mdto.setAge(age);
		mdao.insert(mdto);
		
		
	}

}
