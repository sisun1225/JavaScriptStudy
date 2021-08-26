package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class DeptDAO {
	
	public List<DeptVO> selectAll() {
		List<DeptVO> deptlist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null; // 변수가 try문 밖으로 못나오니 밖에서 한번 선언해준다.
		ResultSet rs = null;
		String sql = "select * from departments order by 1";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql); 
			while(rs.next()) { //있을때까지 돌아라
				DeptVO dept = new DeptVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
						deptlist.add(dept); //만들어서 넣어줘.....
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return deptlist;
	}

	public int insertDept(DeptVO dept) {  
		String sql="insert into departments values(?,?,?,?)";
		Connection conn;
		PreparedStatement st = null;
		int result = 0;
		
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, dept.getDepartment_id());
			st.setString(2, dept.getDepartment_name());
			st.setInt(3, dept.getManager_id());
			st.setInt(4, dept.getLocation_id());
			result = st.executeUpdate();  

		} catch (SQLException e) {
				e.printStackTrace();

		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		
		return result;
	}
	
	
}
