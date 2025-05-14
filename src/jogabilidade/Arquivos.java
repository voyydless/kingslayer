package jogabilidade;

import personagens.Jogador;
import utilizavel.Item;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Arquivos {
    private static final String SAVE_PATH = "saves" + File.separator + "save.txt";

    public static void salvarJogo() {
        File dir = new File("saves");
        if (!dir.exists()) dir.mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_PATH))) {
            Jogador jogador = Jogo.getJogador();

            writer.write("CenaAtual:" + Jogo.getCenaAtual());
            writer.newLine();
            writer.write("CenasVistas:" +
                    Jogo.getCenasVistas().toString().replaceAll("[\\[\\] ]", ""));
            writer.newLine();

            writer.write("Nome:" + jogador.getNome());
            writer.newLine();
            writer.write("Habilidade:" + jogador.getHabilidade());
            writer.newLine();
            writer.write("Energia:" + jogador.getEnergia());
            writer.newLine();
            writer.write("Sorte:" + jogador.getSorte());
            writer.newLine();

            writer.write("Tesouro:" + jogador.getInventario().getTesouro());
            writer.newLine();
            writer.write("Provisoes:" + jogador.getInventario().getProvisoes());
            writer.newLine();

            for (Item item : jogador.getInventario().getItens()) {
                writer.write("Item:" +
                        item.getNome() + ";" +
                        item.getTipo() + ";" +
                        item.isCombate() + ";" +
                        item.getFa() + ";" +
                        item.getDano());
                writer.newLine();
            }

            System.out.println("Jogo salvo automaticamente.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo: " + e.getMessage());
        }
    }

    public static void carregarJogo() {
        File saveFile = new File(SAVE_PATH);
        if (!saveFile.exists()) {
            System.out.println("Nenhum jogo salvo encontrado em " + SAVE_PATH);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
            String linha;
            Map<String, String> dados = new HashMap<>();
            List<Item> itens = new ArrayList<>();

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Item:")) {
                    String[] partes = linha.substring(5).split(";", 5);
                    Item item = new Item(
                            partes[0],
                            partes[1].charAt(0),
                            Boolean.parseBoolean(partes[2]),
                            Integer.parseInt(partes[3]),
                            Integer.parseInt(partes[4])
                    );
                    itens.add(item);
                } else {
                    String[] partes = linha.split(":", 2);
                    if (partes.length == 2) {
                        dados.put(partes[0], partes[1]);
                    }
                }
            }

            // Reconstrói jogador e inventário
            Jogador jogador = new Jogador(
                    dados.getOrDefault("Nome", "Jogador"),
                    Integer.parseInt(dados.getOrDefault("Habilidade", "0")),
                    Integer.parseInt(dados.getOrDefault("Energia", "0")),
                    Integer.parseInt(dados.getOrDefault("Sorte", "0"))
            );
            jogador.getInventario()
                    .adicionarTesouro(Integer.parseInt(dados.getOrDefault("Tesouro", "0")));
            jogador.getInventario()
                    .adicionarProvisoes(Integer.parseInt(dados.getOrDefault("Provisoes", "0")));
            itens.forEach(jogador.getInventario()::adicionarItem);

            // Cena salva
            int cenaSalva = Integer.parseInt(dados.getOrDefault("CenaAtual", "0"));

            // Cenas vistas
            Set<Integer> cenasVistas = new HashSet<>();
            String vistas = dados.get("CenasVistas");
            if (vistas != null && !vistas.isEmpty()) {
                for (String c : vistas.split(",")) {
                    if (!c.trim().isEmpty()) {
                        cenasVistas.add(Integer.parseInt(c.trim()));
                    }
                }
            }

            // Atualiza no jogo
            Jogo.setJogador(jogador);
            Jogo.setCenasVistas(cenasVistas);

            // Agora perguntamos ao jogador qual cena quer iniciar:
            int cenaEscolhida = escolherCena(cenaSalva, cenasVistas);
            Jogo.setCenaAtual(cenaEscolhida);

            // Exibe no console todos os dados carregados
            System.out.println("\n=== Dados do Jogador ===");
            System.out.println("Nome:       " + jogador.getNome());
            System.out.println("Habilidade: " + jogador.getHabilidade());
            System.out.println("Energia:    " + jogador.getEnergia());
            System.out.println("Sorte:      " + jogador.getSorte());
            System.out.println("Tesouro:    " + jogador.getInventario().getTesouro());
            System.out.println("Provisões:  " + jogador.getInventario().getProvisoes());

            System.out.println("\n=== Itens no Inventário ===");
            if (jogador.getInventario().getItens().isEmpty()) {
                System.out.println("Nenhum item.");
            } else {
                for (Item it : jogador.getInventario().getItens()) {
                    System.out.printf("- %s (tipo: %c, combate: %b, FA: %d, dano: %d)%n",
                            it.getNome(),
                            it.getTipo(),
                            it.isCombate(),
                            it.getFa(),
                            it.getDano());
                }
            }

            System.out.println("\n=== Estado do Jogo ===");
            System.out.println("Cena Inicial Escolhida: " + cenaEscolhida);
            System.out.println("Cenas Vistas:           " + cenasVistas);

        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar o jogo: " + e.getMessage());
        }
    }

    //Exibe o jogador as opções de cena e retorna a cena selecionada;
    //cenaSalva - A cena em que o jogo foi salvo;
    //cenasVistas - Conjunto de cenas já vistas (para limitar opções);
    //return número da cena escolhida
    private static int escolherCena(int cenaSalva, Set<Integer> cenasVistas) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nVocê salvou na cena " + cenaSalva + ".");
        System.out.println("Se desejar, escolha outra cena para começar.");
        System.out.println("Cenas já vistas: " +
                cenasVistas.stream()
                        .sorted()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")));
        System.out.print("Número da cena (ou Enter para manter " + cenaSalva + "): ");

        String entrada = sc.nextLine().trim();
        if (entrada.isEmpty()) {
            return cenaSalva;
        }
        try {
            int escolhida = Integer.parseInt(entrada);
            // opcional: validar que a cena está em cenasVistas
            if (!cenasVistas.contains(escolhida)) {
                System.out.println("Cena não estava nas vistas; mantendo cena " + cenaSalva);
            }
            return cenaSalva;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida; mantendo cena " + cenaSalva);
            return cenaSalva;
        }
    }
}
