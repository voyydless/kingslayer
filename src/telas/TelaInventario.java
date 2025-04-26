package telas;
import jogabilidade.Jogo;
import personagens.Jogador;
import utilizavel.Item;

import java.util.Scanner;

//Responsável por exibir o processo de criação do personagem e permitir a visualização do inventário durante o jogo.
public class TelaInventario {
    //Inicia a criação do personagem, permitindo ao jogador distribuir pontos
    //entre habilidade, energia e sorte. Após a criação, define itens iniciais,
    //configura o jogador no jogo e inicia a primeira cena.
    public static void criacaoIncial() {
        Scanner scanner = new Scanner(System.in);
        EstilizacaoTela.limparTela();

        int pontosParaDistribuir = 12;
        int habilidade = 6;
        int energia = 12;
        int sorte = 6;

        //Habilidade
        EstilizacaoTela.centralizar("Você tem " + pontosParaDistribuir + " PONTOS para distribuir entre HABILIDADE, ENERGIA e SORTE", 68);
        EstilizacaoTela.centralizar("Pontos atuais:", 68);
        EstilizacaoTela.centralizar("HABILIDADE: " + habilidade +" | ENERGIA: " + energia + " | SORTE: " + sorte, 68);
        int pontosHabilidade;
        do {
            System.out.println("\nPontos para HABILIDADE: ");
            pontosHabilidade = scanner.nextInt();
            if (pontosHabilidade < 0 || pontosHabilidade > 6 || pontosHabilidade > pontosParaDistribuir) {
                System.out.println("Quantia de pontos inválida. Tente novamente.");
            }
        } while (pontosHabilidade < 0 || pontosHabilidade > 6 || pontosHabilidade > pontosParaDistribuir);
        habilidade += pontosHabilidade;
        pontosParaDistribuir -= pontosHabilidade;

        //Energia
        EstilizacaoTela.limparTela();
        EstilizacaoTela.centralizar("Você tem " + pontosParaDistribuir + " PONTOS para distribuir entre HABILIDADE, ENERGIA e SORTE", 68);
        EstilizacaoTela.centralizar("Pontos atuais:", 68);
        EstilizacaoTela.centralizar("HABILIDADE: " + habilidade +" | ENERGIA: " + energia + " | SORTE: " + sorte, 68);
        int pontosEnergia;
        do {
            System.out.println("\nPontos para ENERGIA: ");
            pontosEnergia = scanner.nextInt();
            if (pontosEnergia < 0 || pontosEnergia > 12 || pontosEnergia > pontosParaDistribuir) {
                System.out.println("Quantia de pontos inválida. Tente novamente.");
            }
        } while (pontosEnergia < 0 || pontosEnergia > 12 || pontosEnergia > pontosParaDistribuir);
        energia += pontosEnergia;
        pontosParaDistribuir -= pontosEnergia;

        //Sorte
        EstilizacaoTela.limparTela();
        EstilizacaoTela.centralizar("Você tem " + pontosParaDistribuir + " PONTOS para distribuir entre HABILIDADE, ENERGIA e SORTE", 68);
        EstilizacaoTela.centralizar("Pontos atuais:", 68);
        EstilizacaoTela.centralizar("HABILIDADE: " + habilidade +" | ENERGIA: " + energia + " | SORTE: " + sorte, 68);
        int pontosSorte;
        do {
            System.out.println("\nPontos para SORTE: ");
            pontosSorte = scanner.nextInt();
            if (pontosSorte < 0 || pontosSorte > 6 || pontosSorte > pontosParaDistribuir) {
                System.out.println("Quantia de pontos inválida. Tente novamente.");
            }
        } while (pontosSorte < 0 || pontosSorte > 6 || pontosSorte > pontosParaDistribuir);
        sorte += pontosSorte;

        //Criação do jogador em si
        EstilizacaoTela.limparTela();
        System.out.println("E o nome por qual será conhecido nesta aventura?");
        scanner.nextLine();
        String nome = scanner.nextLine();
        Jogador jogador = new Jogador(nome, habilidade, energia, sorte);

        //Itens iniciais
        Item espadaCurta = new Item("Espada curta", 'w', true, 1, 0);
        Item armaduraDeCouro = new Item("Armadura de Couro", 'r', false, 0, 0);
        jogador.getInventario().adicionarItem(espadaCurta);
        jogador.getInventario().adicionarItem(armaduraDeCouro);
        jogador.getInventario().adicionarTesouro(100);
        jogador.getInventario().adicionarProvisoes(1);
        jogador.equiparArma(espadaCurta);
        jogador.equiparArmadura(armaduraDeCouro);
        //Exibe os atributos e inventário do jogador
        jogador.exibirInformacoes();

        //Configura o jogador e inicia a primeira cena do jogo
        Jogo.setJogador(jogador);
        Jogo.setCenaAtual(1);
        TelaJogo.iniciar();
    }

