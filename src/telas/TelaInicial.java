package telas;
import java.util.Scanner;

public class TelaInicial {
    public static void titulo() {
        System.out.println("\n" +
                "   __ _______  _____________   _____  _________ \n" +
                "  / //_/  _/ |/ / ___/ __/ /  / _ \\ \\/ / __/ _ \\\n" +
                " / ,< _/ //    / (_ /\\ \\/ /__/ __ |\\  / _// , _/\n" +
                "/_/|_/___/_/|_/\\___/___/____/_/ |_|/_/___/_/|_| \n");
    }

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);

        titulo();
        EstilizacaoTela.centralizar("1. Novo Jogo", 45);
        EstilizacaoTela.centralizar("2. Continuar", 45);
        EstilizacaoTela.centralizar("3. Créditos", 45);
        EstilizacaoTela.centralizar("4. Encerrar", 45);
        EstilizacaoTela.centralizar("\nDigite uma opção para continuar:", 45);

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1: {
                EstilizacaoTela.limparTela();
                TelaInventario.criacaoIncial();
            }
            //case 2: carregar jogo
            case 3: {
                EstilizacaoTela.limparTela();
                titulo();
                EstilizacaoTela.centralizar("Desenvolvido por:", 45);
                EstilizacaoTela.centralizar("Gabriele nome inteiro", 45);
                EstilizacaoTela.centralizar(" Markus nome inteiro", 45);
                EstilizacaoTela.centralizar("Sabrina nome inteiro", 45);
                EstilizacaoTela.centralizar("\nPressione uma tecla para voltar o menu", 45);
                scanner.nextLine();
                scanner.nextLine();
                exibir();
            }
            case 4: {
                EstilizacaoTela.limparTela();
                System.out.println("Encerrando...");
                System.exit(0);
            }
            default:
                EstilizacaoTela.limparTela();
                System.out.println("Opção inválida!");
                exibir();
        }

    }
}
