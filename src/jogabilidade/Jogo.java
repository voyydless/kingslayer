package jogabilidade;

import personagens.Jogador;

import java.util.*;

//Responsável por armazenar o estado global do jogo;
//Gerencia o jogador atual, a cena sendo exibida, e as cenas já visitadas.
//Também fornece acesso para leitura e salvamento automático de progresso.
public class Jogo {
    private static Jogador jogador;
    private static int cenaAtual= 1;
    private static Set<Integer> cenasVistas = new HashSet<>(); //Conjunto de números de cenas já visitadas

    //Define o jogador atual, é chamado após a criação de personagem ou carregamento do jogo.
    public static void setJogador(Jogador j) { jogador = j; }
    public static Jogador getJogador() { return jogador; }

    //Exibe o inventário do jogador na tela;
    //Caso tente ser chamado antes que o jogador tenha sido criado, exibe um aviso.
    public static void mostrarInventario() {
        if (jogador != null) {
            telas.TelaInventario.mostrarInventario(jogador);
        } else {
            System.out.println("Personagem ainda não foi criado!");
        }
    }

    //Getters e setters
    public static int getCenaAtual(){ return cenaAtual; }
    //Atualiza a cena atual e registra essa cena como "vista";
    //Após isso, salva automaticamente o progresso.
    public static void setCenaAtual(int cena ) {
        cenaAtual = cena;
        cenasVistas.add(cena);
        Arquivos.salvarJogo();
    }
    public static Set<Integer> getCenasVistas() { return cenasVistas; }
    public static void setCenasVistas(Set<Integer> cenas) { cenasVistas = cenas; }
}
