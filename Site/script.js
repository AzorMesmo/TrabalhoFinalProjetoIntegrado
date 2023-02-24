//mostra porfolio de cada tatuador

function mostrarLista() {
	var lista = document.getElementById("minhaLista");
	if (lista.style.display === "none") {
		lista.style.display = "block";
	} else {
		lista.style.display = "none";
	}
}

//tela inicial

window.addEventListener('load', function() {
  setTimeout(function() {
    var objeto = document.getElementById('inicial');
    objeto.style.transform = "translateY(-500px)";
  }, 500); // atraso de 500ms
});

//telinhas de login/cadastro/orçamentos

window.addEventListener('load', function() {
  setTimeout(function() {
    var objeto = document.querySelector('.login-screen');
    objeto.style.transform = "translateY(700px)";
  }, 500); // atraso de 500ms
});

//imagens em destque no index

var imagens = document.querySelectorAll('#galeria img');
var meio = Math.floor(imagens.length / 2); // índice da imagem do meio

for (var i = 0; i < imagens.length; i++) {
  if (i === meio) {
    imagens[i].classList.add('grande');
  } else {
    imagens[i].classList.add('pequena');
  }

  imagens[i].addEventListener('mouseover', function() {
    this.classList.add('grande');
  });

  imagens[i].addEventListener('mouseout', function() {
    this.classList.remove('grande');
  });
}
