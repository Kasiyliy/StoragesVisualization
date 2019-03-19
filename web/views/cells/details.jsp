<%@ page import="models.Source" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Storage" %>
<%@ page import="models.Cell" %>
<%@include file="/views/parts/head.jsp"%>
<%@include file="/views/parts/nav.jsp"%>
<script src="https://api-maps.yandex.ru/2.1/?apikey=b644cebb-e397-49c0-9e0e-f304bd3e04c2&lang=en_US" type="text/javascript"></script>

<%
    Cell cell = (Cell) request.getAttribute("cell");

%>

<div class="jumbotron text-center">
    <h1>Cell: <%=cell.getName()%></h1>
</div>

<div class="container">
    <div class="row">

        <div class="col-4">
            <div id="map" style="width: 100%; height: 400px">

            </div>
            <span class="font-italic small">click sign to get information</span>
        </div>

        <div class="col-4">
            <img src="/getImage?file=<%=cell.getImagePath()%>" class="img-fluid">
        </div>

        <div class="col-4">
            <h3 class="my-3"><%=cell.getName()%></h3>
            <h3 class="my-3">Cell details</h3>
            <ul>
                <li>Cost price: <%=cell.getCostPrice()%></li>
                <li>Crn: <%=cell.getCrn()%></li>
                <li>Inv number: <%=cell.getInvNumber()%></li>
                <li>Storage: <%=cell.getStorage().getName()%></li>
                <li>Own date: <%=cell.getOwnDate()%></li>
                <li>Expire date: <%=cell.getExpireDate()%></li>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">
    ymaps.ready(init);
    var myMap,
        myPlacemark;

    var cords = [<%=cell.getStorage().getSource().getLatitude()%>, <%=cell.getStorage().getSource().getLongitude()%>];
    function init(){
        myMap = new ymaps.Map("map", {
            center:cords ,
            zoom: 7
        }, {
            searchControlProvider: 'yandex#search'
        });

        myPlacemark = new ymaps.Placemark(cords, { hintContent: "<%=cell.getStorage().getSource().getName()%>",
            balloonContent: `<%=cell.getStorage().getSource().getName() + "\n " + cell.getStorage().getSource().getLatitude() + " " + cell.getStorage().getSource().getLongitude()%>`
        });

        myMap.geoObjects.add(myPlacemark);

        myPlacemark.balloon.open();
    }
</script>
<%@ include file="/views/parts/footer.jsp"%>