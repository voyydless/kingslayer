package telas;

public class EstilizacaoTela {
    //Função de "limpar a tela" com múltiplos breaklines
    public static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    //Função para centralizar o texto
    public static void centralizar(String texto, int largura) {
        int espacos = (largura - texto.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, espacos)) + texto);
    }

    public static void linhas() {
        System.out.println("----------------------------------------------------------------------");
    }
}
