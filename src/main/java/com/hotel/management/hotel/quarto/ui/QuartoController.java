package com.hotel.management.hotel.quarto.ui;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.management.hotel.quarto.domain.IQuartoService;
import com.hotel.management.hotel.quarto.domain.Quarto;
import com.hotel.management.hotel.quarto.domain.cmd.AtualizarQuarto;
import com.hotel.management.hotel.quarto.domain.cmd.CriarQuarto;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/quartos", produces = APPLICATION_JSON_VALUE)
public class QuartoController {

    private IQuartoService service;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> criarQuarto(@RequestBody CriarQuarto cmd) {
        Quarto quarto = service.criarQuarto(cmd);
        return ResponseEntity.created(fromCurrentRequest()
                .path("/").path(quarto.getId().toString()).build().toUri())
                .build();

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> atualizarQuarto(@PathVariable UUID id, @RequestBody AtualizarQuarto cmd) {
        service.atualizarQuarto(id, cmd);
        return ResponseEntity.created(fromCurrentRequest()
                .path("/").path(id.toString()).build().toUri())
                .build();

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletarQuarto(@PathVariable UUID id) {
        service.deletarQuarto(id);
    }
}
