package br.com.wlbr.screensound.principal;

import java.util.Scanner;

public class Principal {
    private Scanner leitura =  new Scanner(System.in);
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
        System.out.println();
    }
}
