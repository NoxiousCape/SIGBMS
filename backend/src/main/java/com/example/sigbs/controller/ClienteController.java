package com.example.sigbs.controller;

import com.example.sigbs.entities.Cliente;
import com.example.sigbs.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/Cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;


    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Cliente>> authenticate(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Assuming you have a service method to authenticate the user
        Cliente authenticated = clienteService.authenticate(username, password);

        Map<String, Cliente> result = new HashMap<>();
        result.put("authenticated", authenticated);

        return ResponseEntity.ok(result);
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
