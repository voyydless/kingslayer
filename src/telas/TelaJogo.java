package telas;

import jogabilidade.Jogo;
import jogabilidade.LeitorDeArquivos;
import jogabilidade.ProcessarArquivos;
import personagens.Jogador;
import personagens.Inimigo;
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

            // Verifica se a cena atual é o fim do jogo
            if (cena.isFimDoJogo()) {
                EstilizacaoTela.centralizar("--- FIM ---", 70);
                EstilizacaoTela.centralizar("Obrigado por jogar! (˶˃ ᵕ ˂˶)", 70);
                break; // Encerra o loop principal do jogo
            }

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
                        "Você ganhou " + modificadorTesouro + " moedas de ouro." :
                        "Você perdeu " + modificadorTesouro + " moedas de ouro.");
            }

            //Provisões modificadas:
            int modificadorPovisoes = cena.getModificadorProvisoes();
            if (modificadorPovisoes > 0) {
                jogador.getInventario().adicionarProvisoes(modificadorPovisoes);
                System.out.println(modificadorPovisoes > 1 ?
                        "Você ganhou " + modificadorPovisoes + " provisões." :
                        "Você ganhou " + modificadorPovisoes + " provisão.");
            }

            //Perda de itens
            if (cena.isRemoverItens()) {
                jogador.perderTodosItens();
                System.out.println("Todos os seus itens foram perdidos!");
            }

            //Verificar se é uma cena de combate
            if (cena.isCombate()) {
                boolean vitoria = TelaCombate.iniciarCombate(jogador, cena.getInimigo());

                if (vitoria) {
                    cenaAtual = cena.getCenaVitoria();
                    Inimigo inimigo = cena.getInimigo();
                    //Recompensas:
                if (inimigo.getProvisoes() > 0) {
                    jogador.getInventario().adicionarProvisoes(inimigo.getProvisoes());
                    System.out.println("Você ganhou " + inimigo.getProvisoes() + " provisões!");
                }
                if (inimigo.getTesouro() > 0) {
                    jogador.getInventario().adicionarTesouro(inimigo.getTesouro());
                    System.out.println("Você ganhou " + inimigo.getTesouro() + " ouro!");
                }
                if (inimigo.getItem() != null) {
                    jogador.getInventario().adicionarItem(inimigo.getItem());
                    System.out.println("Item adquirido: " + inimigo.getItem());
                }
                    scanner.nextLine(); //Consumir o buffer
                    EstilizacaoTela.linhas();
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                } else {
                    cenaAtual = cena.getCenaDerrota();
                }
                continue;
            }

            // Exibe as opções para próxima cena
            System.out.println("\nEscolhas:");
            Map<Integer, Integer> mapaOpcoes = new LinkedHashMap<>();
            int i = 1;

            for (Map.Entry<Integer, String> opcaoDigitada : cena.getOpcoes().entrySet()) {
                mapaOpcoes.put(i, opcaoDigitada.getKey());
                System.out.println(i + " - " + opcaoDigitada.getValue());
                i++;
            }

            // Mostrar opção de teste de sorte se disponível
            if (cena.isTesteDeSorte()) {
                System.out.println("d - Testar sua sorte (Sorte atual: " + jogador.getSorte() + ") | Abaixo disso, será sucesso. Acima, será fracasso.");
                System.out.println("   Sucesso: " + cena.getDescricaoSucessoSorte());
                System.out.println("   Fracasso: " + cena.getDescricaoFracassoSorte());
            }

            //Opção especial: digitar "i" para ver inventário
            System.out.println("\nDigite o número da cena desejada ou 'i' para abrir o inventário:");
            String entrada = scanner.next(); // Usa apenas next() para evitar problemas com o buffer

            // Processar a entrada do jogador
            if (entrada.equalsIgnoreCase("i")) {
                // Abrir inventário
                TelaInventario.mostrarInventario(jogador);
                scanner.nextLine(); // Limpa o buffer
                continue;
            } else if (entrada.equalsIgnoreCase("d") && cena.isTesteDeSorte()) {
                // Testar sorte
                System.out.println("Rolando um d12...");
                boolean sucesso = jogador.testarSorte();

                if (sucesso) {
                    System.out.println("\nSucesso!");
                    cenaAtual = cena.getCenaSucessoSorte();
                } else {
                    System.out.println("\nFracasso...");
                    cenaAtual = cena.getCenaFracassoSorte();
                }
                System.out.println("Pressione ENTER para continuar");
                scanner.nextLine(); // Limpa o buffer
                scanner.nextLine(); // Espera o ENTER

                Jogo.setCenaAtual(cenaAtual);
                continue;

            } else {
                // Processar escolha numérica
                try {
                    int escolhaVisivel = Integer.parseInt(entrada);
                    if (mapaOpcoes.containsKey(escolhaVisivel)) {
                        int cenaDestino = mapaOpcoes.get(escolhaVisivel); // pega o número real da cena

                        // Verificar requisito de item aqui
                        if (cena.temRequisito(cenaDestino)) {
                            String itemNecessario = cena.getRequisitoItem(cenaDestino);
                            if (!jogador.getInventario().temItemComNome(itemNecessario)) {
                                System.out.println("\nVocê precisa do item '" + itemNecessario + "' para seguir por este caminho!");
                                System.out.println("Pressione ENTER para continuar.");
                                scanner.nextLine();
                                scanner.nextLine();
                                continue; // volta para a seleção de opções
                            }
                        }

                        cenaAtual = cenaDestino;
                        Jogo.setCenaAtual(cenaAtual);
                    } else {
                        System.out.println("Opção inválida. Pressione ENTER para continuar");
                        scanner.nextLine(); // Limpa o buffer
                        scanner.nextLine(); // Espera o ENTER
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Pressione ENTER para continuar");
                    scanner.nextLine(); // Limpa o buffer
                    scanner.nextLine(); // Espera o ENTER
                }
            }

        }
    }
}