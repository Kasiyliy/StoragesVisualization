<%@ page import="models.Source" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Storage" %>
<%@include file="/views/parts/head.jsp"%>
<%@include file="/views/parts/nav.jsp"%>
<script src="https://api-maps.yandex.ru/2.1/?apikey=b644cebb-e397-49c0-9e0e-f304bd3e04c2&lang=en_US" type="text/javascript"></script>

<%
    Source source = (Source) request.getAttribute("source");
    List<Storage> storageList = (List<Storage>)request.getAttribute("storageList");
%>

<div class="jumbotron text-center">
    <h1>Source: <%=source.getName()%></h1>
</div>

<div class="container">
    <div class="row">
        <div class="col-6">
            <div class="card">
                <div class="card-header">
                    Add new storage to source <%=source.getName()%>
                </div>
                <div class="card-body">
                    <div id="map" style="width: 100%; height: 400px">

                    </div>
                    <span class="font-italic small">click sign to get information</span>
                    <form action="/storages" method="post" class="m-3">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" id="name" name="name" aria-describedby="emailHelp" placeholder="Enter name">
                            <input type="hidden" name="sourceId" value="<%=source.getId()%>">
                        </div>
                        <button type="submit" class="btn btn-success">Add</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-6">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>NAME</th>
                    <th>SOURCE</th>
                    <th>Details</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for(int i = 0; i < storageList.size(); i++){
                %>
                <tr>
                    <td><%=i+1%></td>
                    <td><%=storageList.get(i).getName()%></td>
                    <td><%=storageList.get(i).getSource().getName()%></td>
                    <td>
                        <a href="/storages/details?id=<%=storageList.get(i).getId()%>" class="btn btn-info">Read more</a>
                    </td>
                    <td>
                        <form action="/storages" method="post">
                            <input type="hidden" name="_method" value="delete">
                            <input type="hidden" name="storageId" value="<%=storageList.get(i).getId()%>">
                            <input type="submit" value="delete" class="btn btn-danger">
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    ymaps.ready(init);
    var myMap,
        myPlacemark;

    var cords = [<%=source.getLatitude()%>, <%=source.getLongitude()%>];
    function init(){
        myMap = new ymaps.Map("map", {
            center:cords ,
            zoom: 7
        }, {
            searchControlProvider: 'yandex#search'
        });

        myPlacemark = new ymaps.Placemark(cords, { hintContent: "<%=source.getName()%>",
            balloonContent: `<%=source.getName() + "\n " + source.getLatitude() + " " + source.getLongitude()%>`
        });

        myMap.geoObjects.add(myPlacemark);

        myPlacemark.balloon.open();
    }
</script>
<%@ include file="/views/parts/footer.jsp"%>