package br.edu.ifsp.spo.java.cards;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nome1, nome2;

        System.out.println("Digite o nome do Jogador 1:");
        nome1 = scanner.nextLine();

        System.out.println("Digite o nome do Jogador 2:");
        nome2 = scanner.nextLine();

        System.out.println("Escolha o modo de pontuação:");
        System.out.println("[1] Clássico - Figuras valem 10");
        System.out.println("[2] Alternativo - Figuras valem 1");
        int modo = scanner.nextInt();

        Jogo jogo = new Jogo(nome1, nome2, modo);
        jogo.iniciar();

        scanner.close();
    }
}
