<%@page import="model.EmpVO"%>
<%@page import="java.util.List"%>
<%@page import="model.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
EmpDAO dao = new EmpDAO();
int deptid = Integer.parseInt(request.getParameter("deptid"));
List<EmpVO> emplist = dao.selectByDept(deptid);
%>

<table>
<tr>
<td>직원번호</td>
<td>직원이름</td>
<td>입사일</td>
<td>급여</td>
<td>부서</td>
</tr>
 
<%
for(EmpVO emp:emplist){
	String f =  
			 "<tr>"+ 
			 "<td>%d</td>"+ 
			 "<td>%s</td>"+ 
			 "<td>%s</td>"+ 
			 "<td>%d</td>"+ 
			 "<td>%d</td>"+ 
			"</tr>";
	
	String s = String.format(f, emp.getEmployee_id(),emp.getFirst_name(),
			emp.getHire_date(), emp.getSalary(),emp.getDepartment_id());
	out.print(s);
}
%>

</table>