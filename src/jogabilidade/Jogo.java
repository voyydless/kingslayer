package jogabilidade;

import personagens.Jogador;

import java.util.HashSet;
import java.util.Set;

public class Jogo {
    private static Jogador jogador;
    private static int cenaAtual= 1;
    private static Set<Integer> cenasVistas = new HashSet<>();


    public static void setJogador(Jogador j) {
        jogador = j;
    }
    public static Jogador getJogador(){
        return jogador;
    }

    public static void mostrarInventario() {
        if (jogador != null) {
            telas.TelaInventario.mostrarInventario(jogador);
        } else {
            System.out.println("Personagem ainda não foi criado!");
        }
    }
    public static int getCenaAtual(){
        return cenaAtual;
    }
    public static void setCenaAtual(int cena ){
        cenaAtual = cena;
        cenasVistas.add(cena);
        Arquivos.salvarJogo();
    }
    public static Set<Integer> getCenasVistas(){
        return cenasVistas;
    }
    public static void setCenasVistas(Set<Integer> cenas){
        cenasVistas = cenas;
    }
}
