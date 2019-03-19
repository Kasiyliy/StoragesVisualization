
<%@ page import="java.util.List" %>
<%@ page import="models.Storage" %>
<%@ page import="models.Source" %>
<%@include file="/views/parts/head.jsp"%>
<%@include file="/views/parts/nav.jsp"%>





<div class="jumbotron text-center">
    <h1>Storages Page</h1>
</div>

<%
    List<Storage> list = (List<Storage>)request.getAttribute("storageList");
    List<Source> sourceList = (List<Source>)request.getAttribute("sourceList");
%>

<div class="container">
    <form action="/storages" method="post" class="m-3">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" aria-describedby="emailHelp" placeholder="Enter name">
        </div>
        <div class="form-group">
            <label for="source_id">Source</label>
            <select class="form-control" id="source_id" name="sourceId" placeholder="Source">
                <% for(Source source : sourceList) {%>
                    <option value="<%=source.getId()%>"><%=source.getName()%></option>
                <% } %>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Add</button>
    </form>
</div>


<table class="table table-hover">
    <thead>
    <tr>
        <th>#</th>
        <th>ID</th>
        <th>NAME</th>
        <th>SOURCE</th>
        <th>Details</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <%
        for(int i = 0; i < list.size(); i++){
    %>
    <tr>
        <td><%=i+1%></td>
        <td><%=list.get(i).getId()%></td>
        <td><%=list.get(i).getName()%></td>
        <td><%=list.get(i).getSource().getName()%></td>
        <td>
            <a href="/storages/details?id=<%=list.get(i).getId()%>" class="btn btn-info">Read more</a>
        </td>
        <td>
            <form action="/storages" method="post">
                <input type="hidden" name="_method" value="delete">
                <input type="hidden" name="storageId" value="<%=list.get(i).getId()%>">
                <input type="submit" value="delete" class="btn btn-danger">
            </form>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>


<%@ include file="/views/parts/footer.jsp"%>