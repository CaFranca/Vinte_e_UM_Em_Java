package br.edu.ifsp.spo.java.cards.partidas;
import java.util.Scanner;
import br.edu.ifsp.spo.java.cards.itens.Carta;

public class JogoUI {
    private final Scanner scanner;

    public JogoUI() {
        scanner = new Scanner(System.in);
    }

    public String pedirNomeJogador(int numeroJogador) {
        System.out.println("Digite o nome do Jogador " + numeroJogador + ":");
        return scanner.nextLine();
    }

    public int escolherModoPontuacao() {
        System.out.println("Escolha o modo de pontuação:");
        System.out.println("[1] Clássico - Figuras valem 10");
        System.out.println("[2] Alternativo - Figuras valem 1");
        return scanner.nextInt();
    }

    public int perguntarSeAceitaRevanche() {
        System.out.println("Aceita uma revanche?: \n[1] - Sim\n[2] - Não");
        return scanner.nextInt();
    }

    public void exibirPontuacao(String jogador, int pontuacao) {
        System.out.println(jogador + " - Pontuação atual: " + pontuacao);
    }

    public int perguntarEscolhaJogada() {
        System.out.println("Deseja pegar carta? [1] Sim | [0] Passar | [2] Parar:");
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

    public void exibirEmpate(int pontos1, int pontos2) {
        System.out.println("Empate! Ambos fizeram " + pontos1 + " pontos.");
    }

    public void exibirCartaPegada(Carta carta) {
        System.out.println("Você pegou a carta: " + carta);
    }

    public void exibirCartasRestantes(int cartasRestantes) {
        System.out.println("Cartas restantes no baralho: " + cartasRestantes);
    }

    public void fecharScanner() {
        scanner.close();
    }
}
