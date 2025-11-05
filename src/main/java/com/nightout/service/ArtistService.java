package com.nightout.service;

import com.nightout.model.Artist;
import com.nightout.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    public Optional<Artist> findById(Long id) {
        return artistRepository.findById(id);
    }

    public Artist save(Artist artist) {
        return artistRepository.save(artist);
    }

    public Artist update(Long id, Artist updated) {
        return artistRepository.findById(id)
                .map(a -> {
                    a.setStageName(updated.getStageName());
                    a.setGenre(updated.getGenre());
                    a.setBio(updated.getBio());
                    a.setProfileImageUrl(updated.getProfileImageUrl());
                    return artistRepository.save(a);
                })
                .orElseThrow(() -> new RuntimeException("Artist not found"));
    }

    public void delete(Long id) {
        artistRepository.deleteById(id);
    }
}
