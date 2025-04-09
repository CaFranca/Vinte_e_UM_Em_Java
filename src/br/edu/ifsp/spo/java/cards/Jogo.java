package br.edu.ifsp.spo.java.cards;

import java.text.MessageFormat;
import java.util.Scanner;

public class Jogo {
    private final Jogador jogador1;
    private final Jogador jogador2;
    private final Baralho baralho;
    private final Pontuador pontuador;
    private int turno;
    private boolean jogador1Parou;
    private boolean jogador2Parou;
    private boolean jogoFinalizado;

    public Jogo(String nomeJogador1, String nomeJogador2) {
        this.jogador1 = new Jogador(nomeJogador1);
        this.jogador2 = new Jogador(nomeJogador2);
        this.baralho = new Baralho();
        this.pontuador = new Pontuador();
        this.turno = 1;
        this.jogador1Parou = false;
        this.jogador2Parou = false;
        this.jogoFinalizado = false;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        // Distribuir 2 cartas iniciais para cada jogador
        for (int i = 0; i < 2; i++) {
            jogador1.adicionarCarta(baralho.tirarCarta());
            jogador2.adicionarCarta(baralho.tirarCarta());
        }

        while (!jogoFinalizado) {
            Jogador jogadorAtual = (turno == 1) ? jogador1 : jogador2;
            boolean jogadorParou = (turno == 1) ? jogador1Parou : jogador2Parou;

            if (!jogadorParou) {
                System.out.println("\n=== Vez de " + jogadorAtual.getNome() + " ===");
                int pontuacao = pontuador.verificarPontuacao(jogadorAtual.getCartas());
                System.out.println("Pontuação atual: " + pontuacao);
                System.out.println("Deseja pegar carta? [1] Sim | [0] Passar | [2] Parar:");
                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1 -> {
                        Carta novaCarta = baralho.tirarCarta();
                        jogadorAtual.adicionarCarta(novaCarta);
                        System.out.println("Você pegou a carta: " + novaCarta);
                        System.out.println(MessageFormat.format("Cartas restantes no baralho: {0}", baralho.cartasRestantes()));
                        int novaPontuacao = pontuador.verificarPontuacao(jogadorAtual.getCartas());
                        if (novaPontuacao == 21) {
                            System.out.println("Parabéns, você fez 21!");
                            encerrarJogo(jogadorAtual);
                            return;
                        } else if (novaPontuacao > 21) {
                            System.out.println("Estourou! Você fez " + novaPontuacao);
                            Jogador outro = (jogadorAtual == jogador1) ? jogador2 : jogador1;
                            encerrarJogo(outro);
                            return;
                        }
                    }
                    case 0 -> System.out.println(jogadorAtual.getNome() + " passou a vez.");
                    case 2 -> {
                        System.out.println(jogadorAtual.getNome() + " decidiu parar.");
                        if (turno == 1) jogador1Parou = true;
                        else jogador2Parou = true;
                    }
                }
            }

            // Verifica se ambos pararam
            if (jogador1Parou && jogador2Parou) {
                int pontos1 = pontuador.verificarPontuacao(jogador1.getCartas());
                int pontos2 = pontuador.verificarPontuacao(jogador2.getCartas());

                if (pontos1 > pontos2) encerrarJogo(jogador1);
                else if (pontos2 > pontos1) encerrarJogo(jogador2);
                else {
                    System.out.println("Empate! Ambos fizeram " + pontos1);
                    jogoFinalizado = true;
                }
                return;
            }

            // Alterna turno
            turno = (turno == 1) ? 2 : 1;
        }

        scanner.close();
    }

    private void encerrarJogo(Jogador vencedor) {
        int pontuacao = pontuador.verificarPontuacao(vencedor.getCartas());
        System.out.println("\n🏆 " + vencedor.getNome() + " venceu com " + pontuacao + " pontos!");
        jogoFinalizado = true;
    }
}
