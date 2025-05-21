package br.edu.ifsp.spo.java.cards.partidas;

import br.edu.ifsp.spo.java.cards.App;
import br.edu.ifsp.spo.java.cards.itens.Baralho;
import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.itens.Pontuador;

public class Jogo {
    private final Jogador jogador1;
    private final Jogador jogador2;
    private final Baralho baralho;
    private final Pontuador pontuador;
    private final int modo;
    private final JogoUI ui; // Usando JogoUI
    private final int tipoJogo;

    private int turno;
    private boolean jogador1Parou;
    private boolean jogador2Parou;
    private boolean jogoFinalizado;
    public boolean jogoEmpatado;
    public int rodada;
    public boolean fim_rodadas;
    private final int numRodadas;

    public Jogo(String nomeJogador1, String nomeJogador2, int modo, JogoUI ui, int tipoJogo, Baralho baralho) {
        if (tipoJogo == 1) { // PvP
            this.jogador1 = new Jogador(nomeJogador1);
            this.jogador2 = new Jogador(nomeJogador2);
        } else if (tipoJogo == 2) { // PvE
            this.jogador1 = new Jogador(nomeJogador1);
            this.jogador2 = new IA("IA");
        } else if (tipoJogo == 3) { // EvE
            this.jogador1 = new IA("IA 1");
            this.jogador2 = new IA("IA 2");
        } else {
            this.jogador1 = new Jogador(nomeJogador1);
            this.jogador2 = new Jogador(nomeJogador2);
        }

        this.baralho = baralho;
        this.pontuador = new Pontuador();
        this.modo = modo;
        this.ui = ui; // Inicializando JogoUI
        this.turno = 1;
        this.jogador1Parou = false;
        this.jogador2Parou = false;
        this.jogoFinalizado = false;
        this.jogoEmpatado = false;
        this.tipoJogo = tipoJogo;
        this.fim_rodadas = false;
        this.numRodadas = App.num_rodadas;
    }

