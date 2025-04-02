package br.edu.ifsp.spo.java.cards;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nome1;
        String nome2;

        System.out.println("Digite o nome do Jogador 1:");
        nome1 = scanner.nextLine();

        System.out.println("Digite o nome do Jogador 2:");
        nome2 = scanner.nextLine();

        Jogo jogo = new Jogo(nome1, nome2);

        jogo.iniciar();

        scanner.close();
    }
}
