package br.com.wlbr.screensound.principal;

import br.com.wlbr.screensound.model.Artista;
import br.com.wlbr.screensound.model.Musica;
import br.com.wlbr.screensound.model.TipoArtista;
import br.com.wlbr.screensound.repository.ArtistaRepository;
import br.com.wlbr.screensound.service.ConsultaChaGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private ConsultaChaGPT consultaChaGPT;
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
                    4 - Buscar musicas por artistas
                    5 - Pesquisar dados do artista
                    
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
                    break;
                case 4:
                    buscarMusicaPorArtista();
                    break;
                case 5:
                    pesquisarDadosSobreArtista();
                    break;
            }
        }
    }

    private void pesquisarDadosSobreArtista() {
        System.out.println("Pesquisar dados sobre qual artista? ");
        var nome =  leitura.nextLine();
        var resposta = ConsultaChaGPT.ObterInformaçao(nome);
        System.out.println(resposta.trim());
    }

    private void buscarMusicaPorArtista() {
        System.out.println("Buscar musica de que artista? ");
        var nome = leitura.nextLine();
        List<Musica> musicas =  repositorio.buscaMusicaPorArtista(nome);
        musicas.forEach(System.out::println);
    }

    private void listarMusicas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(artista -> artista.getMusicas().forEach(System.out::println));
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
