<html>
	<body>
		<%@ page import="java.util.List,com.springcookbook.bo.Post" %>
		There are ${nbUsers} users.
		<table width="59%" border="1">
		<%List<Post> list = (List<Post>) request.getAttribute("posts");%>
		<% for(int i = 0; i < list.size(); i+=1) { %>
        <tr> 
        	<td><%=list.get(i).getId()%></td>
        	<td><%=list.get(i).getTitle()%></td>
        	<td><%=list.get(i).getDate()%></td>
        	<td><%=list.get(i).getUser().getFirstName()%></td>
        </tr>
    	<% } %>
    	</table>
	</body>
</html>