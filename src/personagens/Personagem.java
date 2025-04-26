package personagens;

//Classe base abstrata que representa um personagem do jogo;
//Estendida por classes como Jogador e Inimigo.
public class Personagem {
    private String nome;
    private int habilidade;
    private int energia;
    private int sorte;

    //Construtor de personagem
    public Personagem(String nome, int habilidade, int energia, int sorte){
        this.nome = nome;
        this.habilidade = habilidade;
        this.energia = energia;
        this.sorte = sorte;
    }

    //Getters
    public String getNome() { return nome; }
    public int getHabilidade() { return habilidade; }
    public int getEnergia() { return energia; }
    public int getSorte() { return sorte; }
    //Define uma nova energia;
    //Usado pelo jogador ao consumir provisões.
    public void setEnergia(int novaEnergia) { this.energia = novaEnergia; }
}
