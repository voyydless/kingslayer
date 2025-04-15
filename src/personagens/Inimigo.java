package personagens;
import utilizavel.Item;

public class Inimigo extends Personagem{
    private int tesouro;
    private int provisoes;
    private Item item;

    public Inimigo(String N, int H, int E, int S, int tesouro, int provisoes, Item item) {
        super(N, H, E, S);
        this.tesouro = tesouro;
        this.provisoes = provisoes;
        this.item = item;
    }

    public void exibirInformacoes() {
        System.out.println(getNome().toUpperCase());
        System.out.println("HABILIDADE: " + getHabilidade() +" | ENERGIA: " + getEnergia() + " | SORTE: " + getSorte());
        System.out.println("ITENS:");
        System.out.println("- Provisões: " + provisoes);
        System.out.println("- Tesouro: " + tesouro);
        System.out.println("- " + item);
    }
}
