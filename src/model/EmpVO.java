package model;

import java.sql.Date;

//DTO(Data Transfer Object)
//VO(Value Object)
//sqlpus  -  desc employees; // 알트 드래그로 선택  복사해서 가져오기// 컨트롤 쉬프트 y 모두 소문자로
public class EmpVO {
	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private int salary;
	private double commission_pct;
	private int manager_id;
	private int department_id;
	
	//생성하는 방법이 3가지있다~~ 기본 하나 만들고 전체 있는거 하나 만들고 필수들어가야하는거 만들고....
	public EmpVO() {
		super();
	}
	public EmpVO(int employee_id, String first_name, String last_name, String email, String phone_number,
			Date hire_date, String job_id, int salary, double commission_pct, int manager_id, int department_id) {
		super();
		 this.employee_id = employee_id;
		 this.first_name = first_name;
		 this.last_name = last_name;
		 this.email = email;
		 this.phone_number = phone_number;
		 this.hire_date = hire_date;
		 this.job_id = job_id;
		 this.salary = salary;
		 this.commission_pct = commission_pct;
		 this.manager_id = manager_id;
		 this.department_id = department_id;
	}
	public EmpVO(int employee_id, String last_name, String email, Date hire_date, String job_id) {
		super();
		this.employee_id = employee_id;
		this.last_name = last_name;
		this.email = email;
		this.hire_date = hire_date;
		this.job_id = job_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public double getCommission_pct() {
		return commission_pct;
	}
	public void setCommission_pct(double commission_pct) {
		this.commission_pct = commission_pct;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmpVO 직원정보 [employee_id=").append(employee_id).append(", first_name=").append(first_name)
				.append(", last_name=").append(last_name).append(", email=").append(email).append(", phone_number=")
				.append(phone_number).append(", hire_date=").append(hire_date).append(", job_id=").append(job_id)
				.append(", salary=").append(salary).append(", commission_pct=").append(commission_pct)
				.append(", manager_id=").append(manager_id).append(", department_id=").append(department_id)
				.append("]");
		return builder.toString();
	}
	
	

}
