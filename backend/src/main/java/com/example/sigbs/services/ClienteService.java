package com.example.sigbs.services;

import com.example.sigbs.entities.Cliente;
import com.example.sigbs.repositories.IARepositoriCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {


    @Autowired
    private IARepositoriCliente iaRepositoriCliente;


    public Cliente saveCliente(Cliente cliente){
        iaRepositoriCliente.save(cliente);
        return cliente;
    }

    public List<Cliente> listClientes(){
        return  iaRepositoriCliente.findAll();
    }
    public boolean existsByIdCliente(Long idCliente) {
        return iaRepositoriCliente.existsById(Math.toIntExact(idCliente));
    }
    public Cliente authenticate(String username, String password) {
        Cliente cliente = iaRepositoriCliente.findByUsuario(username);
        if (cliente != null && cliente.getPassword().equals(password)) {
            return cliente;
        }
        return null;
    }

}
