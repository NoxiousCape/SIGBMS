
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

    loginForm.addEventListener('submit', async function(event) {
        event.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        const credentials = {
            username: username,
            password: password
        };

        try {
            const response = await fetch('http://localhost:18080/Cliente/authenticate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(credentials)
            });

            const data = await response.json();

            if (data.authenticated) {
                resultDiv.textContent = 'Inicio de sesión exitoso';
            } else {
                resultDiv.textContent = 'Credenciales inválidas';
            }
        } catch (error) {
            console.error('Error:', error);
            resultDiv.textContent = 'Error al iniciar sesión';
        }
    });

    $("#fechaDeNacimiento").datepicker({
        dateFormat: "yy/mm/dd",
        changeYear: true,
        yearRange: "1900:2023"
    });

    registerForm.addEventListener('submit', async function(event) {
        event.preventDefault();
        alert("SIFUNCIONAELHPTABOTON");
        const nombre = document.getElementById('Nombres').value;
        const apellido = document.getElementById('Apellidos').value;
        const usuario = document.getElementById('registerUsername').value;
        const password = document.getElementById('registerPassword').value;
        const fecha_Nacimiento = document.getElementById('fechaDeNacimiento').value;
        
       console.log(nombre);
       console.log(apellido);
       console.log(usuario);
       console.log(password);
       console.log(fecha_Nacimiento);


        const userData = {
            nombre:nombre,
            apellido:apellido,
            fecha_Nacimiento:fecha_Nacimiento,
            usuario:usuario,
            password:password,  
        };
    
        // Realiza la solicitud POST al backend para guardar el cliente
        fetch('http://localhost:18080/Cliente/register', {
            method:'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
        .then(response => response.json())
        .then(data => {
            // Verifica la respuesta del servidor
            if (data.message === 'Registro exitoso') {
                alert('Registro exitoso. Redirigiendo a la página de inicio de sesión.');
                // Puedes redirigir al usuario a la página de inicio de sesión aquí
                window.location.href = 'signin.html'; // Cambia la URL según tu estructura de carpetas
            } else {
                alert('Error en el registro. Por favor, inténtalo de nuevo.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al registrar. Por favor, inténtalo de nuevo.');
        });
    });

});
