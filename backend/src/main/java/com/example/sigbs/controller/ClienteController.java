package com.example.sigbs.controller;

import com.example.sigbs.entities.Cliente;
import com.example.sigbs.repositories.IARepositoriCliente;
import com.example.sigbs.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/Cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private IARepositoriCliente iaRepositoriCliente;

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Busca al cliente por nombre de usuario en la base de datos
        Cliente cliente = iaRepositoriCliente.findByUsuario(username);

        if (cliente != null) {
            // Compara el hash de la contraseña proporcionada con el hash almacenado
            if (BCrypt.checkpw(password, cliente.getPassword())) {
                // La contraseña es correcta, autenticación exitosa
                Map<String, Object> response = new HashMap<>();
                response.put("authenticated", true);
                response.put("cliente", cliente);
                return ResponseEntity.ok(response);
            }
        }

        // Autenticación fallida
        Map<String, Object> response = new HashMap<>();
        response.put("authenticated", false);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saveCliente")
    public Cliente saveCliente(@RequestBody Cliente cliente){
        System.out.println("id_cliente recibido en el backend: " + cliente.getIdCliente());
        return clienteService.saveCliente(cliente);
    }

    @GetMapping("/listClientes")
    public List<Cliente> listClientes(){
        return clienteService.listClientes();
    }

    @GetMapping("/checkUniqueness/{idCliente}")
    public ResponseEntity<Map<String, Boolean>> checkIdUniqueness(@PathVariable Long idCliente) {
        boolean isUnique = !clienteService.existsByIdCliente(idCliente);

        Map<String, Boolean> response = new HashMap<>();
        response.put("isUnique", isUnique);

        return ResponseEntity.ok(response);
     }
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Cliente userData) {
        try {
            // Realiza el hash de la contraseña antes de guardarla
            String hashedPassword = BCrypt.hashpw(userData.getPassword(), BCrypt.gensalt());
            userData.setPassword(hashedPassword);
            // Establece el rol por defecto como "cliente"
            userData.setRol("cliente");

            // Imprime los valores para verificar antes de guardar
            System.out.println("Nombre: " + userData.getNombre());
            System.out.println("Apellidos: " + userData.getApellido());
            System.out.println("Fecha de Nacimiento: " + userData.getFecha_Nacimiento());
            System.out.println("Usuario: " + userData.getUsuario());
            System.out.println("Password: " + userData.getPassword());



                    // Guarda el usuario en la base de datos
            clienteService.saveCliente(userData);

            // Devuelve una respuesta JSON con un mensaje de éxito
            Map<String, String> response = new HashMap<>();
            response.put("message", "Registro exitoso");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // En caso de error, devuelve una respuesta JSON con un mensaje de error
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al registrar el usuario");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
