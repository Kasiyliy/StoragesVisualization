<%@ include file="/views/parts/head.jsp"%>
<%
  User currentUser = (User) session.getAttribute("user");
  if(currentUser!=null){
    response.sendRedirect("/profile");
    return;
  }
%>
<div class="row">
  <div class="col-sm-4 offset-md-4" >

    <form action="auth" method="post" class="mt-4">
      <%
        if(request.getParameter("error")!=null){
      %>
      <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>Error!</strong> Something bad happened!
      </div>
      <%
        }
      %>

      <div class="form-group">
        <label for="username">Username address:</label>
        <input type="username" name="username" class="form-control" id="username">
      </div>
      <div class="form-group">
        <label for="pwd">Password:</label>
        <input type="password" name="password" class="form-control" id="pwd">
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>
</div>


<%@ include file="/views/parts/footer.jsp"%>