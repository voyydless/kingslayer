package utilizavel;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Item> itens = new ArrayList<>();
    private int provisoes = 0;
    private int tesouro = 0;

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public void adicionarProvisoes(int quantidade) {
        provisoes += quantidade;
    }

    public void adicionarTesouro(int quantidade) {
        tesouro += quantidade;
    }

    public boolean temItemComNome(String nome) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public void exibir() {
        System.out.println("ITENS:");
        System.out.println("- Provisões: " + provisoes);
        System.out.println("- Tesouro: " + tesouro);
        if (itens.isEmpty()) {
            System.out.println("- Nenhum item a mais");
        } else {
            for (Item item : itens) {
                System.out.println("- " + item);
            }
        }
    }

    public List<Item> getItens() {
        return itens;
    }

    public int getProvisoes() {
        return provisoes;
    }

    public int getTesouro() {
        return tesouro;
    }
}
