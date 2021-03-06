<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Employee Form</title>
</head>
<body>
<hr>
	<form:form action="updateEmployee" method="post" modelAttribute="employee">
		<form:label path="employeeId">Employee Id:</form:label>
		<form:input path="employeeId" readonly="readonly"/><br><br>
		<form:label path="employeeName">Employee Name:</form:label>
		<form:input path="employeeName" /><br><br>
		<form:label path="employeeDepartment">Employee Department:</form:label>
		<form:input path="employeeDepartment" /><br><br>
		<form:label path="employeeSalary">Employee Salary:</form:label>
		<form:input path="employeeSalary" /><br><br>
		<input type="submit" value="Add Employee"/>
	</form:form>
</body>
</html>