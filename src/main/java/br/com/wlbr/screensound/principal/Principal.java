package br.com.wlbr.screensound.principal;

import java.util.Scanner;

public class Principal {
    private Scanner leitura =  new Scanner(System.in);
    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0){
            var menu = """
                    
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
        }
    }
}
