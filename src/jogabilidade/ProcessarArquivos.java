package jogabilidade;

import personagens.Inimigo;
import utilizavel.Item;

import java.util.*;

//Representa uma cena do jogo, contendo o texto narrativo, opções de escolha e transições de cena,
//informações de combate (se tiver), e modificações de inventário.
public class ProcessarArquivos {
    private int numeroCena;
    private String texto;
    private Map<Integer, String> opcoes = new LinkedHashMap<>(); //Mapeamento das opções do jogador
    private boolean combate = false;
    private Inimigo inimigo;
    private Item itemRecebido;
    private int modificadorTesouro = 0;
    private int modificadorProvisoes = 0;
    private int cenaVitoria;
    private int cenaDerrota;

    //Getters e setters
    public int getNumeroCena() { return numeroCena; }
    public void setNumeroCena(int numeroCena) { this.numeroCena = numeroCena; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public Map<Integer, String> getOpcoes() { return opcoes; }
    public void adicionarOpcao(int destino, String descricao) {
        opcoes.put(destino, descricao);
    }

    public boolean isCombate() { return combate; }
    public void setCombate(boolean combate) { this.combate = combate; }

    public Inimigo getInimigo() {return inimigo; }
    public void setInimigo(Inimigo inimigo) { this.inimigo = inimigo; }

    public Item getItemRecebido() { return itemRecebido; }
    public void setItemRecebido(Item itemRecebido) { this.itemRecebido = itemRecebido; }

    public int getModificadorTesouro() { return modificadorTesouro; }
    public void setModificadorTesouro(int valor) { this.modificadorTesouro = valor; }

    public int getModificadorProvisoes() { return modificadorProvisoes; }
    public void setModificadorProvisoes(int quantia) { this.modificadorProvisoes = quantia;}

    public int getCenaVitoria() { return cenaVitoria; }
    public void setCenaVitoria(int cenaVitoria) { this.cenaVitoria = cenaVitoria; }

    public int getCenaDerrota() { return cenaDerrota; }
    public void setCenaDerrota(int cenaDerrota) { this.cenaDerrota = cenaDerrota; }

}
