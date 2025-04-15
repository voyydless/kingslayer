package jogabilidade;

import personagens.Jogador;

public class Jogo {
    private static Jogador jogador;

    public static void setJogador(Jogador j) {
        jogador = j;
    }

    public static Jogador getJogador() {
        return jogador;
    }

    public static void mostrarInventario() {
        if (jogador != null) {
            telas.TelaInventario.mostrarInventario(jogador);
        } else {
            System.out.println("Personagem ainda não foi criado!");
        }
    }
}
