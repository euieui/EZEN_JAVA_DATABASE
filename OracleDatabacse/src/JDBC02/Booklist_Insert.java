package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Booklist_Insert {

	public static void main(String[] args) {
		// Booklist 테이블에 레코드를 추가하는 코드를 작성하세요
		// booknum 에는 sequence 를 사용해주세요
		
		Connection con =null;
		PreparedStatement pstmt = null; 
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("첵 제목을 입력하세요: ");
			String subject = sc.nextLine();
			System.out.print("출판연도를 입력하세요: ");
			int makeyear = sc.nextInt();
			System.out.print("책 가격을 입력하세요: ");
			int inprice = sc.nextInt();
			System.out.print("대여 가격을 입력하세요: ");
			int rentprice = sc.nextInt();
			System.out.print("등급을 입력하세요: ");
			String grade = sc.nextLine();
			
			String sql = "insert into booklist values (book_seq.nextVal,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setInt(2, makeyear);
			pstmt.setInt(3, inprice);
			pstmt.setInt(4, rentprice);
			pstmt.setString(5, grade);
			
			int result = pstmt.executeUpdate();
			if (result == 1) System.out.println("레코드 추가 성공");
			else System.out.println("레코드 추가 실패");
		
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