    public void iniciar() throws InterruptedException {

        int pontosJogador1 = 0;
        int pontosJogador2 = 0;

        for (rodada = 1; rodada <= numRodadas; rodada++) {
            // RESET da rodada
            jogador1.limparCartas();
            jogador2.limparCartas();
            jogador1Parou = false;
            jogador2Parou = false;
            jogoFinalizado = false;
            turno = 1;

            ui.exibirMensagem("\n=== Rodada " + rodada + " de " + numRodadas + " ===");
            ui.exibirPontuacaoTotal(jogador1.getNome(), pontosJogador1);
            ui.exibirPontuacaoTotal(jogador2.getNome(), pontosJogador2);

            // Duas cartas para cada jogador
            for (int i = 0; i < 2; i++) {
                jogador1.adicionarCarta(baralho.tirarCarta());
                jogador2.adicionarCarta(baralho.tirarCarta());
            }

            while (!jogoFinalizado) {
                Jogador jogadorAtual = (turno == 1) ? jogador1 : jogador2;
                boolean jogadorParou = (turno == 1) ? jogador1Parou : jogador2Parou;

                if (!jogadorParou) {
                    ui.exibirMensagem("\n=== Vez de " + jogadorAtual.getNome() + " ===");

                    boolean vezEncerrada = false;
                    while (!vezEncerrada) {
                        int pontuacao = pontuador.verificarPontuacao(jogadorAtual.getCartas(), modo);
                        ui.exibirPontuacao(jogadorAtual.getNome(), pontuacao);

                        int opcao;
                        if (tipoJogo == 1 || (tipoJogo == 2 && jogadorAtual == jogador1)) {
                            opcao = ui.perguntarEscolhaJogada();
                        } else {
                            IA ia = (IA) jogadorAtual;
                            opcao = ia.decidirJogada(pontuacao);
                        }

                        switch (opcao) {
                            case 1 -> {
                                if (baralho.cartasRestantes() == 0) {
                                    ui.exibirMensagem("O baralho acabou! Reembaralhando...");
                                    baralho.resetarBaralho(); // Remover se reembaralhar for proibido
                                }
                                Carta novaCarta = baralho.tirarCarta();
                                jogadorAtual.adicionarCarta(novaCarta);
                                ui.exibirCartaPegada(novaCarta);
                                ui.exibirCartasRestantes(baralho.cartasRestantes());

                                int novaPontuacao = pontuador.verificarPontuacao(jogadorAtual.getCartas(), modo);
                                if (novaPontuacao == 21) {
                                    ui.exibirMensagem("Parabéns, você fez 21!");
                                    jogador1Parou = true;
                                    jogador2Parou = true;
                                    vezEncerrada = true;
                                } else if (novaPontuacao > 21) {
                                    ui.exibirMensagem("Estourou! " + jogadorAtual.getNome() + " fez " + novaPontuacao);
                                    if (turno == 1) jogador1Parou = true;
                                    else jogador2Parou = true;
                                    vezEncerrada = true;
                                }
                            }
                            case 2 -> {
                                ui.exibirMensagem(jogadorAtual.getNome() + " decidiu parar.");
                                if (turno == 1) jogador1Parou = true;
                                else jogador2Parou = true;
                                vezEncerrada = true;
                            }
                        }
                    }
                }

                if (jogador1Parou && jogador2Parou) {
                    int pontos1 = pontuador.verificarPontuacao(jogador1.getCartas(), modo);
                    int pontos2 = pontuador.verificarPontuacao(jogador2.getCartas(), modo);

                    if (pontos1 > 21 && pontos2 > 21) {
                        int perda1 = pontos1 - 21;
                        int perda2 = pontos2 - 21;
                        pontosJogador1 -= perda1;
                        pontosJogador2 -= perda2;
                        ui.exibirMensagem("Ambos estouraram! Perderam " + perda1 + " e " + perda2 + " pontos.");
                    } else if (pontos1 == 21 && pontos2 == 21) {
                        pontosJogador1 += 21;
                        pontosJogador2 += 21;
                        ui.exibirMensagem("Ambos fizeram 21! Recebem 21 pontos.");
                    } else if (pontos1 == 21) {
                        pontosJogador1 += 30;
                        pontosJogador2 -= 5;
                        ui.exibirMensagem(jogador1.getNome() + " venceu com 21! +30 pontos");
                    } else if (pontos2 == 21) {
                        pontosJogador2 += 30;
                        pontosJogador1 -= 5;
                        ui.exibirMensagem(jogador2.getNome() + " venceu com 21! +30 pontos");
                    } else if (pontos1 <= 21 && pontos2 > 21) {
                        pontosJogador1 += pontos1;
                        pontosJogador2 -= 5;
                        ui.exibirMensagem(jogador1.getNome() + " venceu! +" + pontos1 + " pontos");
                    } else if (pontos2 <= 21 && pontos1 > 21) {
                        pontosJogador2 += pontos2;
                        pontosJogador1 -= 5;
                        ui.exibirMensagem(jogador2.getNome() + " venceu! +" + pontos2 + " pontos");
                    } else if (pontos1 == pontos2) {
                        pontosJogador1 += 10;
                        pontosJogador2 += 10;
                        ui.exibirEmpate(pontos1);
                    } else if (pontos1 < 21 && pontos2 < 21) {
                        if (pontos1 > pontos2) {
                            int diff = pontos1 - pontos2;
                            pontosJogador1 += diff;
                            ui.exibirMensagem(jogador1.getNome() + " venceu! +" + diff + " pontos");
                        } else {
                            int diff = pontos2 - pontos1;
                            pontosJogador2 += diff;
                            ui.exibirMensagem(jogador2.getNome() + " venceu! +" + diff + " pontos");
                        }
                    }

                    jogoFinalizado = true;
                }

                turno = (turno == 1) ? 2 : 1;
            }
            int pontuacaoFinalRodada1 = pontosJogador1 - jogador1.getPontuacao();
            int pontuacaoFinalRodada2 = pontosJogador2 - jogador2.getPontuacao();

            jogador1.addPontuacao(pontuacaoFinalRodada1);
            jogador2.addPontuacao(pontuacaoFinalRodada2);


        }

        fim_rodadas = true;
        ui.exibirPontuacaoFinal(jogador1, jogador2);
    }


    private void encerrarJogo(Jogador vencedor) {
        int pontuacao = pontuador.verificarPontuacao(vencedor.getCartas(), modo);
        ui.exibirVencedor(vencedor.getNome(), pontuacao);
        jogoFinalizado = true;
    }
}
