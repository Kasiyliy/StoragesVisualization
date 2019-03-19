
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getSession().getAttribute("user");

    if(user==null){
        response.sendRedirect("/");
        return;
    }
%>
<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link" href="/profile">Profile</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/sources">Sources</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/storages">Storages</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/cells">Cells</a>
    </li>
    <li class="nav-item">

        <form action="/logout" method="post">
            <button class="nav-link"  type="submit">Выйти из системы</button>
        </form>
    </li>
    <li class="nav-item">
        <a class="nav-link">Welcome <%=user.getUsername()%>!</a>
    </li>
</ul>