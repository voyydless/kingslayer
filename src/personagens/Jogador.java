package personagens;

import utilizavel.Inventario;

public class Jogador extends Personagem {
    private Inventario inventario;

    public Jogador(String N, int H, int E, int S) {
        super(N, H, E, S);
        this.inventario = new Inventario();
    }

    public void exibirInformacoes() {
        System.out.println(getNome().toUpperCase());
        System.out.println("HABILIDADE: " + getHabilidade() +" | ENERGIA: " + getEnergia() + " | SORTE: " + getSorte());
        inventario.exibir();
    }

    public Inventario getInventario() {
        return inventario;
    }

}
