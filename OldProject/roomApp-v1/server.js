const http = require('http');
const fs = require('fs');

const server = http.createServer(function(request, response) {
  let path = "public" + request.url;
  console.log(request.url);
  if(path === 'public/Raumliste.html'){
    response.write(baueRaumListe());
  }
  else if(path == 'public/'){
    var file = fs.readFileSync("public/dashboard.html",function(err,date){
      response.writeHead(200,{"content-type": "text/html; charset=utf-8"});
    });
    response.write(file);
  }
  else if(fs.existsSync(path)){
      var file = fs.readFileSync(path, function(err, data) {
        response.writeHead(200);
      });
      response.write(file);
  }
  else{
    var file = fs.readFileSync("public/404.html",function(err,data){
      response.writeHead(404,{"content-type": "text/html; charset=utf-8"});
    });
    response.write(file);
  }
  response.end();
}).listen(8020, function() {
  console.log("Ich bin da.");
});

function baueRaumListe(){
  return `<!DOCTYPE html>
  <html lang="en">
    <head>
      <meta charset="UTF-8" />
      <title>Praktikum</title>
      <link type="text/css" rel="stylesheet" href="css/style.css" />
      <link type="text/css" rel="stylesheet" href="css/flexbox.css" />
    </head>
    <body class="flexbox_Seite">
      <header>
        <a href="dashboard.html">
          <figure>
            <img src="img/Beispiel.jpg" alt="Logo.jpg" height="200" width="200" />
            <figcaption>Logo</figcaption>
          </figure>
        </a>
        <h1>Raumliste</h1>
      </header>
      <nav>
        <ul>
          <li>Seite 1: <a href="Raumliste.html">Raumliste</a></li>
          <li>Seite 2: <a href="Raumdetail.html">Raumdetail</a></li>
          <li>Seite 3: <a href="Buchungsdetail.html">Buchungsdetail</a></li>
          <li>Seite 4: <a href="Raumbuchung_Form.html">Buchungsformular</a></li>
        </ul>
      </nav>
      <div class="flexbox_Information">
        <main>
          <section class="flexbox_Inhalt">
            <h2>Raumsuche</h2>
            <form
              method="GET"
              action="https://labs.inf.fh-dortmund.de/roomReservationService/testRoomSearch.php"
            >
              <label class="requiredLabel">
                <input
                  name="roomno"
                  maxlength="6"
                  required
                  type="text"
                  autofocus
                  placeholder="z.B. A.E.01"
                  list="RÃ¤ume"
                  pattern="[A-C].[E123].[\\d]{2}"
                />
              </label>
              <datalist id="RÃ¤ume">
                ${createOptionList()}
              </datalist>
              <input type="submit" value="Finden" />
            </form>
          </section>
          <section>
            <h2>Raumliste</h2>
            <ul>
              ${createRaumList()}
            </ul>
          </section>
        </main>
        <aside>
          <h2>Benachrichtigungen</h2>
          <ul>
            <li>10.10.2019 Alle Beamer sind kaputt</li>
            <li>11.10.2019 Tageslichtprojektor als Ersatz geliefert</li>
          </ul>
        </aside>
      </div>
      <footer>&copy; by me</footer>
    </body>
  </html>`;
}

class Raum{
  constructor(Nummer, Bezeichnung, Gebaeude,Typ, Kapazitaet, Ausstattungsmerkmale){
      this.Nummer = Nummer;
      this.Bezeichnung = Bezeichnung;
      this.Gebaeude = Gebaeude;
      this.Typ = Typ;
      this.Kapazitaet = Kapazitaet;
      this.Ausstattungsmerkmale = Ausstattungsmerkmale;
      this.Buchungen = new Array();
  }
  addBuchungen(Buchung){
      this.Buchungen.push(Buchung);
      this.Buchungen.sort((a,b) => a.Startzeit-b.Startzeit);
      this.Buchungen.reverse();
  }
}

class Buchung{
  constructor(Bezeichnung, Startzeit, Endzeit, Gebucht_von, Anzahl_der_Teilnehmer, Beschreibung){
      this.Bezeichnung = Bezeichnung;
      this.Startzeit = new Date(Startzeit);
      this.Endzeit = new Date(Endzeit);
      this.Gebucht_von = Gebucht_von;
      this. Anzahl_der_Teilnehmer = Anzahl_der_Teilnehmer;
      this.Beschreibung = Beschreibung;
  }
}


var RaumListe = new Array();
Raum1 = new Raum(1, "A.E.01", "EF42","Doenerbude", 100, ["Beamer", "Tageslichtprojektor"]);
Raum2 = new Raum(2, "A.E.02", "EF42","Hoersaal", 50, ["Beamer"]);
RaumListe.push(Raum1);
RaumListe.push(Raum2);
Buchung1 = new Buchung(1, "December 17, 2019 15:21:00", "December 17, 2019 16:20:00", "Alex", 0, "Analysis 1");
Buchung2 = new Buchung(2, "December 16, 2019 10:00:00", "December 16, 2019 18:20:00", "Alex", 0, "Lineare Algebra 1");
Buchung3 = new Buchung(3, "December 11, 2019 12:20:00", "December 11, 2019 19:20:00", "Alex", 0, "Statistik 1");
Buchung4 = new Buchung(4, "December 11, 2019 12:45:00", "December 11, 2019 11:20:00", "Kevin", 0, "Nichts");
Buchung5 = new Buchung(5, "December 06, 2019 12:45:00", "December 06, 2019 11:20:00", "Kevin", 0, "Nichts");
Buchung6 = new Buchung(6, "December 02, 2019 12:45:00", "December 02, 2019 11:20:00", "Kevin", 0, "Nichts");
Raum1.addBuchungen(Buchung3);
Raum1.addBuchungen(Buchung1);
Raum1.addBuchungen(Buchung2);
Raum2.addBuchungen(Buchung4);
Raum2.addBuchungen(Buchung6);
Raum2.addBuchungen(Buchung5);



function createOptionList(){
  let result = "";
  RaumListe.forEach(element => {
    console.log(element.Bezeichnung);
    result += `<option>${element.Bezeichnung}</option>`
  });
  return result;
}
function createRaumList(){
  let result = "";
  RaumListe.forEach(element => {
    result += `<li><a href="Raumdetail.html">${element.Bezeichnung}</a>  ${element.Typ}   ${element.Gebaeude}</li>`
  });
  return result;
}