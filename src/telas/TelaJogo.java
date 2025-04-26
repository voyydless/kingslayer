package telas;

import jogabilidade.Jogo;
import jogabilidade.LeitorDeArquivos;
import jogabilidade.ProcessarArquivos;
import personagens.Jogador;
import utilizavel.Item;

import java.util.*;

//Gerencia o fluxo do jogo.
//Responsável por carregar e exibir as cenas, processar itens recebidos,
//aplicar modificadores, exibir opções de escolhas e inicializar o combate.
public class TelaJogo {
    //Inicia o loop principal do jogo, exibindo as cenas, processando ações e recebendo entradas do jogador.
    public static void iniciar() {
        Scanner scanner = new Scanner(System.in);
        Jogador jogador = Jogo.getJogador(); //Recupera o jogador atual
        int cenaAtual = Jogo.getCenaAtual(); //Começa na cena atual salva

        //Loop principal, cada iteração é uma cena nova
        while (true) {
            ProcessarArquivos cena = LeitorDeArquivos.carregar(cenaAtual);
            if (cena == null) {
                System.out.println("Erro ao carregar a cena " + cenaAtual);
                return;
            }

            EstilizacaoTela.limparTela();
            System.out.println("\n" + cena.getTexto());

            //Item encontrado:
            Item item = cena.getItemRecebido();
            if (item != null) {
                System.out.println("\nItem adquirido: " + item);
                jogador.getInventario().adicionarItem(item);
            }

            //Tesouro modificado (tanto ganho quanto perda):
            int modificadorTesouro = cena.getModificadorTesouro();
            if (modificadorTesouro != 0) {
                jogador.getInventario().adicionarTesouro(modificadorTesouro);
                System.out.println(modificadorTesouro > 0 ?
                        "\nVocê ganhou " + modificadorTesouro + " moedas de ouro." :
                        "\nVocê perdeu " + modificadorTesouro + " moedas de ouro.");
            }

            //Provisões modificadas:
            int modificadorPovisoes = cena.getModificadorProvisoes();
            if (modificadorPovisoes > 0) {
                jogador.getInventario().adicionarProvisoes(modificadorPovisoes);
                System.out.println(modificadorPovisoes > 1 ?
                        "\nVocê ganhou " + modificadorPovisoes + " provisões." :
                        "\nVocê ganhou " + modificadorPovisoes + " provisão.");
            }

            //Incompleto!! Por enquanto sempre vitória até implementar combate
            //Verificar se é uma cena de combate
            if (cena.isCombate()) {
                System.out.println();
                EstilizacaoTela.centralizar("--- COMBATE INICIADO ---", 70);
                cena.getInimigo().exibirInformacoes();

                System.out.println("\nCombate ainda não implementado - vitória simulada");
                System.out.println("Pressione ENTER para continuar");
                scanner.nextLine();

                //Por enquanto sempre vitória
                cenaAtual = cena.getCenaVitoria();
                continue;
            }

            //Exibe as opções para próxima cena, mudando o número das opções para ser sempre 1,2,...etc, para melhorar visibilidade
            System.out.println("\nEscolhas:");
            Map<Integer, Integer> mapaOpcoes = new LinkedHashMap<>();
            int i = 1;

            for (Map.Entry<Integer, String> opcaoDigitada : cena.getOpcoes().entrySet()) {
                mapaOpcoes.put(i, opcaoDigitada.getKey()); // i = opção visível, getKey = número real da cena
                System.out.println(i + " - " + opcaoDigitada.getValue());
                i++;
            }

            //Opção especial: digitar "i" para ver inventário
            System.out.println("\nDigite o número da cena desejada ou 'i' para abrir o inventário:");
            String entrada = scanner.next();

            if (entrada.equalsIgnoreCase("i")) {
                TelaInventario.mostrarInventario(jogador);
                scanner.nextLine();
                scanner.nextLine();
                continue;
            }

            //Processa a escolha do jogador
            try {
                int escolhaVisivel = Integer.parseInt(entrada);
                if (mapaOpcoes.containsKey(escolhaVisivel)) {
                    int cenaDestino = mapaOpcoes.get(escolhaVisivel); // pega o número real da cena
                    Jogo.setCenaAtual(cenaDestino);
                    cenaAtual = cenaDestino;
                } else {
                    System.out.println("Opção inválida. Pressione ENTER para tentar novamente.");
                    scanner.nextLine();
                    scanner.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Pressione ENTER para tentar novamente.");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
    }
}
