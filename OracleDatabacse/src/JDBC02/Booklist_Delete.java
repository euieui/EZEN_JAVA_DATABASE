package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Booklist_Delete {

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
			System.out.print("삭제할 수정할 도서의 번호를 입력하세요: ");
			String num = sc.nextLine();
			
			String sql = "";
			sql = "Delete from booklist where booknum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  Integer.parseInt(num));
				
				
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
