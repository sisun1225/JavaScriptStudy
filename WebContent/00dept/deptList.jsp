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

</head>
<body>

<!-- 드롭 다운 목록 -->
    <label for="prod1">부서 선택</label>
    <select id="prod1">
      <%
      for(DeptVO dept:deptlist){
    	  out.print("<option value='"+dept.getDepartment_id() +"'>" + 
      								dept.getDepartment_name() + "</option>");
      }
      %>
    </select>
    
    
    
    <hr>    
    <!-- 데이터 목록 -->
    <label for="prod2">포장 여부 </label>
    <input type="text" id="prod2" list="pack">
    <datalist id="pack">
      <%
      for(DeptVO dept:deptlist){
    	  out.print("<option value='"+dept.getDepartment_id() +"'>" + 
      								dept.getDepartment_name() + "</option>");
      }
      %>
    </datalist>     

</body>
</html>