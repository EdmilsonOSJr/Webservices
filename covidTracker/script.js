function carregPaises(){
      
    let urlCountries = "https://restcountries.eu/rest/v2/all";

    $.ajax({
        async: true,
        url: urlCountries,
        method: "GET",

        success:function(response){
            
            let country = "<option selected>Select a country</option>";
            let nameCountry;
            let pos;

            for(const variavel in  response){
                
                nameCountry = response[variavel].name;

                pos = nameCountry.indexOf("(");
                
                if(pos==-1){

                    if(nameCountry=="Holy See")
                        nameCountry = "Holy See (Vatican City State)";
                    
                }
                else{
                    nameCountry = nameCountry.substring(0, pos-1);

                    if(nameCountry=="Korea")
                        nameCountry = "S. Korea";

                    if(nameCountry=="Virgin Islands")
                        nameCountry = "British Virgin Islands";


                    }
                    
                country+="<option value="+nameCountry.replaceAll(" ","%20")+">"+nameCountry+"</option>";
            }     


            $("#paises").html(country);
        },
        error: function(){
            alert(response);
        },
    });

}


function recuperaEstatisticas(){
    
    let estatisticas;
    
    let urlCovid = "https://corona.lmao.ninja/v2/countries/"+$("#paises").val();

    $.ajax({
        async: true,
        url: urlCovid,
        method: "GET",
        success:function(response){
           
            estatisticas=`
            <div class="container my-5 rounded " id="dados1">

                <div class="card float-start my-4 " style="width: 18rem">
                    <div class="card-body">
                        <h5 class="card-title">População</h5>
                        <p class="card-text">`+response.population.toLocaleString('pt-BR')+`</p>
                    </div>
                </div>

                <div class="card float-end my-4" style="width: 18rem">
                    <div class="card-body">
                        <h5 class="card-title">Casos de Covid</h5>
                        <p class="card-text">`+response.cases.toLocaleString("pt-BR")+`</p>
                    </div>
                </div>


                <div class="card m-auto my-4" style="width: 18rem">
                    <div class="card-body">
                        <img src="`+response.countryInfo.flag+`"></img>
                        <h5 class="card-title">`+response.country+`</h5>
                        <p class="card-text">`+response.continent+`</p>
                    </div>
                </div>

                <div class="card float-start my-4" style="width: 18rem">
                    <div class="card-body">
                        <h5 class="card-title">Mortes por Covid</h5>
                        <p class="card-text">`+response.deaths.toLocaleString("pt-BR")+`</p>
                    </div>
                </div>

                <div class="card float-end my-4" style="width: 18rem">
                    <div class="card-body">
                        <h5 class="card-title">Recuperados da Covid</h5>
                        <p class="card-text">`+response.recovered.toLocaleString("pt-BR")+`</p>
                    </div>
                </div>
                
             </div>

             `;

            $("#estatisticas").html(estatisticas);
        
            iniciaMapa(response.countryInfo.lat, response.countryInfo.long);

        },
        
        error: function(response){
           alert(response.responseJSON.message);
        },
    });
}


function iniciaMapa(lat,long) {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: Number(lat), lng: Number(long) },
        zoom: 4,
    });

    let coordenadas = new google.maps.LatLng(lat,long);

    marker = new google.maps.Marker({
        position: coordenadas, 
        map: map,
        title:$("#paises").val()
    });
}

