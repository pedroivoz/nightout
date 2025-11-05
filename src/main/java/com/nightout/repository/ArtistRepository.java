package com.nightout.repository;

import com.nightout.model.Artist;
import com.nightout.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByUser(User user);
}
