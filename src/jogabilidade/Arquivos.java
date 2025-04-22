package jogabilidade;

import personagens.Jogador;
import utilizavel.Item;

import java.io.*;
import java.util.*;

public class Arquivos {
    private static final String SAVE_PATH = "saves/save.txt";

// Abaixo esta sendo feita a leitura dos codigs e escrevendo dentro de um txt
    public static void salvarJogo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_PATH))) {
            Jogador jogador = Jogo.getJogador();

            writer.write("CenaAtual:" + Jogo.getCenaAtual());
            writer.newLine();
            writer.write("CenasVistas:" + Jogo.getCenasVistas().toString().replaceAll("[\\[\\] ]", ""));
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
                writer.write("Item:" + item.getNome() + ";" + item.getTipo() + ";" + item.isCombate() + ";" + item.getFa() + ";" + item.getDano());
                writer.newLine();
            }

            System.out.println("Jogo salvo automaticamente.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo: " + e.getMessage());
        }
    }
// aqui ele le o txt e carrega o jogo *mas n ta funcionando ainda pq precisa ajustar*
    public static void carregarJogo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_PATH))) {
            String linha;
            Map<String, String> dados = new HashMap<>();
            List<Item> itens = new ArrayList<>();

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Item:")) {
                    String[] partes = linha.substring(5).split(";");
                    Item item = new Item(
                            partes[0],
                            partes[1].charAt(0),
                            Boolean.parseBoolean(partes[2]),
                            Integer.parseInt(partes[3]),
                            Integer.parseInt(partes[4])
                    );
                    itens.add(item);
                } else {
                    String[] partes = linha.split(":");
                    dados.put(partes[0], partes[1]);
                }
            }

            Jogador jogador = new Jogador(
                    dados.get("Nome"),
                    Integer.parseInt(dados.get("Habilidade")),
                    Integer.parseInt(dados.get("Energia")),
                    Integer.parseInt(dados.get("Sorte"))
            );

            jogador.getInventario().adicionarTesouro(Integer.parseInt(dados.get("Tesouro")));
            jogador.getInventario().adicionarProvisoes(Integer.parseInt(dados.get("Provisoes")));
            for (Item item : itens) {
                jogador.getInventario().adicionarItem(item);
            }

            Jogo.setJogador(jogador);

            Jogo.setCenaAtual(Integer.parseInt(dados.get("CenaAtual")));

            Set<Integer> cenasVistas = new HashSet<>();
            if (dados.containsKey("CenasVistas")) {
                String[] cenas = dados.get("CenasVistas").split(",");
                for (String c : cenas) {
                    if (!c.isEmpty()) {
                        cenasVistas.add(Integer.parseInt(c));
                    }
                }
            }
            Jogo.setCenasVistas(cenasVistas);

            System.out.println("Jogo carregado com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao carregar o jogo: " + e.getMessage());
        }
    }
}