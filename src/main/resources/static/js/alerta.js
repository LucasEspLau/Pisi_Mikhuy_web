
let alertaBtn = document.getElementById("alertaPremio");
let tapaBtn = document.getElementById("tapa");
var premio;
var cont=0;


function elegirPremio(){
		cont=0;
		var ale=parseInt(Math.random()*(4-0+1)+0);
		if(ale==0) {
			premio='Descuento 2%';
		}else if(ale==1) {
			premio='Descuento 4%';
		}else if(ale==2) {
			premio='Descuento 6%';
		}else if(ale==3) {
			premio='Descuento 8%';
		}else if(ale==4) {
			premio='Descuento 10%';
		}
}

const alert = (message, type) => {
  const wrapper = document.createElement('div')
  wrapper.innerHTML = [
    `<div class="alert alert-${type} alert-warning" role="alert">`,
   `   <div>${message}</div>`,
   
` <a class="nav-link " href="/" >Guardar Premio</a>`,

    '</div>'
  ].join('')
	if(cont==0){
			fetch('/premio', {
			  method: 'POST',
			  body: JSON.stringify(premio),
			  headers: {
			    'Content-Type': 'application/json'
			  }
			})
		 alertaBtn.append(wrapper)
		 
		 console.log(message);
		 contador++;
	}

}
if (alertaBtn) {
  alertaBtn.addEventListener('click', () => {
    alert(premio, 'success')
  })
}
	elegirPremio();
alertaBtn.onclick = function() {
	

    tapaBtn.src = "images/imagesPremio/caja.png";
    return premio;
}
