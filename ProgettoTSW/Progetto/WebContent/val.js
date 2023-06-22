function Refresh(){
  window.location.reload(true);
}

function myFunction() {
  // Get the value of the input field with id="numb"
  let x = document.getElementById("numb").value;
  let y = document.getElementById("cardno").value;
  let z = document.getElementById("mail").value;
  let p = document.getElementById("pass").value;
  let d = document.getElementById("datasc").value;
  var m = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
  let text;

if(z.match(m)) {
 text = "TUTTO APPOSTP MARESCIAAA";
 document.getElementById("e").style.color = "#2AEE3E";
 document.getElementById("e").innerHTML = text;
} 
else{ 
 text = "E-mail non valida";
 document.getElementById("e").style.color = "#FF0000";
 document.getElementById("e").innerHTML = text;
}

if (isNaN(p) || p.length < 4){
 text = "Password troppo debole";
 document.getElementById("rer").style.color = "#FF0000";
 document.getElementById("rer").innerHTML = text;
}
else{ 
 text = "TUTTO APPOSTP MARESCIAAA";
 document.getElementById("rer").style.color = "#2AEE3E";
 document.getElementById("rer").innerHTML = text;
} 

if (isNaN(y) || y.length != 16){
 text = "Numero Carta non  valido";
 document.getElementById("er").style.color = "#FF0000";
 document.getElementById("er").innerHTML = text;
}
else{ 
 text = "TUTTO APPOSTP MARESCIAAA";
 document.getElementById("er").style.color = "#2AEE3E";
 document.getElementById("er").innerHTML = text;
}
 
if (isNaN(x) || x < 99 || x > 999) {
 text = "Numero CVC-CVV non valido";
 document.getElementById("err").style.color = "#FF0000";
 document.getElementById("err").innerHTML = text;
} 
else{ 
 text = "TUTTO APPOSTP MARESCIAAA";
 document.getElementById("err").style.color = "#2AEE3E";
 document.getElementById("err").innerHTML = text;
 }

if (!d){
 text = "Data non valida";
 document.getElementById("sos").style.color = "#FF0000";
 document.getElementById("sos").innerHTML = text;
}
else{ 
 text = "TUTTO APPOSTP MARESCIAAA";
 document.getElementById("sos").style.color = "#2AEE3E";
 document.getElementById("sos").innerHTML = text;
} 
}