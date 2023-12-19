package br.com.wlbr.screensound.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoArtista tipo;
    @OneToMany(mappedBy = "artista")
    private List<Musica> musicas = new ArrayList<>();

    public Artista (){}

    public Artista(String nome, TipoArtista tipo) {
        this.nome =  nome;
        this.tipo = tipo;
    }
    @Override
    public String toString() {
        return
                "Artista='" + nome + '\'' +
                        ", tipo=" + tipo +
                        ", musicas=" + musicas;
    }
}
