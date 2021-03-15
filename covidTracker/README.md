# Covid Tracker 
 
 Combinação de 3 APIs para criação de um web app, que tem como objetivo informar dados estatísticas sobre a COVID em um determinado país. As 3 APIs utilizadas foram:
 
 - REST Countries: Utilizada para recuperar informações sobre países https://restcountries.eu/
 - Novel Covid: Utilizada para recuperar estatísticas sobre a COVID em um determinado pais ou estado https://documenter.getpostman.com/view/11144369/Szf6Z9B3?version=latest#513b0f63-b3ae-4427-9e4d-d3f6937789e9
 - Google Maps: Utilizada para apresentar um mapa com a localização do país escolhido pelo usuário https://cloud.google.com/maps-platform?hl=pt-br
 
 Para utilizar esse web app é necessário baixar a pasta covidTracker e fazer uma pequena modificação em uma linha do documento index.html:
 - <script src="https://maps.googleapis.com/maps/api/js?key=MINHA_KEY"></script> 
 Nessa linha onde está escrito MINHA_KEY deve ser adicionada uma key para a API do google maps.
