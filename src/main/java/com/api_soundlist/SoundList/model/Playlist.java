package com.api_soundlist.SoundList.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_playlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(length = 100)
    private String description;

    @OneToMany(
            mappedBy ="playlist",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Musica> musica = new ArrayList<>();

}
