package br.com.wlbr.screensound.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne()
    private Artista artista;

    public Musica(){}

    public Musica(String nomeMusica) {
        this.titulo = nomeMusica;
    }

    @Override
    public String toString() {
        return
                "Musicas='" + titulo +'\'' +
                ", artista=" + artista;
    }
}
