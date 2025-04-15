import jogabilidade.Jogo;
import telas.TelaInicial;
import telas.TelaInventario;

public class Main {
    public static void main(String[] args) {
    //1. Definição de Telas
        //Tela Inicial:
            //Visibilidade: "100%" (não contando o limpa tela, veja EstilizacaoTela para mais contexto)
            //Funcionalidade: 62,5%
                //case 3 e 4 funcionando 100%
                //case 1 funciona 50% (te leva para a criação de personagem, não começa novo jogo)
                //case 2 0%
        //Tela Inventário:
            //Funcionalidade: 50%
            //Criação de personagem: 100% (falta polimento, mas por enquanto- como apenas está sendo usado para testes, está ok)
            //Exibição de Inventário: ???% (acho que está ok, mas não consegui testar ainda)
        //Tela Padrão: 0%
        //Tela Combate: 0%
        TelaInicial.exibir();

    //Definição de Personagens
    //Personagem (Jogador, Inimigo)
    //Util (Inventario, Item)
    //Funcionalidade (Testar Sorte, Salvar Jogo, Carregar Jogo, etc?)
    }
}
