package JDBC03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Dao : Database Access Object
public class Book_Dao {
	String driver ="oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,"scott","tiger");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	public void close() {
		try {
			if(con != null) con.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Book_Dto> selectAll() {
		// 데이터베이스에서 booklist 테이블 조회후 리턴
		
		// 무엇이든 담을수 있는 ArrayList 인데 아래는 Book_Dto 객체만 담을 수 있는 제네릭 문법을 사용하였습니다.
		ArrayList<Book_Dto> list = new ArrayList<>();
		String sql = "Select * from booklist order by booknum desc";
		con = getConnection();
		
		try {
			// Class.forName(driver);
			// con=DriverManager.getConnection(url,"scott","tiger");  위에 con = getConnection(); 쓴다 (빠져나옴)
			
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int booknum = rs.getInt("booknum");
				String subject = rs.getString("subject");
				int makeyear = rs.getInt("makeyear");
				int inprice = rs.getInt("inprice");
				int rentprice = rs.getInt("rentprice");
				String grade = rs.getString("grade");
				
				Book_Dto bdto = new Book_Dto();
				bdto.setBooknum(booknum);
				bdto.setSubject(subject);
				bdto.setMakeyear(makeyear);
				bdto.setInprice(inprice);
				bdto.setRentprice(rentprice);
				bdto.setGrade(grade);
				
				// 반복이 실행딜때 마다 new Book_Dto() 로 만들어진 객체가 add 되므로 각 레코드들이 담기는 것과 같은 효과를 볼 수 있습니다.
				list.add(bdto);
			}
			
		} 
		// catch (ClassNotFoundException e) {e.printStackTrace();}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * try { if(con != null) con.close(); if(pstmt != null) pstmt.close(); if(rs !=
		 * null) rs.close(); } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		close();
		
		return list;
	}


	public int insert(Book_Dto bdto) {
		int result =0; // 레코드 추가 성공 실패를 저장할 변수
		String sql = "insert into booklist values (book_seq.nextVal , ? ,? ,? ,?,?)";
		con = getConnection();
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getSubject());
			pstmt.setInt(2, bdto.getMakeyear());
			pstmt.setInt(3, bdto.getInprice());
			pstmt.setInt(4, bdto.getRentprice());
			pstmt.setString(5, bdto.getGrade());	
			result = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(); 
		return result;
	}
	
	public int delete(String num) {
		int result = 0;
		String sql = "delete from booklist where booknum = ?";
		con = getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return result;
	}
	public Book_Dto getDto(String booknum) {
		Book_Dto bdto = null;
		con = getConnection();
		String sql ="select * from booklist where booknum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, booknum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bdto = new Book_Dto();
				bdto.setBooknum(rs.getInt("booknum"));
				bdto.setSubject(rs.getString("subject"));
				bdto.setMakeyear(rs.getInt("makeyear"));
				bdto.setInprice(rs.getInt("inprice"));
				bdto.setRentprice(rs.getInt("Rentprice"));
				bdto.setGrade(rs.getString("grade"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return bdto;
	}
	public void update(Book_Dto newDto) {
		con = getConnection();
		String sql = "update booklist set subject = ? , makeyear=?, inprice = ? , rentprice = ? , grade = ? where booknum = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newDto.getSubject());
			pstmt.setInt(2, newDto.getMakeyear());
			pstmt.setInt(3, newDto.getInprice());
			pstmt.setInt(4, newDto.getRentprice());
			pstmt.setString(5, newDto.getGrade());
			pstmt.setInt(6, newDto.getBooknum());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
