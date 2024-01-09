package com.hotel.management.hotel.quarto.domain;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface QuartoRepository extends CrudRepository<Quarto, UUID> {
    
}
