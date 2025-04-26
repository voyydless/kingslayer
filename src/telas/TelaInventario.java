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
        EstilizacaoTela.limparTela();
        Scanner scanner = new Scanner(System.in);
        EstilizacaoTela.centralizar("--- Info ---", 70);
        jogador.exibirInformacoes();
        System.out.println();
        EstilizacaoTela.centralizar("Pressione ENTER para voltar", 70);
    }

}
