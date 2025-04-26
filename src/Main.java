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
            //Visibilidade: 100%
            //Funcionalidade: 87,5%
            //case 1 100%
            //case 2 50% (carrega o jogo salvo, mas não começa de novo)
            //case 3 e 4 100%
        //Tela Inventário:
            //Criação de personagem: 100%
            //Exibição de Inventário: 75% (Apenas falta opção de consumir provisão)
        //Tela Padrão: 75% (Falta adicionar combate, por enquanto apenas é uma vitória simulada)
        //Tela Combate: 0%
        //TESTE TELA INICIAL:
        TelaInicial.exibir();

        //Definição de Personagens
        //Personagem (classe mãe): 100%
        //Jogador: 100%
        //Inimigo: 100%
        //TESTE PERSONAGEM:
        Item excalibur = new Item("Excalibur", 'w', true, 5, 3);
        Jogador arthur = new Jogador("Arthur Boyle", 10, 20, 8);
        arthur.getInventario().adicionarItem(excalibur);
        arthur.getInventario().adicionarTesouro(100);
        arthur.exibirInformacoes();
        System.out.println();

        //TESTE INIMIGO:
        Item lanca = new Item("Lança", 'w', true, 1, 1);
        Inimigo goblin = new Inimigo("Goblin", 10, 20, 6, 200, 1, lanca);
        goblin.exibirInformacoes();

        //Definição de Utilizavel
        //Inventário: 100%
        //Item: 100%

        //Jogabilidade:
            //Ler arquivos: 100%
            //Salvar arquivos: 100%
            //Carregar arquivos: 50% (carrega, mas não continua o jogo)
            //Testar sorte: 0%

        //História: 50% (Já tá tudo escrito, só preciso adicionar o resto e trduzir para português)
    }
}