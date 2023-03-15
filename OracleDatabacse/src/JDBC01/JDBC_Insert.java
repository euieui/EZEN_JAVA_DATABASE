package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC_Insert {

	public static void main(String[] args) {
	
		Connection con =null;
		PreparedStatement pstmt = null; 
		// insert 명령의 경우 결과값이 따로 없어서 ResultSet 은 사용하지 않습니다.
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			// System.out.println("연결 성공");
			Scanner sc = new Scanner(System.in);
			
			System.out.print("저장할 번호를 입력하세요: ");
			String num = sc.nextLine();
			System.out.print("이름을 입력하세요: ");
			String name = sc.nextLine();
			System.out.print("메일을 입력하세요: ");
			String email = sc.nextLine();
			System.out.print("전화번호를 입력하세요: ");
			String tel = sc.nextLine();
			
			// insert into customer  values(5,'김하나','abc3@abc.com','010-3234-3234')
			// String sql = "insert into customer values ("+num+",'"+name+"', '"+email+"', '" +tel+"')"; //따옴표 굉장히 중요하다
			// 그래도 다행이다 이건 옜날방식 
			
			// 얘가 요즘 방식
			String sql = "insert into customer values (?,?,?,?)";
			// SQL 명령을 먼저 장착하고
			pstmt = con.prepareStatement(sql);
			// ? 의 순서 맞춰서 해당 데이터를 세팅
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.setString(2, name);
			pstmt.setString(3,email);
			pstmt.setString(4, tel);
			
			// SQL select 명령만 executeQuery 를 사용하고, 나머지는 exeuteUpdate 메서드를 사용합니다
			// executeUpdate의 결과는 sql 명령이 정상 동작했을때 1, 실패했을때0 이 리턴됩니다
			
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
