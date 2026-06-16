package com.api_soundlist.SoundList.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_musicas")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String title;
    @Column(length = 100)
    private String artist;
    @Column(length = 100)
    private String genre;
    @Column(nullable = false)
    private Integer duration;
    @ManyToOne(fetch =  FetchType.LAZY, optional = false)
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;
}
