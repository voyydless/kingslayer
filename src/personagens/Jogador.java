package personagens;

import telas.EstilizacaoTela;
import utilizavel.Inventario;
import utilizavel.Item;

//Representa o jogador/personagem principal do jogo.
//Extende a classe Personagem e adiciona um inventário, energia máxima e métodos especiais.
public class Jogador extends Personagem {
    private Inventario inventario;
    private int energiaMaxima;

    //Construtor do jogador
    public Jogador(String nome, int habilidade, int energia, int sorte) {
        super(nome, habilidade, energia, sorte);
        this.inventario = new Inventario();
        this.energiaMaxima = energia; //Energia máxima é a energia inicial definida pelo jogador
    }

    //Funcionalidade incompleta!!
    //Usa uma provisão do inventário;
    //Restaura até 4 pontos de energia, respeitando o limite da energia máxima.
    public boolean usarProvisao() {
        if (inventario.getProvisoes() > 0) {
            inventario.adicionarProvisoes(-1);
            int energiaAntes = getEnergia();
            setEnergia(Math.min(energiaAntes + 4, energiaMaxima));
            return true;
        } else {
            return false;
        }
    }

    //Exibe as informações do jogador no console
    public void exibirInformacoes() {
        EstilizacaoTela.linhas();
        EstilizacaoTela.centralizar(getNome().toUpperCase(), 70);
        System.out.println("HABILIDADE: " + getHabilidade() +" | ENERGIA: " + getEnergia() + " | SORTE: " + getSorte());
        inventario.exibir();
        EstilizacaoTela.linhas();
    }

    //Getters e setter
    public Inventario getInventario() { return inventario; }
    public int getEnergiaMaxima() { return energiaMaxima; }
    public void setEnergiaMaxima(int energiaMaxima) { this.energiaMaxima = energiaMaxima; }

    }
