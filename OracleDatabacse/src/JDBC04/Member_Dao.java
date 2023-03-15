package JDBC04;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Member_Dao {
	String driver = "oracle.jdbc.driver.OracleDriver";
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

	public ArrayList<Member_Dto> selectAll() {
		ArrayList<Member_Dto> mlist = new ArrayList<>();
		String sql = "Select * from memberlist order by membernum desc";
		con = getConnection();
		
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int membernum = rs.getInt("membernum");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				Date birth = rs.getDate("birth");
				int bpoint = rs.getInt("bpoint");
				Date joindate = rs.getDate("joindate");
				String gender = rs.getString("gender");
				int age = rs.getInt("age");
				
				Member_Dto mdto = new Member_Dto();
				mdto.setMembernum(membernum);
				mdto.setName(name);
				mdto.setPhone(phone);
				mdto.setBirth(birth);
				mdto.setBpoint(bpoint);
				mdto.setJoindate(joindate);
				mdto.setGender(gender);
				mdto.setAge(age);
				
				mlist.add(mdto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close();
		
		return mlist;
	}

	public void insert(Member_Dto mdto) {
		String sql = "insert into memberlist (membernum, name, phone, birth, gender, age) values (member_seq.nextVal,?,?,?,?,?)";
		con=getConnection();
		
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getPhone());
			pstmt.setDate(3,mdto.getBirth());
			pstmt.setString(4, mdto.getGender());
			pstmt.setInt(5, mdto.getAge());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
	}

	public void delete(Member_Dto mdto) {
		
		String sql = "delete from memberlist where membernum = ? ";
		con = getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mdto.getMembernum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		
		
		
	}

	public Member_Dto getDto(String membernum) {
		Member_Dto mdto =null;
		con = getConnection();
		String sql = "select * from memberlist where membernum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, membernum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mdto = new Member_Dto();
				mdto.setMembernum(rs.getInt("membernum"));
				mdto.setName(rs.getString("name"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setBirth(rs.getDate("birth"));
				mdto.setBpoint(rs.getInt("bpoint"));
				mdto.setJoindate(rs.getDate("joindate"));
				mdto.setGender(rs.getString("gender"));
				mdto.setAge(rs.getInt("age"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return mdto;
	}

	public void update(Member_Dto newDto) {
		con = getConnection();
		String sql = "update memberlist set name=? , phone=?, birth =?, bpoint=?, gender=?, age=? where membernum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newDto.getName());
			pstmt.setString(2, newDto.getPhone());
			pstmt.setDate(3, newDto.getBirth());
			pstmt.setInt(4, newDto.getBpoint());
			pstmt.setString(5, newDto.getGender());
			pstmt.setInt(6, newDto.getAge());
			pstmt.setInt(7, newDto.getMembernum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}






