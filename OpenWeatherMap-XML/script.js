let map;
let marker;

function consulta(){
    
    let cidade = $("#cidade").val();
    const keyOpenWeateher = "67e23b0f421a08c74b84b1fed744be57";
   
    $.ajax({
        async: true,
        url: "https://api.openweathermap.org/data/2.5/weather?q="+cidade+"&units=metric&&mode=xml&APPID="+keyOpenWeateher,
        method: "GET",
        

        success:function(xml){
            
            
            let temperatura = xml.getElementsByTagName('temperature')[0];
            let sun = xml.getElementsByTagName('sun')[0];
            let coordenadas = xml.getElementsByTagName('coord')[0]; 
            let clima = xml.getElementsByTagName('weather')[0];

            let nacerDoSol = new Date(new Date(sun.getAttribute('rise') + "+0000"));
            
            let porDoSol = new Date(new Date(sun.getAttribute('set') + "+0000"));
            
            $("#temp").html(temperatura.getAttribute('value')+"ºC");
            $("#temp_min").html(temperatura.getAttribute('min')+"ºC");
            $("#temp_max").html(temperatura.getAttribute('max')+"ºC");

            $("#nascer_sol").html(nacerDoSol.getHours()+":"+nacerDoSol.getMinutes());
            $("#por_sol").html(porDoSol.getHours()+":"+porDoSol.getMinutes());

            $("#lat").html(coordenadas.getAttribute('lat'));
            $("#lon").html(coordenadas.getAttribute('lon'));
            
            $("#image").attr('src', "http://openweathermap.org/img/wn/"+clima.getAttribute('icon')+"@2x.png")

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