const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", () => {
    container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
    container.classList.remove("sign-up-mode");
});
document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    const resultDiv = document.getElementById('result');

    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        const credentials = {
            username: username,
            password: password
        };

        fetch('http://localhost:18080/Cliente/authenticate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        })
        .then(response => response.json())
        .then(data => {
            if (data.authenticated) {
                resultDiv.textContent = 'Inicio de sesión exitoso';
            } else {
                resultDiv.textContent = 'Credenciales inválidas';
            }
        })
        .catch(error => {
            console.error('Error:', error);
            resultDiv.textContent = 'Error al iniciar sesión';
        });
    });
});