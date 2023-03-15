package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC_Update {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con =null;
		PreparedStatement pstmt = null; 
		// insert 명령의 경우 결과값이 따로 없어서 ResultSet 은 사용하지 않습니다.
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			
			Scanner sc = new Scanner(System.in);
			System.out.print("수정할 회원의 번호를 입력하세요: ");
			String num = sc.nextLine();
			
			System.out.print("수정할 항목을 선택하세요. 1.이메일 2.전화번호");
			String input =sc.nextLine();
			
			String sql = "";
			switch(input) {
				case "1":
					System.out.println("수정할 이메일을 입력하세요: ");
					String email = sc.nextLine();
					sql = "Update customer set email=? where num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, email);
					pstmt.setInt(2,  Integer.parseInt(num));
					break;
				case "2":
					System.out.println("수정할 전화번호를 입력하세요: ");
					String tel = sc.nextLine();
					sql = "update customer set tel=? where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, tel);
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
