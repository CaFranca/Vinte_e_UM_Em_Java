package br.edu.ifsp.spo.java.cards;

import java.text.MessageFormat;

public class Jogo {
    private final Jogador jogador1;
    private final Jogador jogador2;
    private final Baralho baralho;
    private final Pontuador pontuador;
    private final int modo;
    private final JogoUI ui; // Usando JogoUI

    private int turno;
    private boolean jogador1Parou;
    private boolean jogador2Parou;
    private boolean jogoFinalizado;

    public Jogo(String nomeJogador1, String nomeJogador2, int modo, JogoUI ui) {
        this.jogador1 = new Jogador(nomeJogador1);
        this.jogador2 = new Jogador(nomeJogador2);
        this.baralho = new Baralho();
        this.pontuador = new Pontuador();
        this.modo = modo;
        this.ui = ui; // Inicializando JogoUI
        this.turno = 1;
        this.jogador1Parou = false;
        this.jogador2Parou = false;
        this.jogoFinalizado = false;
    }

    public void iniciar() {
        for (int i = 0; i < 2; i++) {
            jogador1.adicionarCarta(baralho.tirarCarta());
            jogador2.adicionarCarta(baralho.tirarCarta());
        }

        while (!jogoFinalizado) {
            Jogador jogadorAtual = (turno == 1) ? jogador1 : jogador2;
            boolean jogadorParou = (turno == 1) ? jogador1Parou : jogador2Parou;

            if (!jogadorParou) {
                ui.exibirMensagem("\n=== Vez de " + jogadorAtual.getNome() + " ===");
                int pontuacao = pontuador.verificarPontuacao(jogadorAtual.getCartas(), modo);
                ui.exibirPontuacao(jogadorAtual.getNome(), pontuacao);

                int opcao = ui.perguntarEscolhaJogada();

                switch (opcao) {
                    case 1 -> {
                        Carta novaCarta = baralho.tirarCarta();
                        jogadorAtual.adicionarCarta(novaCarta);
                        ui.exibirCartaPegada(novaCarta);
                        ui.exibirCartasRestantes(baralho.cartasRestantes());
                        int novaPontuacao = pontuador.verificarPontuacao(jogadorAtual.getCartas(), modo);
                        if (novaPontuacao == 21) {
                            ui.exibirMensagem("Parabéns, você fez 21!");
                            encerrarJogo(jogadorAtual);
                            return;
                        } else if (novaPontuacao > 21) {
                            ui.exibirMensagem("Estourou! Você fez " + novaPontuacao);
                            Jogador outro = (jogadorAtual == jogador1) ? jogador2 : jogador1;
                            encerrarJogo(outro);
                            return;
                        }
                    }
                    case 0 -> ui.exibirMensagem(jogadorAtual.getNome() + " passou a vez.");
                    case 2 -> {
                        ui.exibirMensagem(jogadorAtual.getNome() + " decidiu parar.");
                        if (turno == 1) jogador1Parou = true;
                        else jogador2Parou = true;
                    }
                }
            }

            if (jogador1Parou && jogador2Parou) {
                int pontos1 = pontuador.verificarPontuacao(jogador1.getCartas(), modo);
                int pontos2 = pontuador.verificarPontuacao(jogador2.getCartas(), modo);

                if (pontos1 > pontos2) encerrarJogo(jogador1);
                else if (pontos2 > pontos1) encerrarJogo(jogador2);
                else {
                    ui.exibirEmpate(pontos1, pontos2);
                    jogoFinalizado = true;
                }
                return;
            }

            turno = (turno == 1) ? 2 : 1;
        }
    }

    private void encerrarJogo(Jogador vencedor) {
        int pontuacao = pontuador.verificarPontuacao(vencedor.getCartas(), modo);
        ui.exibirVencedor(vencedor.getNome(), pontuacao);
        jogoFinalizado = true;
    }
}
