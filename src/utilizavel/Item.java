package utilizavel;

public class Item {
    private String nome;
    private char tipo; // c: comum, r: armadura, w: arma
    private boolean combate;
    private int fa;
    private int dano;

    public Item(String nome, char tipo, boolean combate, int fa, int dano) {
        this.nome = nome;
        this.tipo = tipo;
        this.combate = combate;
        this.fa = fa;
        this.dano = dano;
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

    public int getDano() {
        return dano;
    }


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
        return nome + " (" + tipoNome + ") | Combate: " + (combate ? "1" : "0") + " | FA: " + fa + " | Dano Bônus: " + dano;
    }
}
