package personagens;

import telas.EstilizacaoTela;
import utilizavel.Inventario;
import utilizavel.Item;

//Representa o jogador/personagem principal do jogo.
//Extende a classe Personagem e adiciona um inventário, energia máxima e métodos especiais.
public class Jogador extends Personagem {
    private Inventario inventario;
    private int energiaMaxima;
    private Item armaEquipada;
    private Item armaduraEquipada;

    //Construtor do jogador
    public Jogador(String nome, int habilidade, int energia, int sorte) {
        super(nome, habilidade, energia, sorte);
        this.inventario = new Inventario();
        this.energiaMaxima = energia; //Energia máxima é a energia inicial definida pelo jogador
    }

    public void equiparArma(Item arma) {
        if (arma != null && arma.getTipo() == 'w') {
            armaEquipada = arma;
            System.out.println("Você equipou: " + armaEquipada.getNome());
        } else {
            System.out.println("Esse item não é uma arma!");
        }
    }

    public void equiparArmadura(Item armadura) {
        if (armadura != null && armadura.getTipo() == 'r') {
            armaduraEquipada = armadura;
            System.out.println("Você equipou: " + armaduraEquipada.getNome());
        } else {
            System.out.println("Esse item não é uma armadura!");
        }
    }

    //Usa uma provisão do inventário;
    //Restaura até 4 pontos de energia, respeitando o limite da energia máxima.
    public boolean usarProvisao() {
        if (inventario.getProvisoes() > 0 && getEnergia() < getEnergiaMaxima()) {
            inventario.adicionarProvisoes(-1);
            int energiaAntes = getEnergia();
            setEnergia(Math.min(energiaAntes + 4, energiaMaxima));
            return true;
        } else {
            return false;
        }
    }

    public boolean testarSorte() {
        if (getSorte() <= 0) {
            System.out.println("Sua sorte já acabou.");
            return false;
        }
        int dado = (int) (Math.random() * 12) + 1; //Gera número de 1 a 12
        System.out.println("Você rolou: " + dado);

        boolean sucesso = dado <= getSorte();

        setSorte(getSorte() - 1);
        return sucesso;
    }

    //Exibe as informações do jogador no console
    public void exibirInformacoes() {
        EstilizacaoTela.linhas();
        EstilizacaoTela.centralizar(getNome().toUpperCase(), 70);
        System.out.println("HABILIDADE: " + getHabilidade() +" | ENERGIA: " + getEnergia() + " | SORTE: " + getSorte());
        if (armaEquipada != null) {
            System.out.println("Arma equipada: " + armaEquipada.getNome());
        } else {
            System.out.println("Arma equipada: Nenhuma");
        }

        if (armaduraEquipada != null) {
            System.out.println("Armadura equipada: " + armaduraEquipada.getNome());
        } else {
            System.out.println("Armadura equipada: Nenhuma");
        }
        inventario.exibir();
        EstilizacaoTela.linhas();
    }

    //Getters e setter
    public Inventario getInventario() { return inventario; }
    public int getEnergiaMaxima() { return energiaMaxima; }
    public Item getArmaEquipada() { return armaEquipada; }
    public Item getArmaduraEquipada() { return armaduraEquipada; }
    public void setEnergiaMaxima(int energiaMaxima) { this.energiaMaxima = energiaMaxima; }

}
