package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Booklist_Select {

	public static void main(String[] args) {
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id="scott";
		String pw ="tiger";
		
		Connection con =null; 
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,id,pw);
			
			String sql = "select * from booklist";
			
			pstmt = con.prepareStatement(sql);
			// pstmt = con.prepareStatement("select * from custmomer");
			
			// pstmt 에 담겨진 SQL 명령 실행하고 그 결과를 rs 에 저장합니다
			rs = pstmt.executeQuery();
			
			// rs.next() : 최초 실행은 객체의 시작부분(데이터 없는 곳)에서 첫번째 레코드로 이동하는 메서드
			// 그 다음부터는 다음 레코드로 이동. 
			// re.next() 메서드가 실행될때 다음 레코드가 있으면 true, 없으면 false를 리턴
			System.out.println("booknum\tmakeyear\tinprice\trentprice\tgrade\t\tsubject");
			System.out.println("--------------------------------------------------");
			while(rs.next()) { //결과의 처음부터 끝까지 반복 실행 : 레코드 단위로 반복 실행
				// rs.getInt() : number 형 필드값을 추출하는 메서드. 괄호안에 필드이름을 정확히 써야합니다.
				// 필드명에 오타가 있거나 안맞으면 부적합한 열이름 이라는 에러가 발생합니다.
				int booknum = rs.getInt("booknum");
				// rs.getString() : varchar2 형 필드값을 추출하는 메서드
				// 모든 자료형에 대해 get~() 메서드가 모두 존재합니다
				String subject = rs.getString("subject");
				int makeyear = rs.getInt("makeyear");
				int inprice = rs.getInt("inprice");
				int rentprice = rs.getInt("rentprice");
				String grade = rs.getString("grade");
				System.out.printf("%d\t\t%d\t\t%d\t%d\t\t%s\t\t%s\t\n",booknum,makeyear,inprice,rentprice,grade,subject);
			}
			
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
			if( rs != null) rs.close();
		} catch (SQLException e) {	
			e.printStackTrace();
		}

	}

}
