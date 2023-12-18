package br.com.wlbr.screensound.principal;

import br.com.wlbr.screensound.model.Artista;
import br.com.wlbr.screensound.model.TipoArtista;
import br.com.wlbr.screensound.repository.ArtistaRepository;

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
                    
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch(opcao){
                case 1:
                    cadastrarArtistas();
                     break;
                case 2:
            }
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
