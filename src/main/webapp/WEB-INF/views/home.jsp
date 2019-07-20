<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.userdetails.User" %>
<jsp:include page="header.jsp"></jsp:include>

<%

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

%>
    <%=authentication.getName()%>

<a href="/user/create" type="button" class="btn btn-default btn-lg btn-block">create user</a>
<a href="/user/details" type="button" class="btn btn-default btn-lg btn-block">get all users</a>
<a href="/perform_logout" type="button" class="btn btn-primary btn-lg btn-block">Log out</a>

<jsp:include page="footer.jsp"></jsp:include>


