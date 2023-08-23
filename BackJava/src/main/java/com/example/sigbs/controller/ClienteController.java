package com.example.sigbs.controller;

import com.example.sigbs.entities.Cliente;
import com.example.sigbs.services.ClienteService;
import com.example.sigbs.util.AuthResponse;
import com.example.sigbs.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/Cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil; // Asegúrate de tener esta clase configurada

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Cliente cliente = clienteService.authenticate(username, password);

        if (cliente != null) {
            final String token = jwtTokenUtil.generateToken(cliente.getUsuario());
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
    @PostMapping("/saveCliente")
    public Cliente saveCliente(@RequestBody Cliente cliente){
        return clienteService.saveCliente(cliente);
    }

    @GetMapping("/listClientes")
    public List<Cliente> listClientes(){
        return clienteService.listClientes();
    }
}
