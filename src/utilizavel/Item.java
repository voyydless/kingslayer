package utilizavel;

public class Item {
    private String nome;
    private char tipo; // c: comum, r: armadura, w: arma
    private boolean combate;
    private int fa;
//    private int dano;
    //Vou mandar a real. Entendi o que é fa (bônus atribuído a H do personagem em batalha,
    //mas não tenho a mínima ideia do que seja dano.
    //Na teoria faz sentido, mas como o bombate inteiro é ditado por sorte
    //(valor de 1 a 10 é adicionado na habilidade do jogador e do inimigo, quem tiver o maior fa
    //é o vencedor e tira 2 pontos de energia do oponente... então qual o sentido de dano???

    public Item(String nome, char tipo, boolean combate, int fa) {
        this.nome = nome;
        this.tipo = tipo;
        this.combate = combate;
        this.fa = fa;
//        this.dano = dano;
    }

    public String getNome() {
        return nome;
    }

    public char getTipo() {
        return tipo;
    }

    public boolean isCombate() {
        return combate;
    }

//    public int getDano() {
//        return dano;
//    }


    @Override
    public String toString() {
        String tipoNome;
        switch (tipo) {
            case 'w':
                tipoNome = "Arma";
                break;
            case 'r':
                tipoNome = "Armadura";
                break;
            case 'c':
                tipoNome = "Comum";
                break;
            default:
                tipoNome = "???";
        }
        return nome + " (" + tipoNome + ") | Combate: " + (combate ? "1" : "0") + " | FA: " + fa;
        //depois de entender o que é dano, incluir: + " | Dano: " + dano
    }
}
