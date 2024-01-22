package com.hotel.management.reserva.domain;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ReservaRepository extends JpaRepository<Reserva, UUID>{
    List<Reserva> findByDataIs(LocalDate data);
}
