package personagens;

public class Personagem {
    private String nome;
    private int habilidade;
    private int energia;
    private int sorte;

    public Personagem(String N, int H, int E, int S){
        nome = N;
        habilidade = H;
        energia = E;
        sorte = S;
    }

    //Getters para poder utilizar estes atributos em outras classes
    public String getNome() {
        return nome;
    }

    public int getHabilidade() {
        return habilidade;
    }

    public int getEnergia() {
        return energia;
    }

    public int getSorte() {
        return sorte;
    }
}
