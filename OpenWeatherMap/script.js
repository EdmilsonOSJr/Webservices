let map;
let marker;

function consulta(){
    
    let cidade = $("#cidade").val();
    const keyOpenWeateher = "67e23b0f421a08c74b84b1fed744be57";
    let nacerDoSol = new Date(0);
    let porDoSol = new Date(0);

    $.ajax({
        async: true,
        url: "https://api.openweathermap.org/data/2.5/weather?q="+cidade+"&units=metric&APPID="+keyOpenWeateher,
        method: "GET",
        
        success:function(response){
            nacerDoSol.setUTCSeconds(response.sys.sunrise+response.timezone);
            porDoSol.setUTCSeconds(response.sys.sunset+response.timezone);
            $("#temp").html(response.main.temp+"ºC");
            $("#temp_min").html(response.main.temp_min+"ºC");
            $("#temp_max").html(response.main.temp_max+"ºC");
            $("#nascer_sol").html(nacerDoSol.getUTCHours()+":"+nacerDoSol.getUTCMinutes());
            $("#por_sol").html(porDoSol.getUTCHours()+":"+porDoSol.getUTCMinutes());
            $("#lat").html(response.coord.lat);
            $("#lon").html(response.coord.lon);
            $("#image").attr('src', "http://openweathermap.org/img/wn/"+response.weather[0].icon+"@2x.png")

            iniciaMapa(response.coord.lat,response.coord.lon);
        },
        
        error: function(){
            alert("Cidade fornecida não encontrada.")
        }
    });
}

function iniciaMapa(lat,long) {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: Number(lat), lng: Number(long) },
        zoom: 8,
    });

    let coordenadas = new google.maps.LatLng(lat,long);

    marker = new google.maps.Marker({
        position: coordenadas, 
        map: map,
        title:$("#cidade").val()
    });
}