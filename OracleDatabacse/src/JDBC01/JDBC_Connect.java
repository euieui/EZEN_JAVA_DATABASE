package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connect {

	public static void main(String[] args) {
		// 자바에서 지원하는 데이터베이스연결을 위한 구성요소들과 객체를 세팅
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id="scott";
		String pw ="tiger";
		
		// JDBC 를 통한 데이터 베이스 연결 클래스의 객체 : Connection
		Connection con = null;
		try {
			Class.forName(driver);
			// 연결
			DriverManager.getConnection(url,id,pw);
			System.out.println("데이터베이스에 연결 성공했습니다.");
			
			// 데이터 베이스 작업
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("별도의 사유로 인한 연결 실패");
		}
		try {
			if(con!= null) con.close();
			System.out.println("데이터베이스 종료");
		} catch (SQLException e) {
			System.out.println("연결이 종료되지 않았습니다.");
		}
		
		// orcaldatabase 위에서 오른쪽버튼 properties 클릭
		// -> java Build Path 클릭 ->  위탭에서 LIBRARIES클릭
		//	-> 오른쪽 2번재 add External JARs... 클릭
		// -> ojdbc6.jar 열기
		// -> 자바에 있는 Class.forName(driver); 예외 처리하면 빨간줄이 없어진다!
		
		
	}

}
