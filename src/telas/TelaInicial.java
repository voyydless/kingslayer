package telas;
import jogabilidade.Arquivos;
import jogabilidade.Jogo;

import java.util.Scanner;

//A primeira tela exibida ao jogador ao iniciar o jogo;
//Permite escolher entre iniciar um novo jogo, continuar de onde parou, visualizar os créditos ou encerrar o programa.
public class TelaInicial {
    //Título estilizado
    public static void titulo() {
        System.out.println("\n" +
                "   __ _______  _____________   _____  _________ \n" +
                "  / //_/  _/ |/ / ___/ __/ /  / _ \\ \\/ / __/ _ \\\n" +
                " / ,< _/ //    / (_ /\\ \\/ /__/ __ |\\  / _// , _/\n" +
                "/_/|_/___/_/|_/\\___/___/____/_/ |_|/_/___/_/|_| \n");
    }

    public static void exibir() {
        EstilizacaoTela.limparTela();
        Scanner scanner = new Scanner(System.in);

        titulo();
        EstilizacaoTela.centralizar("1. Novo Jogo", 45);
        EstilizacaoTela.centralizar("2. Continuar", 45);
        EstilizacaoTela.centralizar("3. Créditos", 45);
        EstilizacaoTela.centralizar("4. Encerrar", 45);
        EstilizacaoTela.centralizar("\nDigite uma opção para continuar:", 45);

        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1": {
                //Inicia a criação do personagem e começa um novo jogo
                EstilizacaoTela.limparTela();
                TelaInventario.criacaoIncial();
                return;
            }
            case "2": {
                //Carrega o progresso salvo do jogador
                EstilizacaoTela.limparTela();
                Arquivos.carregarJogo();
                System.out.println("\nJogo carregado com sucesso! Cena atual: " + Jogo.getCenaAtual());
                System.out.println("Pressione ENTER para começar");
                scanner.nextLine();
                TelaJogo.iniciar();
                return;
            }
            case "3": {
                //Exibe os créditos
                EstilizacaoTela.limparTela();
                titulo();
                EstilizacaoTela.centralizar("Desenvolvido por:", 45);
                EstilizacaoTela.centralizar("Gabriele Santos de Azevedo", 45);
                EstilizacaoTela.centralizar("Markus Lopes", 45);
                EstilizacaoTela.centralizar("Sabrina Kimberly Rodrigues Ford", 45);
                System.out.println();
                EstilizacaoTela.centralizar("Pressione ENTER para voltar o menu", 45);
                scanner.nextLine();
                scanner.nextLine();
                exibir();
                break;
            }
            case "4": {
                //Encerra o programa
                EstilizacaoTela.limparTela();
                System.out.println("Encerrando...");
                System.exit(0);
                break;
            }
            default:
                //Opção inválida, reinicia o menU
                EstilizacaoTela.limparTela();
                System.out.println("Opção inválida!");
                exibir();
        }

    }
}
