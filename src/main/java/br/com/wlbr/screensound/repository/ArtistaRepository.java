package br.com.wlbr.screensound.repository;

import br.com.wlbr.screensound.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
