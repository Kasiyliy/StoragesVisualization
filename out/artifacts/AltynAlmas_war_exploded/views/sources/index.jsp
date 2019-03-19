<%@ page import="models.Source" %>
<%@ page import="java.util.List" %>
<%@include file="/views/parts/head.jsp"%>
<%@include file="/views/parts/nav.jsp"%>
<script src="https://api-maps.yandex.ru/2.1/?apikey=b644cebb-e397-49c0-9e0e-f304bd3e04c2&lang=en_US" type="text/javascript"></script>

<div class="jumbotron text-center">
    <h1>Sources Page</h1>
</div>

<%
    List<Source> list = (List<Source>)request.getAttribute("list");
%>

<div class="container">

    <div class="row">
        <div class="col-6">
            <div class="card">
                <div class="card-header">
                    Add new Source
                </div>
                <div class="card-body">
                    <form action="/sources" method="post" class="m-3">
                        <h3 class="text-center">Choose coordinates from map</h3>
                        <div id="map" style="width: 100%; height: 400px">

                        </div>
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" id="name" name="name" aria-describedby="emailHelp" placeholder="Enter name" required>
                        </div>
                        <div class="form-group">
                            <label for="longitude">Longitude</label>
                            <input type="text" class="form-control" readonly id="longitude" name="longitude" placeholder="Longitude" required>
                        </div>
                        <div class="form-group">
                            <label for="latitude">Latitude</label>
                            <input type="text" name="latitude" readonly class="form-control" id="latitude"  placeholder="Latitude" required>
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
                    <th>ID</th>
                    <th>NAME</th>
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
                        <a href="/sources/details?id=<%=list.get(i).getId()%>" class="btn btn-info">Read more</a>
                    </td>
                    <td>
                        <form action="sources" method="post">
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
        </div>
    </div>


</div>



<script type="text/javascript">
    ymaps.ready(init);

    function init() {
        var myPlacemark,
            myMap = new ymaps.Map('map', {
                center: [48.005284, 66.9045434],
                zoom: 4
            }, {
                searchControlProvider: 'yandex#search'
            });

        // Слушаем клик на карте.
        myMap.events.add('click', function (e) {
            var coords = e.get('coords');
            // Если метка уже создана – просто передвигаем ее.
            if (myPlacemark) {
                myPlacemark.geometry.setCoordinates(coords);
            }
            // Если нет – создаем.
            else {
                myPlacemark = createPlacemark(coords);
                myMap.geoObjects.add(myPlacemark);
                // Слушаем событие окончания перетаскивания на метке.
                myPlacemark.events.add('dragend', function () {
                    getAddress(myPlacemark.geometry.getCoordinates());
                });
            }
            document.getElementById("latitude").value = coords[0];
            document.getElementById("longitude").value = coords[1];
            getAddress(coords);
        });

        // Создание метки.
        function createPlacemark(coords) {
            return new ymaps.Placemark(coords, {
                iconCaption: 'поиск...'
            }, {
                preset: 'islands#violetDotIconWithCaption',
                draggable: true
            });
        }

        // Определяем адрес по координатам (обратное геокодирование).
        function getAddress(coords) {
            myPlacemark.properties.set('iconCaption', 'поиск...');
            ymaps.geocode(coords).then(function (res) {
                var firstGeoObject = res.geoObjects.get(0);

                myPlacemark.properties
                    .set({
                        // Формируем строку с данными об объекте.
                        iconCaption: [
                            // Название населенного пункта или вышестоящее административно-территориальное образование.
                            firstGeoObject.getLocalities().length ? firstGeoObject.getLocalities() : firstGeoObject.getAdministrativeAreas(),
                            // Получаем путь до топонима, если метод вернул null, запрашиваем наименование здания.
                            firstGeoObject.getThoroughfare() || firstGeoObject.getPremise()
                        ].filter(Boolean).join(', '),
                        // В качестве контента балуна задаем строку с адресом объекта.
                        balloonContent: firstGeoObject.getAddressLine()
                    });
            });
        }
    }
</script>

<%@ include file="/views/parts/footer.jsp"%>