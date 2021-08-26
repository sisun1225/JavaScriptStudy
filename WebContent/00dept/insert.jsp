<%@page import="model.DeptDAO"%>
<%@page import="model.DeptVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="3;url=menu.html">
<title>부서db등록</title>

<%
//1.사용자의 입력값을 읽기
int id = Integer.parseInt(request.getParameter("dept_id"));
String name = request.getParameter("dept_name");
int mid = Integer.parseInt(request.getParameter("manager_id"));
int lid = Integer.parseInt(request.getParameter("location_id"));
DeptVO dept = new DeptVO(id, name, mid, lid);
//2.DAO호출
DeptDAO dao = new DeptDAO();
int result = dao.insertDept(dept);
String message = "입력성공";

if(result<=0){
	message = "입력실패";
}
%>

</head>
<body>



	<h1>알림:<%=message %></h1>
	<hr>
	<p>부서아이디는 ${param.dept_id}</p>
	<p>dept_name ${param.dept_name}</p>
	<p>manager_id ${param.manager_id}</p>
	<p>location_id ${param.location_id}</p>
	
</body>
</html>
