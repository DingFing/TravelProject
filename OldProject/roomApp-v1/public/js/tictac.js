let ausgabe = "";
for(let i = 0; i <= 100; i++){
	if(i % 15 == 0) ausgabe += "TicTac ";
	else if (i % 3 == 0) ausgabe += "Tic ";
	else if (i % 5 == 0) ausgabe += "Tac ";
	else ausgabe += i + " ";
}
console.log(ausgabe);