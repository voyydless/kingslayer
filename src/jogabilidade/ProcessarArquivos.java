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
    private boolean testeDeSorte = false;
    private int cenaSucessoSorte;
    private int cenaFracassoSorte;
    private String descricaoSucessoSorte;
    private String descricaoFracassoSorte;
    private Map<Integer, String> requisitosItem = new HashMap<>();
    private boolean fimDoJogo = false;
    private boolean removerItens = false;

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

    public boolean isTesteDeSorte() { return testeDeSorte; }
    public void setTesteDeSorte(boolean testeDeSorte) { this.testeDeSorte = testeDeSorte; }

    public int getCenaSucessoSorte() { return cenaSucessoSorte; }
    public void setCenaSucessoSorte(int cenaSucessoSorte) { this.cenaSucessoSorte = cenaSucessoSorte; }

    public int getCenaFracassoSorte() { return cenaFracassoSorte; }
    public void setCenaFracassoSorte(int cenaFracassoSorte) { this.cenaFracassoSorte = cenaFracassoSorte; }

    public String getDescricaoSucessoSorte() { return descricaoSucessoSorte; }
    public void setDescricaoSucessoSorte(String descricao) { this.descricaoSucessoSorte = descricao; }

    public String getDescricaoFracassoSorte() { return descricaoFracassoSorte; }
    public void setDescricaoFracassoSorte(String descricao) { this.descricaoFracassoSorte = descricao; }

    public void adicionarRequisito(int cena, String itemNome) { requisitosItem.put(cena, itemNome); }
    public String getRequisitoItem(int cena) { return requisitosItem.get(cena); }
    public boolean temRequisito(int cena) { return requisitosItem.containsKey(cena); }

    public boolean isFimDoJogo() { return fimDoJogo; }
    public void setFimDoJogo(boolean fimDoJogo) { this.fimDoJogo = fimDoJogo; }

    public boolean isRemoverItens() { return removerItens; }
    public void setRemoverItens(boolean remover) { this.removerItens = remover; }
}
