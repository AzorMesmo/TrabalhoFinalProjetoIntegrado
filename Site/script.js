function mostrarLista() {
	var lista = document.getElementById("minhaLista");
	if (lista.style.display === "none") {
		lista.style.display = "block";
	} else {
		lista.style.display = "none";
	}
}

window.addEventListener('load', function() {
  setTimeout(function() {
    var objeto = document.getElementById('inicial');
    objeto.style.transform = "translateY(500px)";
  }, 500); // atraso de 500ms
});

window.addEventListener('load', function() {
  setTimeout(function() {
    var objeto = document.querySelector('.login-screen');
    objeto.style.transform = "translateY(700px)";
  }, 500); // atraso de 500ms
});