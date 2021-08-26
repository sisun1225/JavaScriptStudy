package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

//DAO(Data Access object)  db에가서 해야하는 작업
public class EmpDAO {
	
	
	//CRUD(Create:insert, Read:select, U:Update, D:Delete)
	
	//사용자가 웹을 통해서 삭제
	public int delete(int empid) {
		int result = 0;
		String sql= "delete from employees where EMPLOYEE_ID = ?";
		Connection conn;
		PreparedStatement st = null;
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			result = st.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	
	//사용자가 웹을 통해서 개인정보수정      본래정보가 보인다.=> controller=>Dao =>db
	public int updateEmp(EmpVO emp) {
		int result = 0; //수정된 건수를 리턴하겠다..
		String sql=
				" update employees set " +
			    " FIRST_NAME=?, " +    
			    " LAST_NAME =?," +
			    " EMAIL=?," +
			    " PHONE_NUMBER=?," +
			    " HIRE_DATE=?," +
			    " JOB_ID=?," +
			    " SALARY=?," +
			    " COMMISSION_PCT=?," +
			    " MANAGER_ID=?," +
			    " DEPARTMENT_ID=?" +
			" where EMPLOYEE_ID = ? ";
			    
		Connection conn;
		PreparedStatement st = null;
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setInt(11, emp.getEmployee_id());
			st.setString(1, emp.getFirst_name());
			st.setString(2, emp.getLast_name());
			st.setString(3, emp.getEmail());
			st.setString(4, emp.getPhone_number());
			st.setDate(5, emp.getHire_date());
			st.setString(6, emp.getJob_id());
			st.setInt(7, emp.getSalary());
			st.setDouble(8, emp.getCommission_pct());
			st.setInt(9, emp.getManager_id());
			st.setInt(10, emp.getDepartment_id());
			result = st.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}

		return result;
		
	}
	
	
	