    //Exibe as informações do personagem e seu inventário durante o jogo;
    //É acessível a qualquer momento digitando "i" nas cenas.
    public static void mostrarInventario(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        boolean voltar = false;

        while (!voltar) {
            EstilizacaoTela.limparTela();
            EstilizacaoTela.centralizar("--- Info ---", 70);
            jogador.exibirInformacoes();

            EstilizacaoTela.centralizar("Opções: ", 70);
            EstilizacaoTela.centralizar("'e' para equipar um item", 70);
            EstilizacaoTela.centralizar("'p' para usar uma provisão", 70);
            EstilizacaoTela.centralizar("'v' para voltar", 70);
            String opcao = scanner.nextLine();

            switch (opcao.toLowerCase()) {
                case "v":
                    voltar = true;
                    break;
                case "e":
                    menuEquipar(jogador);
                    break;
                case "p":
                    if (jogador.usarProvisao()) {
                        EstilizacaoTela.linhas();
                        EstilizacaoTela.centralizar("Você usou uma provisão e recuperou energia!", 70);
                        EstilizacaoTela.linhas();
                    } else {
                        EstilizacaoTela.linhas();
                        EstilizacaoTela.centralizar("Você não tem como usar uma provisão no momento.", 70);
                        EstilizacaoTela.linhas();
                    }
                    EstilizacaoTela.centralizar("Pressione ENTER para continuar", 70);
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("\nOpção inválida. Pressione ENTER para tentar novamente.");
                    scanner.nextLine();
            }
        }
    }

    public static void menuEquipar(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);

        EstilizacaoTela.linhas();
        EstilizacaoTela.centralizar("Escolha o que deseja equipar: ", 70);
        EstilizacaoTela.centralizar("1. Arma", 70);
        EstilizacaoTela.centralizar("2. Armadura", 70);
        EstilizacaoTela.centralizar(" --- Digite qualquer outra tecla para cancelar ---", 70);
        EstilizacaoTela.linhas();
        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                equiparArma(jogador);
                break;
            case "2":
                equiparArmadura(jogador);
                break;
            default:
                System.out.println("Voltando...");
                break;
        }

    }

    public static void equiparArma(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nArmas disponíveis: ");

        int i = 1;
        for (Item item : jogador.getInventario().getItens()) {
            if (item.getTipo() == 'w') {
                System.out.println(i + " - " + item.getNome());
            }
            i++;
        }

        System.out.println("Digite o número da arma que deseja equipar: ");
        int opcao = scanner.nextInt();
        i = 1;
        for (Item item : jogador.getInventario().getItens()) {
            if (item.getTipo() == 'w') {
                if (i == opcao) {
                    jogador.equiparArma(item);
                    return;
                }
                i++;
            }
        }
        System.out.println("Arma não encontrada.");
    }

    private static void equiparArmadura(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nArmaduras disponíveis:");

        int i = 1;
        for (Item item : jogador.getInventario().getItens()) {
            if (item.getTipo() == 'r') {
                System.out.println(i + " - " + item.getNome());
            }
            i++;
        }

        System.out.println("Digite o número da armadura que deseja equipar: ");
        int opcao = scanner.nextInt();
        i = 1;
        for (Item item : jogador.getInventario().getItens()) {
            if (item.getTipo() == 'r') {
                if (i == opcao) {
                    jogador.equiparArmadura(item);
                    return;
                }
                i++;
            }
        }

        System.out.println("Armadura não encontrada.");
    }

}

