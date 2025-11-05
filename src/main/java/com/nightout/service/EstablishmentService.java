package com.nightout.service;

import com.nightout.model.Establishment;
import com.nightout.repository.EstablishmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstablishmentService {

    private final EstablishmentRepository repo;

    public EstablishmentService(EstablishmentRepository repo) {
        this.repo = repo;
    }

    public List<Establishment> findAll() { return repo.findAll(); }
    public Optional<Establishment> findById(Long id) { return repo.findById(id); }
    public Establishment save(Establishment e) { return repo.save(e); }

    public Establishment update(Long id, Establishment updated) {
        return repo.findById(id)
                .map(est -> {
                    est.setName(updated.getName());
                    est.setAddress(updated.getAddress());
                    est.setCity(updated.getCity());
                    est.setState(updated.getState());
                    est.setDescription(updated.getDescription());
                    est.setImageUrl(updated.getImageUrl());
                    return repo.save(est);
                })
                .orElseThrow(() -> new RuntimeException("Establishment not found"));
    }

    public void delete(Long id) { repo.deleteById(id); }
}
