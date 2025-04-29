package jogabilidade;

import personagens.Inimigo;
import utilizavel.Item;

import java.io.*;

public class LeitorDeArquivos {

    public static ProcessarArquivos carregar(int numeroCena) {
        //Criação de um novo objeto Arquivos, que será preenchido com os dados da cena;
        //Logo em seguida, criação de um StringBuilder (essencialmente, um acumulador de texto) para guardar a narrativa dessa cena.
        ProcessarArquivos cena = new ProcessarArquivos();
        cena.setNumeroCena(numeroCena);
        StringBuilder texto = new StringBuilder();

        //Definição do caminho do arquivo cena.
        String caminho = "cenas/" + numeroCena + ".txt";

        //Abre o aqruivo da cena para leitura, usando BufferedReader para ler linha por linha;
        //linha = br.readLine() chama o método readLine() no objeto br, atribuindo o conteúdo lido à variável linha.
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha = br.readLine();

            //É combate?
            //Se sim, identificado pelo arquivo começando em "m",
            //faz a leitura da cena normalmente até a listagem de atibutos do inimigo.
            if (linha != null && linha.trim().equalsIgnoreCase("m")) {
                cena.setCombate(true);

                while ((linha = br.readLine()) != null && !linha.startsWith("N:")) {
                    texto.append(linha).append("\n");
                }
                cena.setTexto(texto.toString().trim());

                //Leitura dos atributos, cortando os primeiros dois carácteres "(letra):".
                //Transforma o valor em int, e adiciona ele aos respectivos atributos.
                String nome = linha.substring(2).trim();

                linha = br.readLine();
                int habilidade = Integer.parseInt(linha.substring(2).trim());

                linha = br.readLine();
                int sorte = Integer.parseInt(linha.substring(2).trim());

                linha = br.readLine();
                int energia = Integer.parseInt(linha.substring(2).trim());

                linha = br.readLine();
                int tesouro = Integer.parseInt(linha.substring(2).trim());

                linha = br.readLine();
                int provisoes = Integer.parseInt(linha.substring(2).trim());

                //O inimigo tem item?
                //Se sim, separa o string por ;, cada atributo separado por ele sendo enumerado de 0-4;
                //nome já é String, então não há necessidade de converter, tipo é char, combate é true se for 1, fa e dano são integers;
                //Por último, cria o item e associa ele ao inimigo.
                linha = br.readLine();
                Item item = null;
                if (linha.startsWith("I:")) {
                    String[] partes = linha.substring(2).trim().split(";");
                    String nomeItem = partes[0].trim();
                    char tipo = partes[1].trim().charAt(0);
                    boolean combate = partes[2].trim().equals("1");
                    int fa = Integer.parseInt(partes[3].trim());
                    int dano = Integer.parseInt(partes[4].trim());

                    item = new Item(nomeItem, tipo, combate, fa, dano);

                    linha = br.readLine();
                }
                //Criação do inimigo em si com base nos dados que acabamos de extrair.
                Inimigo inimigo = new Inimigo(nome, habilidade, energia, sorte, tesouro, provisoes, item);
                cena.setInimigo(inimigo);

                String[] destinos = linha.split(";");
                cena.setCenaVitoria(Integer.parseInt(destinos[0].trim()));
                cena.setCenaDerrota(Integer.parseInt(destinos[1].trim()));


            } else {
                boolean encontrouSorte = false;

                while ((linha = br.readLine()) != null) {
                    //Tem item?
                    //Se sim, mesmo que o processo de leitura de item com o inimigo.
                    //Mas agora, cria o item e associa ele à cena atual.
                    if (linha.startsWith("I:")) {
                        String[] partes = linha.substring(2).split(";");
                        String nome = partes[0].trim();
                        char tipo = partes[1].trim().charAt(0);
                        boolean combate = partes[2].trim().equals("1");
                        int fa = Integer.parseInt(partes[3].trim());
                        int dano = Integer.parseInt(partes[4].trim());

                        Item item = new Item(nome, tipo, combate, fa, dano);
                        cena.setItemRecebido(item);

                        //Tem tesouro?
                        //Se sim, converte o valor descrito no arquivo para integer--
                        //e registra na cena que o tesouro do jogador deve ser modificado.
                    } else if (linha.startsWith("T:")) {
                        int valor = Integer.parseInt(linha.substring(2).trim());
                        cena.setModificadorTesouro(valor);

                        //Tem provisão?
                        //Se sim, converte o valor descrito no arquivo para integer--
                        //e registra na cena que as provisões do jogador devem ser modificadas.
                    } else if (linha.startsWith("P:")) {
                        int quantia = Integer.parseInt(linha.substring(2).trim());
                        cena.setModificadorProvisoes(quantia);

                        // Verificação de teste de sorte
                    } else if (linha.startsWith("SORTE")) {
                        cena.setTesteDeSorte(true);
                        encontrouSorte = true;

                    }else if (linha.equalsIgnoreCase("FIM")) {
                            cena.setFimDoJogo(true);

                        //É uma opção?
                    } else if (linha.startsWith("#")) {
                            String[] partes = linha.substring(1).split(":");
                            int destino = Integer.parseInt(partes[0].trim());
                            String descricao = partes[1].trim();

                        // Verifica se há requisito entre colchetes no final
                        if (descricao.contains("[") && descricao.contains("]")) {
                            int inicio = descricao.indexOf('[');
                            int fim = descricao.indexOf(']');
                            String requisito = descricao.substring(inicio + 1, fim).trim();
                            descricao = descricao.substring(0, inicio).trim(); // remove [requisito] do texto
                            cena.adicionarRequisito(destino, requisito);
                        }
                            if (encontrouSorte) {
                                // Se já encontramos SORTE e ainda não temos destino de sucesso
                                if (cena.getCenaSucessoSorte() == 0) {
                                    cena.setCenaSucessoSorte(destino);
                                    cena.setDescricaoSucessoSorte(descricao);
                                }
                                // Se já temos destino de sucesso mas não de fracasso
                                else if (cena.getCenaFracassoSorte() == 0) {
                                    cena.setCenaFracassoSorte(destino);
                                    cena.setDescricaoFracassoSorte(descricao);
                                }
                            } else {
                                // Se não encontramos SORTE ainda, é uma opção normal
                                cena.adicionarOpcao(destino, descricao);
                            }
                        }
                        //Não tem nada disso? Então ainda é a história
                        else {
                            texto.append(linha).append("\n");
                        }
                    }
                    cena.setTexto(texto.toString().trim());
                }
                return cena;
            } catch(IOException e) {
                System.out.println("Erro ao ler arquivo: " + e.getMessage());
                return null;
            }
        }
    }