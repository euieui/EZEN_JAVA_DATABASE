package JDBC06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Rent_Dao {
	
	
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	// 생성자를 private으로 만들어서 외부에서 생성자를 사용할 수 없게 만듭니다.
	private Rent_Dao(){}  // 드라이브 에서 Rent_Dao rdao = new rent_Dao(); 를 할 수가 없다!
	
	// 클래스 내부에서 딱한개 유일한 객체를 생성합니다. private static 으로
	// private 요소는 클래스 내부에서 제한없이 사용가능
	private static Rent_Dao itc = new Rent_Dao();
	// 외부에서 itc 를 리턴 받아 쓸 수 dlTrpgownsms public static 메서드를 생성합니다
	
	public static Rent_Dao getInstance() {
		return itc;
	}

	public ArrayList<Rent_Dto> selectAll() {
		ArrayList<Rent_Dto> list = new ArrayList<>();
		
		con = DBManager.getConnection();
		String sql = "select to_char(rentdate,'YYYY-MM-DD') as rn, numseq, booknum, membernum, discount from rentlist order by numseq desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Rent_Dto rdto = new Rent_Dto();
				rdto.setRentdate(rs.getString("rn"));
				rdto.setNumseq(rs.getInt("numseq"));
				rdto.setBooknum(rs.getInt("booknum"));
				rdto.setMembernum(rs.getInt("membernum"));
				rdto.setDiscount(rs.getInt("discount"));
				list.add(rdto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBManager.close(con,pstmt,rs);
		return list;
	}

	public void insert(Rent_Dto rdto) {
		con = DBManager.getConnection();
		
		String sql = "insert into rentlist values (to_date(''||?||'','YYYYMMDD'),rent_seq.nextVal,?,?,?)"; // 난 그냥 되던데 ?
		// 		String sql = "insert into rentlist values (to_date(?,'YYYYMMDD'),rent_seq.nextVal,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rdto.getRentdate());
			pstmt.setInt(2, rdto.getBooknum());
			pstmt.setInt(3, rdto.getMembernum());
			pstmt.setInt(4, rdto.getDiscount());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBManager.close(con, pstmt, rs);
		
	}

	public Rent_Dto getDto(int parseInt) {
		con = DBManager.getConnection();
		String sql = "select to_char(rentdate,'YYYYMMDD') as rn, numseq, booknum, membernum, discount from rentlist where numseq= ?";
		Rent_Dto rdto = null;  // --- Rent_Dto rdto = new Rent_Dto(); 이렇게 해버리면 numseq 오버해서 입력할때 입력한 대여건이 없습니다가 뜨지 않는다
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, parseInt);
			rs = pstmt.executeQuery();
			
			while (rs.next()) { // if  와 while 차이가 있나?
				rdto = new Rent_Dto();
				rdto.setRentdate(rs.getString("rn"));
				rdto.setNumseq(rs.getInt("numseq"));
				rdto.setBooknum(rs.getInt("booknum"));
				rdto.setMembernum(rs.getInt("membernum"));
				rdto.setDiscount(rs.getInt("discount"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBManager.close(con, pstmt, rs);
		return rdto;
	}

	public void update(Rent_Dto newDto) {
		con = DBManager.getConnection();
		String sql = "update rentlist set rentdate =to_date(?,'yyyymmdd'), booknum = ?, membernum=?, discount=? where numseq=?";
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, newDto.getRentdate());
			pstmt.setInt(2, newDto.getBooknum());
			pstmt.setInt(3, newDto.getMembernum());
			pstmt.setInt(4, newDto.getDiscount());
			pstmt.setInt(5, newDto.getNumseq());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBManager.close(con, pstmt, rs);
		
	}

	public String confirmBn(String booknum) {
		con = DBManager.getConnection();
		String sql = "select * from booklist where booknum = ?";
		String bn=null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(booknum));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bn=rs.getInt("booknum") + ""; // 기타데이터에 + ""가 연산되면 결과는 String 입니다.
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBManager.close(con, pstmt, rs);
		return bn;
	}

	public String confrimMn(String membernum) {
		con = DBManager.getConnection();
		String sql = "select * from memberlist where membernum = ?";
		String mn = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(membernum));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mn=rs.getInt("membernum") + "";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBManager.close(con, pstmt, rs);
		return mn;
	}

	public void delete(int i) {
		con = DBManager.getConnection();
		String sql = "delete from rentlist where numseq = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, i);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBManager.close(con, pstmt, rs);
		
	}
	
	
	
	
	
}
 