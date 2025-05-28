package br.edu.ifsp.spo.java.cards.partidas;

import br.edu.ifsp.spo.java.cards.itens.Carta;

import java.util.Scanner;

public class JogoUI {
    private final Scanner scanner;

    public JogoUI() {
        scanner = new Scanner(System.in);
    }

    public int num_rodadas() {
        System.out.println("Digite o numero de rodadas que terá:");
        int rodadas = scanner.nextInt();
        while (rodadas > 10 || rodadas < 1) {
            System.out.println("Numero de rodadas fora do permitido, digite entre 1 e 10");
            rodadas = scanner.nextInt();
        }
        return rodadas;
    }

    public void exibirPontuacaoTotal(String nomeJogador, int pontuacaoTotal) {
        System.out.println(nomeJogador + " - Pontuação total até agora: " + pontuacaoTotal + " pontos");
    }


    public int encerrar() {
        System.out.println("As rodadas se encerraram");
        return 2;
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
        scanner.nextLine(); // Consumir quebra de linha pendente do scanner
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


    public int escolherJogadores() {
        System.out.println("Escolha o modo de Jogo:");
        System.out.println("[1] PvP - Player vs Player");
        System.out.println("[2] PvE - Player vs I.A");
        System.out.println("[3] EvE - I.A-1 vs I.A-2");
        System.out.println("Digite:");
        return scanner.nextInt();
    }

    public int escolherModoPontuacao() {
        System.out.println("Escolha o modo de pontuação:");
        System.out.println("[1] Clássico - Figuras valem 10");
        System.out.println("[2] Alternativo - Figuras valem 1");
        System.out.println("Digite:");
        return scanner.nextInt();
    }

    public String pedirNomeJogador(int numeroJogador) {
        System.out.println("Digite o nome do Jogador " + numeroJogador + ":");
        return scanner.nextLine();
    }


    public int perguntarSeAceitaRevanche() {
        System.out.println("Aceita uma revanche?: \n[1] - Sim\n[2] - Não\nDigite:");
        return scanner.nextInt();
    }

    public void exibirPontuacao(String jogador, int pontuacao) {
        System.out.println(jogador + " - Pontuação atual: " + pontuacao);
    }

    public int perguntarEscolhaJogada() {
        System.out.println("Deseja pegar carta? [1] Sim | [2] Parar\nDigite:");
        return scanner.nextInt();
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

    public void exibirCartaPegada(Carta carta) {
        System.out.println("Você pegou a carta: " + carta);
        var valor = carta.getValor();
        var naipe =carta.getNaipe();
        carta.printCarta(naipe,valor);

    }

    public void exibirCartasRestantes(int cartasRestantes) {
        System.out.println("Cartas restantes no baralho: " + cartasRestantes);
    }

    public void fecharScanner() {
        scanner.close();
    }
}
