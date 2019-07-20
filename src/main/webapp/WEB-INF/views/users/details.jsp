<%@ page import="com.security.demo.persistance.models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.security.demo.persistance.models.Role" %>
<%@ page import="com.security.demo.persistance.models.Privilege" %>
<jsp:include page="../header.jsp"></jsp:include>

<table class="table table-striped table-responsive-md btn-table" style="text-align: center">
    <thead>
    <tr>
        <th>#</th>
        <th> Name</th>
        <th>email</th>
        <th>password</th>
        <th>Roles/privilleges</th>
    </tr>
    </thead>

    <tbody>
        <%
    List<User> users = (List<User>) request.getAttribute("users");
    for (User user : users) {%>

    <tr>
        <td><%=user.getId()%></td>
        <td>
            <%=user.getFirstName() + " " + user.getLastName()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getPassword()%>
        </td>
        <td>
            <table class="table table-striped table-responsive-md btn-table">
                <thead>
                <tr>
                    <th>Roles</th>
                    <th>Privillege</th>
                </tr>
                </thead>

                <tbody>

                <% for (Role role : user.getRoles()) {%>

                <% for (Privilege privilege : role.getPrivileges()) {%>
                <tr>
                    <td><%=role.getName()%>
                    </td>
                    <td><%=privilege.getName()%>
                    </td>
                </tr>
                <%}%>

                <%}%>
                </tbody>
            </table>
        </td>

    </tr>

        <%}%>
</table>

</tbody>

</table>