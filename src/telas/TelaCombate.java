package telas;

import personagens.Inimigo;
import personagens.Jogador;

import java.util.Random;
import java.util.Scanner;

public class TelaCombate {

    public static boolean iniciarCombate(Jogador jogador, Inimigo inimigo) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Contadores de acertos
        int acertosJogador = 0;
        int acertosInimigo = 0;

        EstilizacaoTela.centralizar("Pressione ENTER para iniciar o combate", 70);
        scanner.nextLine();
        EstilizacaoTela.centralizar("--- COMBATE INICIADO ---", 70);
        EstilizacaoTela.centralizar("Oponente:", 70);
        inimigo.exibirInformacoes();
        EstilizacaoTela.centralizar("Você:",70);
        jogador.exibirInformacoes();

        while (acertosJogador < 3 && acertosInimigo < 3) {
//            EstilizacaoTela.linhas();
            EstilizacaoTela.centralizar("Pressione ENTER para rolar os dados", 70);
            System.out.println("\nSua energia: " + jogador.getEnergia() + "\nEnergia do oponente: " + inimigo.getEnergia() +
                                "\nSeus acertos: " + acertosJogador + "\nAcertos do oponente: " + acertosInimigo);
            scanner.nextLine();

            //Rolagens de dado
            int dadoJogador = random.nextInt(10) + 1;
            int dadoInimigo = random.nextInt(10) + 1;
            int faJogador = dadoJogador + jogador.getHabilidade();
            int faInimigo = dadoInimigo + inimigo.getHabilidade();

            System.out.println("Você rolou: " + dadoJogador + " | FA total: " + faJogador);
            System.out.println("Oponente rolou: " + dadoInimigo + " | FA total: " + faInimigo);

            if (faJogador > faInimigo) {
                int dano = 2 + (jogador.getArmaEquipada() != null ? jogador.getArmaEquipada().getDano() : 0);

                //Redução da armadura do inimigo
                if (inimigo.getItem() != null && inimigo.getItem().getTipo() == 'r') {
                    dano -= inimigo.getItem().getDano();
                }

                dano = Math.max(dano, 0); //Não pode causar dano negativo
                inimigo.setEnergia(inimigo.getEnergia() - dano);
                System.out.println("\n" + inimigo.getNome() + " recebeu " + dano + " de dano!");
                EstilizacaoTela.linhas();
                acertosJogador++;

            } else if (faInimigo > faJogador) {
                int dano = 2 + (inimigo.getItem() != null ? inimigo.getItem().getDano() : 0);

                //Redução da armadura do jogador
                if (jogador.getArmaduraEquipada() != null) {
                    dano -= jogador.getArmaduraEquipada().getDano();
                }

                dano = Math.max(dano, 0);
                jogador.setEnergia(jogador.getEnergia() - dano);
                System.out.println("\n" + inimigo.getNome() + " te acertou e causou " + dano + " de dano!");
                EstilizacaoTela.linhas();
                acertosInimigo++;

                if (jogador.getEnergia() <= 0) {
                    System.out.println("\nVocê caiu em batalha...");
                    return false; // derrota
                }

            } else {
                System.out.println("\nEmpate!");
                EstilizacaoTela.linhas();
            }
        }
        // Verifica quem venceu com base nos acertos
        if (acertosJogador >= 2) {
            EstilizacaoTela.linhas();
            EstilizacaoTela.centralizar("\nVocê venceu!\n", 70);
            EstilizacaoTela.linhas();
            return true;
        } else {
            EstilizacaoTela.linhas();
            EstilizacaoTela.centralizar("\nVocê foi derrotado...\n", 70);
            EstilizacaoTela.linhas();
            return false;
        }
    }
}
