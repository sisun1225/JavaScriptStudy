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
<%
DeptDAO dao = new DeptDAO();
List<DeptVO> deptlist = dao.selectAll(); 
%>

<script src="../00day10/js/jquery-3.6.0.min.js"></script>
<script>
$(function() {
	$("#deptSelect").on("change", function() {
		$.ajax({
			url:"emplist.jsp",
			data:{"deptid":$(this).val()},
			type:"get",
			success:function(htmlData){
				$("#here").html(htmlData);
			}
		});
	});
});
</script>


</head>
<body>
<h1>부서의 직원목록</h1>
<select id = "deptSelect">
  <option value="부서ID">부서이름</option>
  <%
  	for(DeptVO dept:deptlist){
  		String s=String.format("<option value='%s'>%s</option>", 
  				dept.getDepartment_id(),dept.getDepartment_name());
  		out.print(s);
  	}
  %>
</select>
<div id="here">여기</div>
</body>
</html>