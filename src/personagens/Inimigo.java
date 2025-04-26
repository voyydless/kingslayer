package personagens;
import telas.EstilizacaoTela;
import utilizavel.Item;

//Representa um inimigo encontrado em cenas de combate;
//Herda de Personagem e pode conter tesouro, provisões e um item.
public class Inimigo extends Personagem{
    private int tesouro;
    private int provisoes;
    private Item item;

    //Construtor do inimigo
    public Inimigo(String nome, int habilidade, int energia, int sorte, int tesouro, int provisoes, Item item) {
        super(nome, habilidade, energia, sorte);
        this.tesouro = tesouro;
        this.provisoes = provisoes;
        this.item = item;
    }

    //Exibe as informações do inimigo no console
    public void exibirInformacoes() {
        EstilizacaoTela.linhas();
        EstilizacaoTela.centralizar(getNome().toUpperCase(), 70);
        System.out.println("HABILIDADE: " + getHabilidade() +" | ENERGIA: " + getEnergia() + " | SORTE: " + getSorte());
        System.out.println("ITENS:");
        System.out.println("- Provisões: " + provisoes);
        System.out.println("- Tesouro: " + tesouro);
        if (item != null) {
            System.out.println("- " + item);
        } else {
            System.out.println("- Não há itens.");
        }
        EstilizacaoTela.linhas();
    }

    //Getters
    public int getTesouro() { return tesouro; }

    public int getProvisoes() { return provisoes; }

    public Item getItem() { return item; }
}
