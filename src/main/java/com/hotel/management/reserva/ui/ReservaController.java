package com.hotel.management.reserva.ui;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.management.hotel.quarto.domain.Quarto;
import com.hotel.management.hotel.quarto.domain.cmd.CriarQuarto;
import com.hotel.management.reserva.app.cmd.RealizarCheckin;
import com.hotel.management.reserva.domain.IReservaService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/reserva", produces = APPLICATION_JSON_VALUE)
public class ReservaController {

    private IReservaService service;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> reservar() {
        
        service.reservar(UUID.fromString("4ec0c255-b8a8-4f09-b131-bb6d1eb4d553"));

        return ResponseEntity.created(fromCurrentRequest()
                .path("/")
                .build().toUri())
                .build();

    }


    @PostMapping("/{id}/checkin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> realizarCheckin(@PathVariable UUID id) {
        
        service.realizarCheckin(id);

        return ResponseEntity.created(fromCurrentRequest()
                .path("/")
                .build().toUri())
                .build();

    }

}
