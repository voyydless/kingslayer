package util;

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
}
