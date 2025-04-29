package telas;

import personagens.Inimigo;
import personagens.Jogador;

import java.util.Random;
import java.util.Scanner;

public class TelaCombate {

    public static boolean iniciarCombate(Jogador jogador, Inimigo inimigo) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        EstilizacaoTela.centralizar("--- COMBATE INICIADO ---", 70);
        EstilizacaoTela.centralizar("Oponente:", 70);
        inimigo.exibirInformacoes();
        EstilizacaoTela.centralizar("Você:",70);
        jogador.exibirInformacoes();

        while (jogador.getEnergia() > 0 && inimigo.getEnergia() > 0) {
            EstilizacaoTela.linhas();
            EstilizacaoTela.centralizar("\nPressione ENTER para rolar os dados", 70);
            System.out.println("\nSua energia: " + jogador.getEnergia() + "\nEnergia do oponente: " + inimigo.getEnergia());
            scanner.nextLine();

            //Rolagens de dado
            int dadoJogador = random.nextInt(10) + 1;
            int dadoInimigo = random.nextInt(10) + 1;
            int faJogador = dadoJogador + jogador.getHabilidade();
            int faInimigo = dadoInimigo + inimigo.getEnergia();

            System.out.println("Você rolou: " + dadoJogador + " | FA total: " + faJogador);
            System.out.println("Oponente rolou: " + dadoInimigo + " | FA total: " + faInimigo);

            if (faJogador > faInimigo) {
                int dano = 2 + (jogador.getArmaEquipada() != null ? jogador.getArmaEquipada().getDano() : 0);
                inimigo.setEnergia(inimigo.getEnergia() - dano);
                System.out.println("\nVocê causou " + dano + " de dano em " + inimigo.getNome());
            } else if (faInimigo > faJogador) {
                int dano = 2 + (inimigo.getItem() != null ? inimigo.getItem().getDano() : 0);
                jogador.setEnergia(jogador.getEnergia() - dano);
                System.out.println("\n" + inimigo.getNome() + " te acertou e causou " + dano + " de dano");
            } else {
                System.out.println("\nEmpate!");
            }
        }
        if (jogador.getEnergia() > 0) {
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
