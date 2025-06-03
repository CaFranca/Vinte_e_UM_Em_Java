package br.edu.ifsp.spo.java.cards;

import br.edu.ifsp.spo.java.cards.itens.Baralho;
import br.edu.ifsp.spo.java.cards.partidas.Jogo;
import br.edu.ifsp.spo.java.cards.partidas.JogoUI;

public class App {

    // Número total de rodadas a serem jogadas, definido via interface UI
    public static int num_rodadas;

    public static void main(String[] args) throws InterruptedException {
        JogoUI ui = new JogoUI();

        int tipoJogo = -1;
        while (true) {
            try {
                tipoJogo = ui.escolherJogadores();
                if (tipoJogo < 1 || tipoJogo > 3) {
                    System.out.println("Escolha inválida. Digite um número entre 1 e 3.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
                ui.fecharScanner(); // Fechar scanner para evitar problemas no próximo uso
            }
        }

        int modo = -1;
        while (true) {
            try {
                modo = ui.escolherModoPontuacao();
                if (modo < 1 || modo > 2) {
                    System.out.println("Escolha inválida. Digite 1 ou 2.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
            }
        }

        int rodadas = -1;
        while (true) {
            try {
                rodadas = ui.numRodadas();
                if (rodadas < 1 || rodadas > 10) {
                    System.out.println("Número de rodadas fora do permitido (1 a 10). Tente novamente.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
            }
        }
        num_rodadas = rodadas;

        String[] nomes = ui.obterNomesDosJogadores(tipoJogo);

        Baralho baralho = new Baralho();

        int jogar = 1;  // Controle do loop (1 = jogar, 2 = sair)

        while (jogar == 1) {
            Jogo jogo = new Jogo(nomes[0], nomes[1], modo, ui, tipoJogo, baralho);
            jogo.iniciar();

            if (jogo.fim_rodadas) {
                jogar = 2;
            } else {
                if (jogo.jogoEmpatado) {
                    ui.exibirMensagem("O jogo terminou empatado. Reiniciando o jogo...");
                } else {
                    int respostaRevanche = -1;
                    while (true) {
                        try {
                            respostaRevanche = ui.perguntarSeAceitaRevanche();
                            if (respostaRevanche != 1 && respostaRevanche != 2) {
                                System.out.println("Opção inválida. Digite 1 para Sim ou 2 para Não.");
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("Entrada inválida. Por favor, digite um número válido.");
                        }
                    }
                    jogar = respostaRevanche;

                    if (jogar == 1) {
                        ui.exibirMensagem("Iniciando uma nova partida...");
                    } else {
                        ui.exibirMensagem("Jogo encerrado!!");
                    }
                }
            }
        }

        ui.fecharScanner();
    }
}
