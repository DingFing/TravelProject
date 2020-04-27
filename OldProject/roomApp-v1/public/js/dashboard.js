let kachelBereich = document.querySelector(".startseite_Flexbox");
let btn = document.createElement("Button");
btn.innerText = "+";
btn.className = "startseite_Navigation";

kachelBereich.appendChild(btn);

var array = [];
if(localStorage.getItem("array", JSON.stringify(array)) !== null){
	array = JSON.parse(localStorage.getItem("array"));
	for(let i = 0;i<array.length;i++){
        createKachel(array[i],array[++i]);
	}
}
btn.onclick = function(){
	let name = prompt("Geben Sie den Namen ein!");
    let link = prompt("Geben Sie den Link ein!");
    if(localStorage.setItem("array", JSON.stringify(array)) !== null){
		array = JSON.parse(localStorage.getItem("array"));
	}
    array.push(name,link);
    localStorage.setItem("array", JSON.stringify(array));
    createKachel(name, link);
}

function createKachel(name, link){
    let kachel = document.createElement("a");
    kachel.className = "startseite_Navigation";
    kachel.textContent = `${name}`;
    kachel.href = `${link}`;
    kachelBereich.lastChild.before(kachel);
}