	//사용자가 웹을 통해서 회원가입 한다 => controller가 다받는다-> service -> dao ->db   //우리는 서비스 없이 했다..
	public int insertEmp(EmpVO emp) {  //컨트롤러가 읽어서 emp로 직원정보가 온다.. 
		String sql="insert into employees values(?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn;
		PreparedStatement st = null;
		//값을 넣고 끝이기 때문에 resultset은 필요없음
		int result = 0; //인서트 잘했는지 건수확인용....
		
		conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);//자동으로 커밋되는거 방지......
			st = conn.prepareStatement(sql);
			//여기부터 물음표에 들어갈것 설정
			st.setInt(1, emp.getEmployee_id());
			st.setString(2, emp.getFirst_name());
			st.setString(3, emp.getLast_name());
			st.setString(4, emp.getEmail());
			st.setString(5, emp.getPhone_number());
			st.setDate(6, emp.getHire_date());
			st.setString(7, emp.getJob_id());
			st.setInt(8, emp.getSalary());
			st.setDouble(9, emp.getCommission_pct());
			st.setInt(10, emp.getManager_id());
			st.setInt(11, emp.getDepartment_id());
			result = st.executeUpdate();  //db에 수정할때는 무조건 익스큐트업데이트로
			conn.commit();//다 잘 수행된다면 그때 commit
		} catch (SQLException e) {
			try {
				conn.rollback();//실패하면 롤백 //그리고 trycatch처리 해주기
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	

	//넣고싶은 기능
	//1.모든직원조회
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null; // 변수가 try문 밖으로 못나오니 밖에서 한번 선언해준다.
		ResultSet rs = null;
		String sql = "select * from employees";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql); 
			while(rs.next()) { //있을때까지 돌아라
				emplist.add(makeEmp(rs)); //만들어서 넣어줘.....
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return emplist;
	}
	//2.기본키(primary key)..null불가, 필수칼럼, 중복없음...
	//직원번호로 조회
	public EmpVO selectById(int empid) {
		EmpVO emp = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null; // 변수가 try문 밖으로 못나오니 밖에서 한번 선언해준다.
		ResultSet rs = null;
		String sql = "select * from employees where employee_id = ?";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			rs = st.executeQuery(); 
			while(rs.next()) { //있을때까지 돌아라
				emp= makeEmp(rs); //만들어서 넣어줘.....
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return emp;
	}


	//3. 부서로 조회
	public List<EmpVO> selectByDept(int deptid) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null; // 변수가 try문 밖으로 못나오니 밖에서 한번 선언해준다.
		ResultSet rs = null;
		String sql = "select * from employees where department_id=?";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, deptid);
			rs = st.executeQuery(); 
			while(rs.next()) { //있을때까지 돌아라
				emplist.add(makeEmp(rs)); //만들어서 넣어줘.....
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return emplist;
	}
	
	
	//4. job_id로 조회
	public List<EmpVO> selectByjob(String jobid) {
		List<EmpVO> emplist = new ArrayList<>(); //여러건 넣을 방 만들고
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null; 
		ResultSet rs = null;
		String sql = "select * from employees where job_id=?"; //가변이야~
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, jobid);
			rs = st.executeQuery(); 
			while(rs.next()) { //있을때까지 돌아라
				emplist.add(makeEmp(rs)); //만들어서 넣어줘.....
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return emplist;
	}
	
	
	//5. 급여로 조회
	public List<EmpVO> selectBySalary(int minsal, int maxsal) {
		List<EmpVO> emplist = new ArrayList<>(); //여러건 넣을 방 만들고
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null; 
		ResultSet rs = null;
		String sql = "select * from employees where salary between ? and ?"; //가변이야~
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, minsal);
			st.setInt(2, maxsal);
			rs = st.executeQuery(); 
			while(rs.next()) { //있을때까지 돌아라
				emplist.add(makeEmp(rs)); //만들어서 넣어줘.....
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	
	//6. 입사일조회
	public List<EmpVO> selectByDate2(Date sdate, Date edate) {
		List<EmpVO> emplist = new ArrayList<>(); //여러건 넣을 방 만들고
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null; 
		ResultSet rs = null;
		String sql = "select * from employees "
				+ "where hire_date between ? and ?"; 
		try {
			st = conn.prepareStatement(sql);
			st.setDate(1, sdate);
			st.setDate(2, edate);
			rs = st.executeQuery(); 
			while(rs.next()) { //있을때까지 돌아라
				emplist.add(makeEmp(rs)); //만들어서 넣어줘.....
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	
	
	
	public List<EmpVO> selectByDate(String sdate, String edate) {
		List<EmpVO> emplist = new ArrayList<>(); //여러건 넣을 방 만들고
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null; 
		ResultSet rs = null;
		String sql = "select * from employees "
				+ "where hire_date between ? and ?"; 
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, sdate);
			st.setString(2, edate);
			rs = st.executeQuery(); 
			while(rs.next()) { //있을때까지 돌아라
				emplist.add(makeEmp(rs)); //만들어서 넣어줘.....
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	
	
	//7. 이름에 특정문자가 들어간 사람 조회
	public List<EmpVO> selectByName(String ch) {
		List<EmpVO> emplist = new ArrayList<>(); //여러건 넣을 방 만들고
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null; 
		ResultSet rs = null;
		String sql = "select * from employees "
				+ "where first_name like ?"; 
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, "%"+ch+"%");
			
			rs = st.executeQuery(); 
			while(rs.next()) { //있을때까지 돌아라
				emplist.add(makeEmp(rs)); //만들어서 넣어줘.....
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	
	
	//8. 여러조건조회(부서, job, salary, hire_date)
	public List<EmpVO> selectByCondition(int deptid, 
			               String jobid, int sal, Date hdate) { //pc 날짜 구할거 아니면 sql데이터가 편하다.
		List<EmpVO> emplist = new ArrayList<>(); //여러건 넣을 방 만들고
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null; 
		ResultSet rs = null;
		String sql = "select * from employees "+
		    	" where department_id =?"+
		   		" and job_id = ?"+
				" and salary >= ?"+
				" and hire_date between ? and add_months(?, 60) ";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, deptid);
			st.setString(2, jobid);
			st.setInt(3, sal);
			st.setDate(4, hdate);
			st.setDate(5, hdate);
			rs = st.executeQuery(); 
			while(rs.next()) { 
				emplist.add(makeEmp(rs)); //만들어서 넣어줘.....
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	
	
	
	private EmpVO makeEmp(ResultSet rs) throws SQLException {
		EmpVO emp = new EmpVO(); //너무 많으니 없을때는 괄호안에 비우고 디폴트로 해준다
		emp.setCommission_pct(rs.getDouble("Commission_pct"));
		emp.setDepartment_id(rs.getInt("Department_id"));  //숫자줘도되지만 칼럼이름이 오타적음
		emp.setEmail(rs.getString("Email"));
		emp.setEmployee_id(rs.getInt("Employee_id"));
		emp.setFirst_name(rs.getString("First_name"));
		emp.setHire_date(rs.getDate("Hire_date"));
		emp.setJob_id(rs.getString("Job_id"));
		emp.setLast_name(rs.getString("Last_name"));
		emp.setManager_id(rs.getInt("Manager_id"));
		emp.setPhone_number(rs.getString("Phone_number"));
		emp.setSalary(rs.getInt("Salary"));
		return emp;
	}
	
	
	
}
