function getViewPortWidth(){
    return window.innerWidth || document.documentElement.clientWidth;
}
console.log(`Die Viewport-Breite beträgt: ${getViewPortWidth()} Pixel.`);

class Raum{
    constructor(Nummer, Bezeichnung, Gebäude, Typ, Kapazität, Ausstattungsmerkmale, Buchungen){
        this.Nummer = Nummer;
        this.Bezeichnung = Bezeichnung;
        this.Gebäude = Gebäude;
        this.Typ = Typ;
        this.Kapazität = Kapazität;
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


Raum1 = new Raum(1, "A.E.01", "EF42","Dönerbude", 100, ["Beamer", "Tageslichtprojektor"]);
Raum2 = new Raum(2, "A.E.02", "EF42","Hörsaal", 50, ["Beamer"]);
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

/*Raum1.Buchungen.forEach(element => {
    console.log(element.Startzeit);
});
Raum2.Buchungen.forEach(element => {
    console.log(element.Startzeit);
});
document.querySelector("main.grid_Hauptinhalt section ul").innerHTML = `<li> Raumnummer: ${Raum1.Bezeichnung}</li>`;
*/

if(document.querySelector("main.grid_Hauptinhalt section ul") !== null){
    let liste = document.querySelector("main.grid_Hauptinhalt section ul");
    for(element in Raum1){
        if(element !== "Buchungen"){
            let li = document.createElement("li");
            li.appendChild(document.createTextNode(`${element}: ${Raum1[element]}`));
            liste.appendChild(li);
        }
    }
}

if(document.querySelector("table#buchungen") !== null){
    let tabelle = document.querySelector("table#buchungen");
    for(element in Raum1.Buchungen){
        let newRow = tabelle.insertRow(1);
        let datum = newRow.insertCell(0);
        let start = newRow.insertCell(1);
        let ende = newRow.insertCell(2);
        let bezeichnung = newRow.insertCell(3);
        datum.innerHTML = `${Raum1.Buchungen[element].Startzeit.toLocaleDateString()}`;
        start.innerHTML = `${Raum1.Buchungen[element].Startzeit.toLocaleTimeString()}`;
        ende.innerHTML = `${Raum1.Buchungen[element].Endzeit.toLocaleTimeString()}`;
        bezeichnung.innerHTML = `<a href="Buchungsdetail.html">${Raum1.Buchungen[element].Beschreibung}</a>`;
    }
}