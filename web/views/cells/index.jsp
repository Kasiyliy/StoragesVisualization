<%@ page import="models.Cell" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Storage" %>
<%@include file="/views/parts/head.jsp"%>
<%@include file="/views/parts/nav.jsp"%>

<div class="jumbotron text-center">
    <h1>Cells Page</h1>
</div>

<%
    List<Cell> list = (List<Cell>)request.getAttribute("list");
    List<Storage> storageList = (List<Storage>)request.getAttribute("storageList");
%>

<div class="container">
    <form action="/cells" method="post" class="m-3"  enctype="multipart/form-data">
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" name="name" aria-describedby="emailHelp" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label for="invNumber">InvNumber</label>
                    <input type="number" class="form-control" id="invNumber" name="invNumber" placeholder="invNumber">
                </div>
                <div class="form-group">
                    <label for="crn">CRN</label>
                    <input type="number"  step=any  class="form-control" id="crn" name="crn" placeholder="CRN">
                </div>
                <div class="form-group">
                    <label for="costPrice">Cost price</label>
                    <input type="number"  step=any  class="form-control" id="costPrice" name="costPrice" placeholder="Cost price">
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="ownDate">Own date</label>
                    <input type="date" name="ownDate" class="form-control" id="ownDate"  placeholder="Own date">
                </div>

                <div class="form-group">
                    <label for="expireDate">Expire date</label>
                    <input type="date" name="expireDate" class="form-control" id="expireDate"  placeholder="Expire date">
                </div>
                <div class="form-group">
                    <label for="storageId">Storage</label>
                    <select class="form-control" id="storageId" name="storageId" placeholder="Storage">
                        <% for(Storage storage : storageList) {%>
                        <option value="<%=storage.getId()%>"><%=storage.getName()%></option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="image">Image</label>
                    <input type="file" name="image" class="form-control" id="image"  placeholder="Image">
                </div>
            </div>
        </div>


        <button type="submit" class="btn btn-success">Add</button>
    </form>

<table class="table table-hover">
    <thead>
    <tr>
        <th>#</th>
        <th>ID</th>
        <th>NAME</th>
        <th>Image</th>
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
        <td>
            <img src="/getImage?file=<%=list.get(i).getImagePath()%>" alt="" width="100px" height="100px">
        </td>
        <td>
            <a href="/cells/details?id=<%=list.get(i).getId()%>" class="btn btn-info">Read more</a>
        </td>
        <td>
            <form action="/cells" method="post">
                <input type="hidden" value="<%=list.get(i).getId()%>" name="id">
                <input type="hidden" value="delete" name="_method">
                <input class="btn btn-danger" type="submit" value="DELETE">
            </form>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<%@ include file="/views/parts/footer.jsp"%>