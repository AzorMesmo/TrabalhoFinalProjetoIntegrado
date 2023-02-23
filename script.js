const loginScreen = document.querySelector('.login-screen');
      
window.addEventListener('load', () => {
  loginScreen.style.opacity = '0';
  loginScreen.style.transform = 'translateY(-100px)';

  setTimeout(() => {
    loginScreen.style.opacity = '1';
    loginScreen.style.transform = 'translateY(0)';
  }, 500);
});

function mostrarLista() {
	var lista = document.getElementById("minhaLista");
	if (lista.style.display === "none") {
		lista.style.display = "block";
	} else {
		lista.style.display = "none";
	}
}

