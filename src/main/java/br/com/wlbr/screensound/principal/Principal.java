package br.com.wlbr.screensound.principal;

import br.com.wlbr.screensound.model.Artista;
import br.com.wlbr.screensound.model.Musica;
import br.com.wlbr.screensound.model.TipoArtista;
import br.com.wlbr.screensound.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistaRepository repositorio;
    private Scanner leitura =  new Scanner(System.in);

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0){
            var menu = """
                    * * * Screen Sound Music * * *
                    
                    1 - Cadastrar artistas
                    2 - Cadastrar musicas
                    3 - Listar musicas
                    
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch(opcao){
                case 1:
                    cadastrarArtistas();
                     break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
            }
        }
    }

    private void listarMusicas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(System.out::println);
    }

    private void cadastrarMusicas() {
        System.out.println("Cadastrar música de que artista? ");
        var nome = leitura.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);
        if (artista.isPresent()){
            System.out.println("Informe o titulo da música");
            var nomeMusica = leitura.nextLine();
            Musica musica =  new Musica(nomeMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            repositorio.save(artista.get());
        } else {
            System.out.println("artista não encontrado");
        }
    }

    private void cadastrarArtistas() {

        var cadastrarNovo = "S";
        while (cadastrarNovo.equalsIgnoreCase("S")) {
            System.out.println("Informe o nome desse artista: ");
            var nome = leitura.nextLine();
            System.out.println("informe o tipo desse artista : (solo, dupla ou banda) ");
            var tipo = leitura.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);
            repositorio.save(artista);
            System.out.println("Cadastrar novo artista? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }
    }
}
