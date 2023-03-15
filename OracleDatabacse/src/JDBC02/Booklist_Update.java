package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Booklist_Update {

	public static void main(String[] args) {
		Connection con =null;
		PreparedStatement pstmt = null; 
		
		// 첫번째 수정할 도서의 도서번호 입력
		// 두번째 수정할 도서의 항목(출판연도 , 입고 가격 대여가격, 등급)
		// 네[가지중 하나를 입력 받고 해당 내용을 수정하는 코드를 작성하세요
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			
			Scanner sc = new Scanner(System.in);
			System.out.print("수정할 도서의 번호를 입력하세요: ");
			String num = sc.nextLine();
			
			System.out.print("수정할 항목을 선택하세요. 1.출판연도 2.입고가격 3.대여가격 4.등급");
			String input =sc.nextLine();
			
			String sql = "";
			switch(input) {
				case "1":
					System.out.println("수정할 출판연도를 입력하세요: ");
					String makeyear = sc.nextLine();
					sql = "Update booklist set makeyear=? where booknum = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(makeyear));
					pstmt.setInt(2,  Integer.parseInt(num));
					break;
				case "2":
					System.out.println("수정할 입고가격를 입력하세요: ");
					String inprice = sc.nextLine();
					sql = "update booklist set inprice=? where booknum=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(inprice));
					pstmt.setInt(2, Integer.parseInt(num));
					break;
				case "3":
					System.out.println("수정할 대여가격를 입력하세요: ");
					String rentprice = sc.nextLine(); //--- nextInt 는 잘 안쓴다 오류를 줄이기 위해서
					sql = "update booklist set rentprice=? where booknum=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(rentprice));
					pstmt.setInt(2, Integer.parseInt(num));  //--- 그냥 setString 으로 넣어도 되나봄 ㅇㅇㅇ
					break;
				case "4":
					System.out.println("수정할 등급을 입력하세요: ");
					String grade = sc.nextLine();
					sql = "update booklist set grade=? where booknum=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, grade);
					pstmt.setInt(2, Integer.parseInt(num));
					break;
			}
			int result = pstmt.executeUpdate();
			if(result == 1) System.out.println("수정 성공");
			else System.out.println("수정 실패");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(con != null) con.close();
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
