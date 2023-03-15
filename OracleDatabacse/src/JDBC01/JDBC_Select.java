package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_Select {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id="scott";
		String pw ="tiger";
		
		Connection con =null; //연결을 위한 객체
		PreparedStatement pstmt = null; // con 에 SQL 실행해주는 객체
		ResultSet rs = null; // SQL 실행결과를 저장하는 객체
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,id,pw);
			// System.out.println("데이터베이스에 연결 성공했습니다.");
			
			// 데이터베이스 연결후에는 SQL 명령을 사용하기 위해 String 변수에 SQL 명령을 준비합니다
			// 데이터베이스에 제공되어질 명령이므로 STring 형으로 준비합니다
			String sql = "select * from customer";
			
			pstmt = con.prepareStatement(sql);
			// pstmt = con.prepareStatement("select * from custmomer");
			
			// pstmt 에 담겨진 SQL 명령 실행하고 그 결과를 rs 에 저장합니다
			rs = pstmt.executeQuery();
			
			// rs.next() : 최초 실행은 객체의 시작부분(데이터 없는 곳)에서 첫번째 레코드로 이동하는 메서드
			// 그 다음부터는 다음 레코드로 이동. 
			// re.next() 메서드가 실행될때 다음 레코드가 있으면 true, 없으면 false를 리턴
			System.out.println("번호 \t 이름 \t\t 이메일 \t\t 전화번호");
			System.out.println("--------------------------------------------------");
			while(rs.next()) { //결과의 처음부터 끝까지 반복 실행 : 레코드 단위로 반복 실행
				// rs.getInt() : number 형 필드값을 추출하는 메서드. 괄호안에 필드이름을 정확히 써야합니다.
				// 필드명에 오타가 있거나 안맞으면 부적합한 열이름 이라는 에러가 발생합니다.
				int num = rs.getInt("num");
				// rs.getString() : varchar2 형 필드값을 추출하는 메서드
				// 모든 자료형에 대해 get~() 메서드가 모두 존재합니다
				String name = rs.getString("name");
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				System.out.printf("%d\t %s\t %s\t %s\n",num,name,email,tel);
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
