package br.edu.ifsp.spo.java.cards.partidas;

import br.edu.ifsp.spo.java.cards.itens.Carta;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class JogoUI {
    private final Scanner scanner;

    public JogoUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Solicita e retorna o número de rodadas, garantindo que seja entre 1 e 10.
     * Re-pede caso o usuário insira valor inválido ou não numérico.
     */
    public int numRodadas() {
        int rodadas = -1;
        while (rodadas < 1 || rodadas > 10) {
            System.out.println("Digite o número de rodadas que terá (entre 1 e 10):");
            try {
                rodadas = scanner.nextInt();
                if (rodadas < 1 || rodadas > 10) {
                    System.out.println("Número fora do permitido, digite entre 1 e 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                scanner.nextLine(); // limpar entrada inválida
            }
        }
        scanner.nextLine(); // limpar o \n pendente
        return rodadas;
    }

    public void exibirPontuacaoTotal(String nomeJogador, int pontuacaoTotal) {
        System.out.println(nomeJogador + " - Pontuação total até agora: " + pontuacaoTotal + " pontos");
    }

    public int encerrar() {
        System.out.println("As rodadas se encerraram");
        return 2; // Retorno fixo conforme código original
    }

    public void exibirPontuacaoFinal(Jogador jogador1, Jogador jogador2) {
        System.out.println("\n=== Pontuação Final ===");
        System.out.println(jogador1.getNome() + ": " + jogador1.getPontuacao() + " pontos");
        System.out.println(jogador2.getNome() + ": " + jogador2.getPontuacao() + " pontos");

        if (jogador1.getPontuacao() > jogador2.getPontuacao()) {
            System.out.println("🏆 " + jogador1.getNome() + " venceu!");
        } else if (jogador2.getPontuacao() > jogador1.getPontuacao()) {
            System.out.println("🏆 " + jogador2.getNome() + " venceu!");
        } else {
            System.out.println("🤝 Empate!");
        }
    }

    public String[] obterNomesDosJogadores(int modo) {
        String nome1, nome2;

        switch (modo) {
            case 1 -> { // PvP
                nome1 = pedirNomeJogador(1);
                nome2 = pedirNomeJogador(2);
            }
            case 2 -> { // PvE
                nome1 = pedirNomeJogador(1);
                nome2 = "I.A";
            }
            case 3 -> { // EvE
                nome1 = "I.A-1";
                nome2 = "I.A-2";
            }
            default -> {
                nome1 = "Jogador 1";
                nome2 = "Jogador 2";
            }
        }
        return new String[]{nome1, nome2};
    }

    /**
     * Solicita e retorna o modo de jogo escolhido (1, 2 ou 3).
     * Repete até receber uma entrada válida.
     */
    public int escolherJogadores() {
        int escolha = -1;
        while (escolha < 1 || escolha > 3) {
            System.out.println("Escolha o modo de Jogo:");
            System.out.println("[1] PvP - Player vs Player");
            System.out.println("[2] PvE - Player vs I.A");
            System.out.println("[3] EvE - I.A-1 vs I.A-2");
            System.out.print("Digite: ");
            try {
                escolha = scanner.nextInt();
                if (escolha < 1 || escolha > 3) {
                    System.out.println("Escolha inválida. Digite 1, 2 ou 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.nextLine(); // limpar entrada inválida
            }
        }
        scanner.nextLine(); // limpar \n pendente
        return escolha;
    }

    /**
     * Solicita e retorna o modo de pontuação (1 ou 2).
     * Repete até receber entrada válida.
     */
    public int escolherModoPontuacao() {
        int modo = -1;
        while (modo != 1 && modo != 2) {
            System.out.println("Escolha o modo de pontuação:");
            System.out.println("[1] Clássico - Figuras valem 10");
            System.out.println("[2] Alternativo - Figuras valem 1");
            System.out.print("Digite: ");
            try {
                modo = scanner.nextInt();
                if (modo != 1 && modo != 2) {
                    System.out.println("Escolha inválida. Digite 1 ou 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.nextLine();
            }
        }
        scanner.nextLine(); // limpar \n pendente
        return modo;
    }

    public String pedirNomeJogador(int numeroJogador) {
        System.out.println("Digite o nome do Jogador " + numeroJogador + ":");
        return scanner.nextLine();
    }

    /**
     * Pergunta se aceita revanche, retornando 1 (sim) ou 2 (não).
     * Repete enquanto a resposta for inválida.
     */
    public int perguntarSeAceitaRevanche() {
        int resposta = -1;
        while (resposta != 1 && resposta != 2) {
            System.out.println("Aceita uma revanche? \n[1] - Sim\n[2] - Não\nDigite:");
            try {
                resposta = scanner.nextInt();
                if (resposta != 1 && resposta != 2) {
                    System.out.println("Resposta inválida. Digite 1 para Sim ou 2 para Não.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.nextLine();
            }
        }
        scanner.nextLine(); // limpar \n pendente
        return resposta;
    }

    public void exibirPontuacao(String jogador, int pontuacao) {
        System.out.println(jogador + " - Pontuação atual: " + pontuacao);
    }

    /**
     * Pergunta se o jogador deseja pegar carta (1) ou parar (2).
     * Repete enquanto a entrada for inválida.
     */
    public int perguntarEscolhaJogada() {
        int escolha = -1;
        while (escolha != 1 && escolha != 2) {
            System.out.println("Deseja pegar carta? [1] Sim | [2] Parar");
            System.out.print("Digite: ");
            try {
                escolha = scanner.nextInt();
                if (escolha != 1 && escolha != 2) {
                    System.out.println("Escolha inválida. Digite 1 para Sim ou 2 para Parar.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.nextLine();
            }
        }
        scanner.nextLine(); // limpar \n pendente
        return escolha;
    }

    public void exibirResultadoRodada(String jogador, int pontuacao) {
        System.out.println(jogador + " fez " + pontuacao + " pontos nesta rodada.");
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void exibirVencedor(String vencedor, int pontuacao) {
        System.out.println("🏆 " + vencedor + " venceu com " + pontuacao + " pontos!");
    }

    public void exibirEmpate(int pontos1) {
        System.out.println("Empate! Ambos fizeram " + pontos1 + " pontos.");
    }

    public void exibirCartaPegada(Carta carta, List<Carta> maoDoJogador) {
        System.out.println("Você pegou a carta: " + carta);
        carta.printCarta(carta.getNaipe(), carta.getValor());

        System.out.println("\n== Cartas na sua mão ==");
        Carta.exibirCartasLadoALado(maoDoJogador);
        System.out.println("========================\n");
    }

    public void exibirCartasRestantes(int cartasRestantes) {
        System.out.println("Cartas restantes no baralho: " + cartasRestantes);
    }

    public void fecharScanner() {
        scanner.close();
    }
}
