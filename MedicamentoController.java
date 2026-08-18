package com.lembremed.controller;

import com.lembremed.dto.MedicamentoVisionDto;
import com.lembremed.model.Medicamento;
import com.lembremed.service.MedicamentoService;
import com.lembremed.service.VisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "*")
public class MedicamentoController {

    @Autowired private MedicamentoService service;
    @Autowired private VisionService visionService;

    @PostMapping("/analisar-foto")
    public ResponseEntity<MedicamentoVisionDto> analisarFoto(@RequestBody Map<String, String> payload) {
        return ResponseEntity.ok(visionService.analisarCaixaMedicamento(payload.get("imagemBase64")));
    }

    @PostMapping
    public ResponseEntity<Medicamento> criar(@RequestBody Medicamento med) {
        return ResponseEntity.ok(service.salvar(med));
    }
    
    // ... outros métodos (listar, marcarTomado, etc)
}
