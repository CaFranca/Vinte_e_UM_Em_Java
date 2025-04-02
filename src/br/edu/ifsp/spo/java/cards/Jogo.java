package br.edu.ifsp.spo.java.cards;

import java.text.MessageFormat;
import java.util.Scanner;

public class Jogo {
    private Jogador jogador1;
    private Jogador jogador2;
    private Baralho baralho;
    private int turno;
    private int jogo;
    private int valor1;
    private int valor2;
    private boolean jogador1Parou;
    private boolean jogador2Parou;

    private int PrimeirasCartas;

    public Jogo(String nomeJogador1, String nomeJogador2) {
        jogador1 = new Jogador(nomeJogador1);
        jogador2 = new Jogador(nomeJogador2);
        baralho = new Baralho();
        turno = 1;
        jogo = 0;
        valor1 = 0;
        valor2 = 0;
        jogador1Parou = false;
        jogador2Parou = false;
        PrimeirasCartas=0;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        while (jogo == 0) {
            if(PrimeirasCartas==0){
                for (int i = 0; i <2 ; i++) {
                    Carta carta = baralho.tirarCarta();
                    int numerocarta = obterValorCarta(carta.getValor());
                    valor1 += numerocarta;
                }
                for (int i = 0; i <2 ; i++) {
                    Carta carta = baralho.tirarCarta();
                    int numerocarta = obterValorCarta(carta.getValor());
                    valor2 += numerocarta;
                }
                PrimeirasCartas=1;
                }

            int resposta = 0;

            if (turno == 1 && !jogador1Parou) {
                System.out.println("\n=== Vez do " + jogador1.getnome() + " ===");
                System.out.println("Seu valor atual é: " + valor1);
                System.out.println("Deseja pegar mais uma carta? Digite 1 para sim, ou 0 para passar e 2 para parar:");
                resposta = scanner.nextInt();

                if (resposta == 1) {
                    Carta carta = baralho.tirarCarta();
                    int numerocarta = obterValorCarta(carta.getValor());
                    valor1 += numerocarta;
                    System.out.println("Você pegou a carta: " + carta);
                    System.out.println(MessageFormat.format("Cartas restantes no baralho: {0}", baralho.cartasRestantes()));

                    if (valor1 == 21) {
                        System.out.println("\n" + jogador1.getnome() + " venceu! Ele(a) alcançou 21.");
                        jogo = 1;
                    } else if (valor1 > 21) {
                        System.out.println("\n" + jogador2.getnome() + " venceu! Com " + valor2);
                        System.out.println("Seu valor estourou 21: " + valor1);
                        jogo = 1;
                    }

                } else if (resposta == 0) {
                    System.out.println(jogador1.getnome() + " passou a vez.");
                } else if (resposta == 2) {
                    jogador1Parou = true;
                    System.out.println(jogador1.getnome() + " decidiu parar.");
                }
            }

            if (turno == 2 && !jogador2Parou) {
                System.out.println("\n=== Vez do " + jogador2.getnome() + " ===");
                System.out.println("Seu valor atual é: " + valor2);
                System.out.println("Deseja pegar mais uma carta? Digite 1 para sim, ou 0 para passar e 2 para parar:");
                resposta = scanner.nextInt();

                if (resposta == 1) {
                    Carta carta = baralho.tirarCarta();
                    int numerocarta = obterValorCarta(carta.getValor());
                    valor2 += numerocarta;
                    System.out.println("Você pegou a carta: " + carta);
                    System.out.println(MessageFormat.format("Cartas restantes no baralho: {0}", baralho.cartasRestantes()));

                    if (valor2 == 21) {
                        System.out.println("\n" + jogador2.getnome() + " venceu! Ele(a) alcançou 21.");
                        jogo = 1;
                    } else if (valor2 > 21) {
                        System.out.println("\n" + jogador1.getnome() + " venceu!");
                        System.out.println("Seu valor estourou 21: " + valor2);
                        jogo = 1;
                    }

                } else if (resposta == 0) {
                    System.out.println(jogador2.getnome() + " passou a vez.");
                } else if (resposta == 2) {
                    jogador2Parou = true;
                    System.out.println(jogador2.getnome() + " decidiu parar.");
                }
            }

            if (jogador1Parou && jogador2Parou) {
                int aproximacao1 = 21 - valor1;
                int aproximacao2 = 21 - valor2;

                if (aproximacao1 < aproximacao2) {
                    System.out.println(jogador1.getnome() + " venceu com " + valor1);
                    jogo = 1;
                } else if (aproximacao2 < aproximacao1) {
                    System.out.println(jogador2.getnome() + " venceu com " + valor2);
                    jogo = 1;
                } else {
                    System.out.println("O jogo empatou.");
                    jogo = 1;
                }
            }

            turno = (turno == 1) ? 2 : 1;
        }

        scanner.close();
    }

    private int obterValorCarta(Valor valorCarta) {
        int valor = 0;
        switch (valorCarta) {
            case AS:
                valor = 1;
                break;
            case DOIS:
                valor = 2;
                break;
            case TRES:
                valor = 3;
                break;
            case QUATRO:
                valor = 4;
                break;
            case CINCO:
                valor = 5;
                break;
            case SEIS:
                valor = 6;
                break;
            case SETE:
                valor = 7;
                break;
            case OITO:
                valor = 8;
                break;
            case NOVE:
                valor = 9;
                break;
            case DEZ:
            case DAMA:
            case VALETE:
            case REI:
                valor = 10;
                break;
            default:
                System.out.println("Carta desconhecida: " + valorCarta);
                break;
        }
        return valor;
    }
}
