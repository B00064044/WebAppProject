<html>
	<body>
		<%@ page import="java.util.List,com.springcookbook.bo.User" %>
		There are ${nbUsers} users.
		<table width="59%" border="1">
		<%List<User> list = (List<User>) request.getAttribute("users");%>
		<% for(int i = 0; i < list.size(); i+=1) { %>
        <tr> 
        	<td><%=list.get(i).getId()%></td>
        	<td><%=list.get(i).getFirstName()%></td>
        	<td><%=list.get(i).getAge()%></td>
        </tr>
    	<% } %>
    	</table>
	</body>
</html>