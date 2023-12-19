package br.com.wlbr.screensound.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "musicas")
public class Musicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne()
    private Artista artista;

    @Override
    public String toString() {
        return
                "Musicas='" + titulo +'\'' +
                ", artista=" + artista;
    }
}
