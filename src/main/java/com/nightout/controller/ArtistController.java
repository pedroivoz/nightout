package com.nightout.controller;

import com.nightout.model.Artist;
import com.nightout.model.User;
import com.nightout.repository.ArtistRepository;
import com.nightout.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artists")
@CrossOrigin(origins = "*")
public class ArtistController {

    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;

    public ArtistController(ArtistRepository artistRepository, UserRepository userRepository) {
        this.artistRepository = artistRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyArtistProfile(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return artistRepository.findByUser(user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Artist> createArtistProfile(Authentication auth, @RequestBody Artist artistRequest) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Artist artist = new Artist();
        artist.setStageName(artistRequest.getStageName());
        artist.setGenre(artistRequest.getGenre());
        artist.setBio(artistRequest.getBio());
        artist.setProfileImageUrl(artistRequest.getProfileImageUrl());
        artist.setUser(user);

        artistRepository.save(artist);

        return ResponseEntity.ok(artist);
    }
}
