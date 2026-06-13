package com.api_soundlist.SoundList.repository;

import com.api_soundlist.SoundList.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
}
