package com.springframework.petclinic.services;

import com.springframework.petclinic.model.Vet;

import java.util.Set;

public interface VetService {
    String findById(Long Id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
