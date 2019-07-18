<%@ page import="com.security.demo.persistance.models.User" %>
<%@ page import="java.util.List" %>
<jsp:include page="../header.jsp"></jsp:include>

<table class="table table-striped table-responsive-md btn-table"></table>

<thead>
<tr>
    <th>#</th>
    <th> Name</th>
    <th>email</th>
    <th>password</th>
</tr>
</thead>

<tbody>
<%
    List<User> users=(List<User>)request.getAttribute("users");
    for (User user:users ) {%>

<tr>
    <th scope="row">xx</th>
    <td>
      <%=user.getFirstName()+" "+user.getLastName()%>
    </td>
    <td><%=user.getEmail()%></td>
    <td><%=user.getRoles()%></td>
</tr>

    <%}%>

</tbody>

</table>