<%@page import="model.DeptVO"%>
<%@page import="java.util.List"%>
<%@page import="model.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<tr>
  <th>부서번호</th><th>부서이름</th><th>메니저</th><th>지역번호</th>
</tr>
<%
DeptDAO dao = new DeptDAO();
List<DeptVO> deptlist=dao.selectAll();
for(DeptVO dept:deptlist){
	out.print("<tr>");
	out.print("<td>"+ dept.getDepartment_id() +"</td>");
	out.print("<td>"+ dept.getDepartment_name() +"</td>");
	out.print("<td>"+ dept.getManager_id() +"</td>");
	out.print("<td>"+ dept.getLocation_id() +"</td>");
	out.print("</tr>");
}
%>

</table>

</body>
</html>