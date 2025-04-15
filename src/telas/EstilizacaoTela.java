package telas;

public class EstilizacaoTela {
    //"Limpar tela", como não é um comando que existe em Java. Testei algumas opções, mas só essa funcionou
    //Não fica muito clean, então se algum de vocês pensarem em algo melhor, fiquem a vontade para mudar!
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
}
