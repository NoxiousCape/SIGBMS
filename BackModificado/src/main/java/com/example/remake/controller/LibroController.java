package com.example.remake.controller;

import com.example.remake.entities.Libro;
import com.example.remake.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository; // Un repositorio de Spring Data MongoDB

    @PostMapping("/cargar")
    public Libro cargarLibro(@RequestBody Libro libro) {
        // Aquí debes implementar la lógica para cargar el PDF y guardar la información en MongoDB
        // Guarda la ruta o identificador del archivo PDF en el campo "archivoPDF" de la entidad "Libro"
        return libroRepository.save(libro);
    }

//    @GetMapping("/{id}/mostrar")
//    public ResponseEntity<byte[]> mostrarLibro(@PathVariable String id) {
//        // Aquí debes implementar la lógica para recuperar y mostrar el PDF del libro
//        // Consulta la entidad "Libro" por ID y obtén la ruta o identificador del archivo PDF
//        // Lee el archivo PDF y devuelve su contenido en la respuesta HTTP
//        // Asegúrate de configurar el encabezado adecuado para indicar que es un archivo PDF
//        // También maneja errores si el libro no se encuentra o el archivo no existe
//
//    }
}