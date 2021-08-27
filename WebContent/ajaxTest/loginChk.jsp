<%@page import="model.EmpVO"%>
<%@page import="model.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String userid = request.getParameter("userid");
int empid = Integer.parseInt(userid);
EmpDAO dao = new EmpDAO();
EmpVO emp = dao.selectById(empid);
String message ="아이디에 해당하는 직원이 존재하지 않습니다. ";
if(emp!=null){
	message = emp.getFirst_name()+emp.getLast_name()+"환영~";	
}

%>

<p><%=message %></p>
<p>당신의 이메일은 ${param.email}입니다</p>