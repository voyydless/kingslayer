package util;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<String> itens = new ArrayList<>();

    public void adicionarItem(String item) {
        itens.add(item);
    }

    public void exibir() {
        System.out.println("Itens: " + (itens.isEmpty() ? "Nenhum" : String.join(", ", itens)));
    }
}
