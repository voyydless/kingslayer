import jogabilidade.Jogo;
import personagens.Inimigo;
import personagens.Jogador;
import telas.TelaInicial;
import telas.TelaInventario;
import utilizavel.Inventario;
import utilizavel.Item;

public class Main {
    public static void main(String[] args) {
    //Definição de Telas
        //Tela Inicial:
            //Visibilidade: "100%" (não contando o limpa tela, veja EstilizacaoTela para mais contexto)
            //Funcionalidade: 62,5%
                //case 3 e 4 funcionando 100%
                //case 1 funciona 50% (te leva para a criação de personagem, não começa novo jogo)
                //case 2 0%
        //Tela Inventário:
            //Funcionalidade: ???%
            //Criação de personagem: 100% (falta polimento, mas por enquanto- como apenas está sendo usado para testes, está ok)
            //Exibição de Inventário: ???% (acho que está ok, mas não consegui testar ainda)
        //Tela Padrão: 0%
        //Tela Combate: 0%
    //TESTE TELA INICIAL:
//        TelaInicial.exibir();

    //Definição de Personagens
        //Personagem (classe mãe): 100%
            //Jogador: 100%
            //Inimigo: 100%
    //TESTE PERSONAGEM:
//        Item excalibur = new Item("Excalibur", 'w', true, 5, 3);
//        Jogador arthur = new Jogador("Arthur Boyle", 10, 20, 8);
//        arthur.getInventario().adicionarItem(excalibur);
//        arthur.getInventario().adicionarTesouro(1000);
//        arthur.exibirInformacoes();
//        System.out.println();

    //TESTE INIMIGO:
//        Item lanca = new Item("Lança", 'w', true, 1, 1);
//        Inimigo goblin = new Inimigo("Goblin", 10, 20, 6, 200, 1, lanca);
//        goblin.exibirInformacoes();

    //Definição de Utilizavel
        //Inventário: 100%
        //Item: 100%

    //Jogabilidade: (Testar Sorte, Salvar Jogo, Carregar Jogo, etc?)
    }
}
