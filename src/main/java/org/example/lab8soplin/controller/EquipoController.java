package org.example.lab8soplin.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.lab8soplin.entity.Equipo;
import org.example.lab8soplin.repository.EquipoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/equipo")
public class EquipoController {
    final EquipoRepository equipoRepository;
    public EquipoController(EquipoRepository equipoRepository) {this.equipoRepository = equipoRepository; }

    //LISTAR
    @GetMapping(value = {"/list", ""})
    public List<Equipo> listaEquipos() {
        return equipoRepository.findAll();
    }

    // CREAR
    @PostMapping(value = {"", "/"})
    public ResponseEntity<HashMap<String, Object>> guardarEquipo(
            @RequestBody Equipo equipo,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        equipoRepository.save(equipo);
        if (fetchId) {
            responseJson.put("id", equipo.getId());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionException(HttpServletRequest request) {
        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un equipo");
        }
        else {
            System.out.println(responseMap.get("timestamp"));
        }
        return ResponseEntity.badRequest().body(responseMap);
    }
}
